package com.deep.api.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendTableUtil {
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static Connection connection = null;
    private static Statement statement = null;
    private static String username = "doubibobo";
    private static String password = "12151618";

    static {
        try {
            Class.forName(jdbcDriver);
            System.out.println("成功加载数据库驱动!");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到数据库驱动");
            e.printStackTrace();
        }
    }

    /**
     * 创建一张数据表
     */
    public static boolean createTable(String tableName, Map<String, String> colnums) {
        // TODO need to verify the tableName(Like the 2018-4-1 is invalid!)
        if (colnums == null) {
            System.out.println("column is null");
            return false;
        }
        // 获取提交的col并且获取其条件
        String getColnums = "";
        for (Map.Entry<String, String> entry : colnums.entrySet()) {
            if (entry.getValue().toString().equals("")) {
                getColnums += entry.getKey().toString() + " varchar(20) not null,";
            } else {
                getColnums += entry.getKey().toString() + " varchar(20) " + entry.getValue().toString() + ",";
            }
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String creatSql = "CREATE TABLE " + tableName + " ("
                    + getColnums
                    + "id int auto_increment"
                    + ", PRIMARY KEY(id))charset=utf8;";
            System.out.println(creatSql);
            if (0 == statement.executeLargeUpdate(creatSql)) {
                System.out.println("成功创建表");
            } else {
                System.out.println("error for create a table!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 删除一张数据表
     * @param tableName
     * @return
     */
    public static boolean dropTable(String tableName) {
        if (tableName.equals("") || tableName == null) {
            return false;
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String dropSql = "DROP TABLE " + tableName;
            if (1 == statement.executeUpdate(dropSql)) {
                System.out.println("成功删除表");
            } else {
                System.out.println("删除表格失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 方法功能: 获取用户创建的所有表格的信息
     * @return
     */
    public static Map<String, Object> getAllTable() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        Map<String, Object> tableNames = new HashMap<>();
        try {
            DatabaseMetaData databaseMetaData = (DatabaseMetaData)connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "%", null);
            while (resultSet.next()) {
                tableNames.put(resultSet.getString("TABLE_NAME"), resultSet.getString("REMARKS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }

    /**
     * 获取某一个表格的所有列
     * @param tableName
     * @return
     */
    public static Map<String, String> getAllColumns(String tableName) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        Map<String, String> tableColumns = new HashMap<>();
        try {
            DatabaseMetaData databaseMetaData = (DatabaseMetaData)connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
            while (resultSet.next()) {
                tableColumns.put(resultSet.getString("COLUMN_NAME"), resultSet.getString("REMARKS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableColumns;
    }

    /**
     * 判断数据库中是否存在着tableName表, 如果存在, 返回true, 否则返回false
     * @param tableName
     * @return
     */
    public static boolean isExistTable(String tableName) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, null);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取某张用户表中的所有数据
     * @param tableName
     * @return
     */
    public static List<Object> getAllRows(String tableName) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String querySql = "select * from " + tableName;
            List<Object> objects = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(querySql);
            while (resultSet.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    rowData.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
                }
                objects.add(rowData);
            }
            resultSet.close();
            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取某一张用户表中的单条数据
     * @param tableName
     * @param id
     * @return
     */
    public static List<Object> getOneRow(String tableName, String id) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String querySql = "select * from " + tableName + " WHERE id = " + id;
            System.out.println(querySql);
            List<Object> objects = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(querySql);
            while (resultSet.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    rowData.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                }
                objects.add(rowData);
            }
            resultSet.close();
            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  向数据表中添加一条数据
     * @param tableName
     * @param columns
     * @return
     */
    public static boolean insertRow(String tableName, Map<String, String> columns) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            // 获取提交的col并且获取其条件
            String getColumns = "";
            String getValues = "";
            for (Map.Entry<String, String> entry : columns.entrySet()) {
                getColumns += entry.getKey().toString() + ",";
                getValues += "\"" + entry.getValue().toString() + "\",";
            }
            getColumns = getColumns.substring(0, getColumns.length()-1);
            getValues = getValues.substring(0, getValues.length()-1);
            String insertSql = "INSERT INTO " + tableName + " ("
                    + getColumns
                    + ") VALUES("
                    + getValues + ")";
            System.out.println(insertSql);
            if (0 != statement.executeUpdate(insertSql)) {
                System.out.println("成功插入数据");
            } else {
                System.out.println("error for insert a row!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 从数据表中删除一条数据
     * @param tableName
     * @param id
     * @return
     */
    public static boolean deleteRow(String tableName, String id) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String deleteSql = "DELETE FROM " + tableName + " WHERE id = " + id;
            System.out.println(deleteSql);
            if (0 != statement.executeUpdate(deleteSql)) {
                System.out.println("成功删除数据");
            } else {
                System.out.println("error for insert a row!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 修改数据表中的某一条数据
     * @param tableName
     * @param columns
     * @param id
     * @return
     */
    public static boolean updateRow(String tableName, Map<String, String> columns, String id) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            // 获取提交的col并且获取其条件
            String condition = "";
            for (Map.Entry<String, String> entry : columns.entrySet()) {
                condition += entry.getKey().toString() + "= \"" + entry.getValue().toString() + "\",";
            }
            condition = condition.substring(0, condition.length()-1);
            String updateSql = "UPDATE " + tableName + " SET " + condition + " WHERE id = " + id;
            System.out.println(updateSql);
            if (0 != statement.executeUpdate(updateSql)) {
                System.out.println("修改数据成功");
                return true;
            } else {
                System.out.println("error for insert a row!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

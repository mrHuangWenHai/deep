package com.deep.domain.util;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 工具类 用于备份数据库
 * create by zhongrui on 18-4-11.
 */
public class BackupUtil {

    /**
     * 用于数据库数据备份
     * @param savePath 存放路径
     * @param hostIP  ip
     * @param username  用户名
     * @param password  密码
     * @param database  数据库
     * @param tableName 表名
     * @param suffix   文件后缀
     * @return 数据库备份结果
     * @throws InterruptedException 备份冲突
     */
    public static boolean sqlBackup(String savePath, String hostIP, String username, String password, String database, String tableName, String suffix) throws InterruptedException{
        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhMMss");
            String fileName = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()))+suffix;

            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));

            Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + username + " -p" + password + " --set-charset=UTF8 " + database + " " + tableName );
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor() == 0){//0 表示线程正常终止。
                return true;
            }
        }catch (IOException e){

            e.printStackTrace();
        } finally {

            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (printWriter != null){
                    printWriter.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return false;
    }
}

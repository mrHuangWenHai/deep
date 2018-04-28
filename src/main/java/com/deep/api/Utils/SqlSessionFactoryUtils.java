package com.deep.api.Utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * create by zhongrui on 18-4-16.
 */
public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;

    /**
     * 实现连接another数据库
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory() {

        synchronized (LOCK) {

            if (sqlSessionFactory != null){
                return sqlSessionFactory;
            }

            String resource = "mybatis-config-outer.xml";
            InputStream inputStream;
            try{
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }

    }

    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            getSqlSessionFactory();
        }
        return  sqlSessionFactory.openSession();
    }

}


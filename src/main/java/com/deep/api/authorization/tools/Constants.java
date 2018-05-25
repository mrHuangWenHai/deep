package com.deep.api.authorization.tools;

public class Constants {
    // 存储当前登录用户ID的字段名
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    // token的有效期
    public static final int TOKEN_EXPIRES_HOUR = 72;
    // 存放Authorization的header字段
    public static final String AUTHORIZATION = "authorization";
    // 设置文件的上传路径 TODO 上传时需要更改路径
    public static final String filePath = "/root/picture/";
    // 设置URL TODO 上传时需要更改路径
    public static final String serverUrl = "180.76.180.95:9010/picture/";
}

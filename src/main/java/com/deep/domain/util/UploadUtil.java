package com.deep.domain.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * create by zhongrui on 18-4-2.
 */
public class UploadUtil {
    /**
     * 随机生成文件名的文件上传
     * @param file 文件(二进制)
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return 上传结果
     * @throws Exception 读取文件异常
     */
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhMMss");
        Calendar calendar = Calendar.getInstance();
        fileName = dateFormat.format(calendar.getTime()) + fileName;
        FileOutputStream out = new FileOutputStream(filePath + fileName);
//        System.out.println("filePath:"+filePath);
//        System.out.println("fileName:"+fileName);
//        System.out.println("fileAddress:"+fileAddress);
        out.write(file);
        out.flush();
        out.close();
        return fileName;
    }


}

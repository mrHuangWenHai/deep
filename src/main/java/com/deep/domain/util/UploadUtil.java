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
    public static String uploadFile(byte[] file, String filePath, String fileName,String fileAddress) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhMMss");
        Calendar calendar = Calendar.getInstance();
        fileName = dateFormat.format(calendar.getTime()) + fileName;
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        fileAddress = filePath + fileName;
//        System.out.println("filePath:"+filePath);
//        System.out.println("fileName:"+fileName);
//        System.out.println("fileAddress:"+fileAddress);
        out.write(file);
        out.flush();
        out.close();
        return fileAddress;
    }
}

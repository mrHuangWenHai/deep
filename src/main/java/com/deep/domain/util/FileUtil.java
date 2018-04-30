package com.deep.domain.util;

import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * create by zhongrui on 18-4-5.
 */
public class FileUtil {


    /**
     * 生产模块数据删除时
     * 将耳牌文件一起删除
     * @param path 路径
     * @return 是否成功
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        return !file.exists() || file.delete();
    }


    /**
     * 限定文件类型
     * @param file MultipartFile
     * @return
     */

    public static String getFileHeader( MultipartFile file) {
        InputStream is = null;
        String value = null;
        try {
            is = file.getInputStream();
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {

        } finally {
            if (null != is) {
                try {
                    is.close(); }
                    catch (IOException e) {

                }
            }
        }
        return value;
    }
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }

        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
}


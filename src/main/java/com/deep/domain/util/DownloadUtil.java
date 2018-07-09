package com.deep.domain.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 下载文件Util
 * create by zhongrui on 18-4-19.
 */
public class DownloadUtil {

    /**
     * 下载文件
     * @param response HttpServletResponse
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return 下载结果
     */
    public static boolean testDownload(HttpServletResponse response,String filePath, String fileName, OutputStream outputStream) throws UnsupportedEncodingException{
        File file = new File(filePath + fileName);
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                fis.close();
                bis.close();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }



}

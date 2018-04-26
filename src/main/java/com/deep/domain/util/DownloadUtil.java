package com.deep.domain.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 下载文件Util
 * create by zhongrui on 18-4-19.
 */
public class DownloadUtil {

    public static boolean downloadFile(HttpServletResponse response, String fileName , String filePath,String fileLocation) {
        //文件原路径 文件下载到的路径 文件名

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            InputStream in = new FileInputStream(filePath + fileName);

            //OutputStream out = response.getOutputStream();
            OutputStream out = new FileOutputStream(fileLocation + fileName);
            byte[] buff = new byte[1024];
            int len;
            File targetFile = new File(fileLocation);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            while ((len = in.read(buff)) > 0) {
                out.write(buff, 0, len);
            }
            in.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }




}

package com.deep.api.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class UploadFileUtil {
    public static void upload(MultipartFile file, String fileAddress) {
        if (!file.isEmpty()) {
            File saveFile = new File(fileAddress);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("上传失败，" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败，" + e.getMessage());
            }
        }
    }
}

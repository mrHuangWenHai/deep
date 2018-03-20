package com.deep.domain.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * create by zhongrui on 18-3-20.
 */
public class UploadUtil {

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void uploadFile(byte[] file, String filePath) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhMMss");
        Calendar calendar = Calendar.getInstance();

        filename = dateFormat.format(calendar.getTime());
        FileOutputStream out = new FileOutputStream(filePath + filename);
        out.write(file);
        out.flush();
        out.close();
    }
}

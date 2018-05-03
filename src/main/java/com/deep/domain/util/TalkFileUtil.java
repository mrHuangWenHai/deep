package com.deep.domain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

public class TalkFileUtil {

  protected static Logger logger = LoggerFactory.getLogger(TalkFileUtil.class);

  private static String bytesToHexString(byte[] src) {
    StringBuilder stringBuilder = new StringBuilder();
    if (src == null || src.length <= 0) {
      return null;
    }
    for (byte aSrc : src) {
      // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
      String hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }

  public static boolean isSafeFile(MultipartFile file) {
    String bytes = getFileHeader(file);
    //System.out.println(bytes);
    //jpg,jpeg,png,gif,bmp,webp,flv,avi,m4a,mkv,mp3,mp4,wma,txt,doc,docx,ppt,pptx,xls,xlsx,pdf,zip,rar.
    logger.info(bytes);
    return bytes.equals("FFD8FFE0") || bytes.equals("FFD8FFE1") || bytes.equals("FFD8FFE8") || bytes.equals("89504E47") || bytes.equals("47494638") || bytes.contains("424D") || bytes.equals("52494646") || bytes.equals("464C5601") || bytes.equals("0000001C") || bytes.equals("1A45DFA3") || bytes.equals("49443303") || bytes.equals("3026B275") || bytes.equals("00000000") || bytes.equals("504B0304") || bytes.equals("D0CF11E0") || bytes.equals("25504446") || bytes.equals("52617221");
  }

  private static String getFileHeader(MultipartFile file) {
    InputStream is = null;
    String value = null;
    try {
      is = file.getInputStream();
      byte[] b = new byte[4];
      is.read(b, 0, b.length);
      value = bytesToHexString(b);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return value;
  }

}


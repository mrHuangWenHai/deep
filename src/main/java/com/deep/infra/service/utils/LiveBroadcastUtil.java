package com.deep.infra.service.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by huangwenhai on 2018/3/13.
 */
public class LiveBroadcastUtil {

  private static final char[] DIGITS_LOWER =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  public static long getUnixTime(int field, int amount) {

    Calendar calendar = Calendar.getInstance();
    calendar.add(field, amount);
    Date date = calendar.getTime();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long t = 0;
    try {
      t = df.parse(df.format(date)).getTime() / 1000;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return t;
  }

  public static String md5Secret(String input) {

    String txSecret = null;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      txSecret  = byteArrayToHexString(
          messageDigest.digest(input.getBytes("UTF-8")));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return txSecret;
  }

  public static String getSafeUrl(String key, String streamId, long txTime) {

    String input = new StringBuilder().
        append(key).
        append(streamId).
        append(Long.toHexString(txTime).toUpperCase()).toString();

    String txSecret = md5Secret(input);
    return txSecret == null ? "" :
        new StringBuilder().
            append("&txSecret=").
            append(txSecret).
            append("&").
            append("txTime=").
            append(Long.toHexString(txTime).toUpperCase()).
            toString();

  }

  public static String byteArrayToHexString(byte[] data) {
    char[] out = new char[data.length << 1];

    for (int i = 0, j = 0; i < data.length; i++) {
      out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
      out[j++] = DIGITS_LOWER[0x0F & data[i]];
    }
    return new String(out);
  }
}

package com.deep.api.Utils;

public class StringToLongUtil {
    public static long stringToLong(String user) {
        long uid;
        try {
            uid = Integer.parseInt(user);
            if (uid < 0) {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return uid;
    }
    public static int stringToInt(String user) {
        int uid;
        try {
            uid = Integer.parseInt(user);
            if (uid < 0) {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return uid;
    }

    public static byte stringToByte(String user) {
        byte uid;
        try {
            uid = Byte.parseByte(user);
            if (uid < 0) {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return uid;
    }
}

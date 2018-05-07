package com.deep.api.Utils;

import java.util.ArrayList;
import java.util.List;

public class PermitUtil {
    // 将前台传递的数组进行解析替换成为字符串
    public static String stringArrayToString (List<String> permit) {
        char[] defaultPermit = new char[192];
        // 进行数组初始化
        for (int i = 0; i < defaultPermit.length; i++) {
            defaultPermit[i] = '0';
        }
        for (String aPermit : permit) {
            String[] temp = aPermit.split("-");
            switch (temp[0]) {
                case "0":
                    defaultPermit[Integer.valueOf(temp[1])] = '1';
                    break;
                case "1":
                    defaultPermit[Integer.valueOf(temp[1]) + 6] = '1';
                    break;
                case "2":
                    defaultPermit[Integer.valueOf(temp[1]) + 12] = '1';
                    break;
                case "3":
                    defaultPermit[Integer.valueOf(temp[1]) + 18] = '1';
                    break;
                case "4":
                    defaultPermit[Integer.valueOf(temp[1]) + 24] = '1';
                    break;
                case "5":
                    defaultPermit[Integer.valueOf(temp[1]) + 30] = '1';
                    break;
                case "6":
                    defaultPermit[Integer.valueOf(temp[1]) + 48] = '1';
                    break;
                case "7":
                    defaultPermit[Integer.valueOf(temp[1]) + 36] = '1';
                    break;
                case "8":
                    defaultPermit[Integer.valueOf(temp[1]) + 40] = '1';
                    break;
                case "9":
                    defaultPermit[Integer.valueOf(temp[1]) + 44] = '1';
                    break;
                case "10":
                    defaultPermit[Integer.valueOf(temp[1]) + 56] = '1';
                    break;
                case "11":
                    defaultPermit[Integer.valueOf(temp[1]) + 60] = '1';
                    break;
                case "12":
                    defaultPermit[Integer.valueOf(temp[1]) + 64] = '1';
                    break;
                case "13":
                    defaultPermit[Integer.valueOf(temp[1]) + 68] = '1';
                    break;
                case "14":
                    defaultPermit[Integer.valueOf(temp[1]) + 72] = '1';
                    break;
                case "15":
                    defaultPermit[Integer.valueOf(temp[1]) + 76] = '1';
                    break;
                case "16":
                    defaultPermit[Integer.valueOf(temp[1]) + 88] = '1';
                    break;
                case "17":
                    defaultPermit[Integer.valueOf(temp[1]) + 92] = '1';
                    break;
                case "18":
                    defaultPermit[Integer.valueOf(temp[1]) + 96] = '1';
                    break;
                case "19":
                    defaultPermit[Integer.valueOf(temp[1]) + 100] = '1';
                    break;
                case "20":
                    defaultPermit[Integer.valueOf(temp[1]) + 104] = '1';
                    break;
                default:
                    System.out.println("error");
            }
        }
        System.out.println("defaultPermit = " + String.valueOf(defaultPermit));
        return String.valueOf(defaultPermit);
    }

    // 将字符串解析成字符数组
    public static List<String> stringToStringArray(String permit) {
        List<String> permitFinal = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (permit.charAt(i*6 + j) == '1') {
                    permitFinal.add(i + "-" + j);
                }
            }
        }

        for (int i = 48; i < 53; i++) {
            if (permit.charAt(i)  == '1') {
                permitFinal.add(6 + "-" + String.valueOf(i - 48));
            }
        }

        for (int i = 36; i < 48; i++) {
            for (int j = 7; j < 10; j++) {
                for (int k = 0; k < 4; k++) {
                    if (permit.charAt(i)  == '1') {
                        permitFinal.add(j + "-" + k);
                    }
                }
            }
        }

        for (int i = 56; i < 80; i++) {
            for (int j = 10; j < 16; j++) {
                for (int k = 0; k < 4; k++) {
                    if (permit.charAt(i)  == '1') {
                        permitFinal.add(j + "-" + k);
                    }
                }
            }
        }

        for (int i = 88; i < 108; i++) {
            for (int j = 16; j < 21; j++) {
                for (int k = 0; k < 4; k++) {
                    if (permit.charAt(i)  == '1') {
                        permitFinal.add(j + "-" + k);
                    }
                }
            }
        }

        return permitFinal;
    }
}

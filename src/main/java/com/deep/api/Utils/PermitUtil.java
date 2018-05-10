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

        Integer count = 7;

        for (int i = 36; i < 40; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(7 + "-" + String.valueOf(i-36));
            }
        }

        for (int i = 40; i < 44; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(8 + "-" + String.valueOf(i-40));
            }
        }

        for (int i = 44; i < 48; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(9 + "-" + String.valueOf(i-44));
            }
        }

        for (int i = 56; i < 60; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(10 + "-" + String.valueOf(i-56));
            }
        }

        for (int i = 60; i < 64; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(11 + "-" + String.valueOf(i-60));
            }
        }

        for (int i = 64; i < 68; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(12 + "-" + String.valueOf(i-64));
            }
        }

        for (int i = 68; i < 72; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(13 + "-" + String.valueOf(i-68));
            }
        }

        for (int i = 72; i < 76; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(14 + "-" + String.valueOf(i-72));
            }
        }

        for (int i = 76; i < 80; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(15 + "-" + String.valueOf(i-76));
            }
        }



        for (int i = 88; i < 92; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(16 + "-" + String.valueOf(i-88));
            }
        }

        for (int i = 92; i < 96; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(17 + "-" + String.valueOf(i-92));
            }
        }

        for (int i = 96; i < 100; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(18 + "-" + String.valueOf(i-96));
            }
        }

        for (int i = 100; i < 104; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(19 + "-" + String.valueOf(i-100));
            }
        }

        for (int i = 104; i < 108; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(20 + "-" + String.valueOf(i-104));
            }
        }

        for (int i = 109; i < 112; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(21 + "-" + String.valueOf(i-109));
            }
        }

        for (int i = 112; i < 116; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(22 + "-" + String.valueOf(i-112));
            }
        }

        for (int i = 118; i < 124; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(23 + "-" + String.valueOf(i-118));
            }
        }

        if (permit.charAt(116) == '1')
            permitFinal.add(24 + "-" + String.valueOf(0));

        if (permit.charAt(117) == '1')
            permitFinal.add(25 + "-" + String.valueOf(0));

        return permitFinal;
    }
}

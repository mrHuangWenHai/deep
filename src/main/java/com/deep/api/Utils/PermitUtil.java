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
                    defaultPermit[Integer.valueOf(temp[1]) + 36] = '1';
                    break;
                case "7":
                    defaultPermit[Integer.valueOf(temp[1]) + 42] = '1';
                    break;
                case "8":
                    defaultPermit[Integer.valueOf(temp[1]) + 47] = '1';
                    break;
                case "9":
                    defaultPermit[Integer.valueOf(temp[1]) + 51] = '1';
                    break;
                case "10":
                    defaultPermit[Integer.valueOf(temp[1]) + 55] = '1';
                    break;
                case "11":
                    defaultPermit[Integer.valueOf(temp[1]) + 59] = '1';
                    break;
                case "12":
                    defaultPermit[Integer.valueOf(temp[1]) + 63] = '1';
                    break;
                case "13":
                    defaultPermit[Integer.valueOf(temp[1]) + 67] = '1';
                    break;
                case "14":
                    defaultPermit[Integer.valueOf(temp[1]) + 71] = '1';
                    break;
                case "15":
                    defaultPermit[Integer.valueOf(temp[1]) + 75] = '1';
                    break;
                case "16":
                    defaultPermit[Integer.valueOf(temp[1]) + 79] = '1';
                    break;
                case "17":
                    defaultPermit[Integer.valueOf(temp[1]) + 83] = '1';
                    break;
                case "18":
                    defaultPermit[Integer.valueOf(temp[1]) + 87] = '1';
                    break;
                case "19":
                    defaultPermit[Integer.valueOf(temp[1]) + 91] = '1';
                    break;
                case "20":
                    defaultPermit[Integer.valueOf(temp[1]) + 95] = '1';
                    break;
                case "21":
                    defaultPermit[Integer.valueOf(temp[1]) + 99] = '1';
                    break;
                case "22":
                    defaultPermit[Integer.valueOf(temp[1]) + 101] = '1';
                    break;
                case "23":
                    defaultPermit[Integer.valueOf(temp[1]) + 102] = '1';
                    break;
                case "24":
                    defaultPermit[Integer.valueOf(temp[1]) + 106] = '1';
                    break;
                case "25":
                    defaultPermit[Integer.valueOf(temp[1]) + 107] = '1';
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
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (permit.charAt(i*6 + j) == '1') {
                    permitFinal.add(i + "-" + j);
                }
            }
        }

        for (int i = 42; i < 47; i++) {
            if (permit.charAt(i)  == '1') {
                permitFinal.add(7 + "-" + String.valueOf(i - 42));
            }
        }

        for (int i = 47; i < 51; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(8 + "-" + String.valueOf(i-47));
            }
        }

        for (int i = 51; i < 55; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(9 + "-" + String.valueOf(i-51));
            }
        }

        for (int i = 55; i < 59; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(10 + "-" + String.valueOf(i-55));
            }
        }

        for (int i = 59; i < 63; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(11 + "-" + String.valueOf(i-59));
            }
        }

        for (int i = 63; i < 67; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(12 + "-" + String.valueOf(i-63));
            }
        }

        for (int i = 67; i < 71; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(13 + "-" + String.valueOf(i-67));
            }
        }

        for (int i = 71; i < 75; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(14 + "-" + String.valueOf(i-71));
            }
        }

        for (int i = 75; i < 79; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(15 + "-" + String.valueOf(i-75));
            }
        }

        for (int i = 79; i < 83; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(16 + "-" + String.valueOf(i-79));
            }
        }



        for (int i = 83; i < 87; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(17 + "-" + String.valueOf(i-83));
            }
        }

        for (int i = 87; i < 91; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(18 + "-" + String.valueOf(i-87));
            }
        }

        for (int i = 91; i < 95; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(19 + "-" + String.valueOf(i-91));
            }
        }

        for (int i = 95; i < 99; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(20 + "-" + String.valueOf(i-95));
            }
        }

        for (int i = 99; i < 101; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(21 + "-" + String.valueOf(i-99));
            }
        }

        for (int i = 101; i < 102; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(22 + "-" + String.valueOf(i-101));
            }
        }

        for (int i = 102; i < 106; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(23 + "-" + String.valueOf(i-102));
            }
        }

        for (int i = 106; i < 107; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(24 + "-" + String.valueOf(i-106));
            }
        }

        for (int i = 107; i < 108; i++) {
            if(permit.charAt(i) == '1') {
                permitFinal.add(25 + "-" + String.valueOf(i-107));
            }
        }
        return permitFinal;
    }
}

package com.deep.api.Utils;

/**
 * use authentication of the HttpRequest, judge and get userId
 */
public class TokenAnalysis {
    /**
     * judge the User is factory or agent or general user
     * @param authentication authentication of a HttpRequest
     * @return authentication 0 is factory, 1 is agent, 2 is general user
     */
    public static String getFlag(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split(":");
        if (param.length != 4) {
            return null;
        }
        return param[3];
    }

    /**
     * get user's id (the primary key)
     * @param authentication authentication of a HttpRequest
     * @return the user's id (the primary key)
     */
    public static Long getUserId(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split(":");
        if (param.length != 4) {
            return null;
        }
        return StringToLongUtil.stringToLong(param[0]);
    }
}

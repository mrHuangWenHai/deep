package com.deep.infra.selective;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public class NutritionAnotherPlanSelective {
    private String doCommonSql(List<Long> lists, String factoryName, Byte isPassCheck,
                               String startTime, String endTime, String earTag) {
        String commonSql = "";

        // 将lists集合转换为字符串，进行多个羊场的处理
        String factoryLists = lists.toString();
        factoryLists = factoryLists.substring(1, factoryLists.length()-1);
        String factoryOptions = "factory_num in (" + factoryLists + ")";

        String factoryNameOptions = " factory_name = \"" + factoryName + "\"";
        String passOptions = " ispass_check = " + isPassCheck;
        String timeOptions = " nutrition_t between \"" + startTime + "\" and \"" +endTime + "\"";
        String earTags = " ear_tag_file like '" + earTag + "'";

        commonSql += factoryOptions;
        if (!"".equals(factoryName)) {
            commonSql += " and" + factoryNameOptions;
        }
        if (isPassCheck != -1) {
            commonSql += " and" + passOptions;
        }
        if (!"".equals(startTime) || !"".equals(endTime)) {
            commonSql += " and" + timeOptions;
        }
        if (!"".equals(earTag)) {
            commonSql += " and" + earTags;
        }
        return commonSql;
    }

    private String doCountSql() {
        return "select count(*) from nutrition_plan_another where ";
    }

    private String doSelectSql() {
        return "select * from nutrition_plan_another where ";
    }

    public String countRecords(@Param("lists") List<Long> lists,
                               @Param("factoryName") String factoryName,
                               @Param("isPassCheck") Byte isPassCheck,
                               @Param("startTime") String startTime,
                               @Param("endTime") String endTime,
                               @Param("earTag") String earTag) {
        System.out.println("count sql = " + doCountSql() + doCommonSql(lists, factoryName, isPassCheck, startTime, endTime, earTag));
        return doCountSql() + doCommonSql(lists, factoryName, isPassCheck, startTime, endTime, earTag);
    }

    public String selectRecords(@Param("start") Long start,
                                @Param("lists") List<Long> lists,
                                @Param("factoryName") String factoryName,
                                @Param("isPassCheck") Byte isPassCheck,
                                @Param("startTime") String startTime,
                                @Param("endTime") String endTime,
                                @Param("earTag") String earTag) {
        String options = "";
        options = options + doSelectSql() + doCommonSql(lists, factoryName, isPassCheck, startTime, endTime, earTag);
        options = options + " order by gmt_modified desc";
        options = options + " limit " + start + ", 10";
        System.out.println("nutrition select sql " + options);
        return options;
    }
}

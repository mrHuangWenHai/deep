package com.deep.infra.selective;

import com.deep.api.Utils.TimeUtil;

import java.util.List;

public class BreedingPlanSelective {
    public String countBreedingPlanAnother(List<Long> lists, Byte pass, String factoryName, String startTime, String endTime, String earTag) {
        String options = "select count(*) from breeding_plan_another where ";
        String factoryNameOptions = " factory_name = \"" + factoryName + "\"";
//        String timeOptions = " breeding_time between \"" + TimeUtil.translateToString(TimeUtil.getBeforeDay(TimeUtil.TranslateToDate(startTime))) + "\" and \"" + TimeUtil.translateToString(TimeUtil.getNextDay(TimeUtil.TranslateToDate(endTime))) + "\"";
        String timeOptions = " breeding_time between \"" + startTime + "\" and \"" +endTime + "\"";
        String earTags = " ram_sheep_trademark = \"" + earTag + "\" or " + "ewe_sheep_trademark = \"" + earTag + "\"";

        // 将lists集合转换为字符串
        String allDirectFactories = lists.toString();
        allDirectFactories = allDirectFactories.substring(1, allDirectFactories.length()-1);

        String factoryOptions = " factory_number in (" + allDirectFactories + ")";
        options += factoryOptions;
        String passOptions = " is_pass_check = " + pass;
        if (!"".equals(factoryName)) {
            options += " and" + factoryNameOptions;
        }
        if (!"".equals(startTime) || !"".equals(endTime)) {
            options += " and" + timeOptions;
        }
        if (!"".equals(earTag)) {
            options += " and" + earTags;
        }
        if (pass != -1) {
            options += " and" + passOptions;
        }
        return options;
    }

    public String findAllSelective(List<Long> lists, Integer page, Byte size, Byte pass, String factoryName, String startTime, String endTime, String earTag) {
        String options = "select * from breeding_plan_another where";
        String factoryNameOptions = " factory_name = \"" + factoryName + "\"";
//        String timeOptions = " breeding_time between " + TimeUtil.getBeforeDay(TimeUtil.TranslateToDate(startTime)) + " and " + TimeUtil.getNextDay(TimeUtil.TranslateToDate(endTime));
//        String timeOptions = " breeding_time between \"" + TimeUtil.translateToString(TimeUtil.getBeforeDay(TimeUtil.TranslateToDate(startTime))) + "\" and \"" + TimeUtil.translateToString(TimeUtil.getNextDay(TimeUtil.TranslateToDate(endTime))) + "\"";
        String timeOptions = " breeding_time between \"" + startTime + "\" and \"" +endTime + "\"";
        String earTags = " ram_sheep_trademark = \"" + earTag + "\" or " + "ewe_sheep_trademark = \"" + earTag + "\"";

        // 将lists集合转换为字符串
        String allDirectFactories = lists.toString();
        allDirectFactories = allDirectFactories.substring(1, allDirectFactories.length()-1);

        String factoryOptions = " factory_number in (" + allDirectFactories + ")";
        String pageSize = " limit " + page*size + ", " + size;
        String passOptions = " is_pass_check = " + pass;
        options += factoryOptions;

        if (!"".equals(factoryName)) {
            options += " and" + factoryNameOptions;
        }
        if (!"".equals(startTime) || !"".equals(endTime)) {
            options += " and" + timeOptions;
        }
        if (!"".equals(earTag)) {
            options += " and" + earTags;
        }
        if (pass != -1) {
            options += " and" + passOptions;
        }
        options += " order by gmt_modify desc";
        options +=pageSize;
        return options;
    }
}

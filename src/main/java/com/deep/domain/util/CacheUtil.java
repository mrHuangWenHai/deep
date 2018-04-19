package com.deep.domain.util;

import com.deep.domain.model.TypeBriefModel;
import com.deep.domain.service.TypeBriefService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 一级缓存 将系谱档案中品名-特征缓存
 * create by zhongrui on 18-4-19.
 */
@Component
@Order(value = 1)
public class CacheUtil implements CommandLineRunner{

    @Resource
    private TypeBriefService typeBriefService;

    @Override
    public void run(String... args) {
        List<TypeBriefModel> list = this.typeBriefService.getAll();
        Integer i = 0;
        for (TypeBriefModel model : list){
            JedisUtil.setCertainKeyValue(model.getType(),model.getBrief());
            JedisUtil.setCertainKeyValue("type_"+i, model.getType());
            //System.out.println("type:"+JedisUtil.getCertainKeyValue("type_"+i));
            i++;
        }
        System.out.println("cache save");
    }
}

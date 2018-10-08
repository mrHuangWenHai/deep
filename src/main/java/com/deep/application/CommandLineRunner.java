package com.deep.application;

import com.deep.api.Utils.ClientDetailUtil;
import com.deep.domain.service.management_level.ClientDetailService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    @Resource
    private ClientDetailService clientDetailService;

    @Override
    public void run(String... args) throws Exception {
//        clientDetailService.clientDetailLoading();
//
//        if (ClientDetailUtil.isLoad()) {
//          System.out.println("信息加载完毕！");
//        } else {
//          System.out.println("信息加载失败！");
//        }
    }
}

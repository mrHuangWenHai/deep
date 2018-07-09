package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;

import com.deep.api.response.Response;
import com.deep.domain.model.EnvironmentTraceModel;
import com.deep.domain.model.EnvironmentTraceReturnModel;
import com.deep.domain.service.EnvironmentTraceService;
import com.deep.domain.util.BackupUtil;

import com.deep.domain.util.JudgeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * create by zhongrui on 18-4-16.
 */
@Configuration
@EnableScheduling
@RestController
@RequestMapping(value = "/envir")
public class EnvironmentDBResource {

    private final Logger logger = LoggerFactory.getLogger(EnvironmentDBResource.class);

    @Resource
    private EnvironmentTraceService environmentTraceService;

    /**
     * 查询按照工厂号最新数据
     * @param factoryNum 工厂号
     * @return 最新数据
     */
    @Permit(authorities = {"front_view_organic_environment", "rear_view_organic_environment"})
    @RequestMapping(value = "/get/{factoryNum}",method = RequestMethod.GET)
    public Response getLatest(@PathVariable(value = "factoryNum")BigInteger factoryNum){
        logger.info("invoke getLatest {}" , factoryNum);
        EnvironmentTraceModel environmentTraceModel = this.environmentTraceService.getEnvironmentTraceModelLatestByFactoryNum(factoryNum);
        EnvironmentTraceReturnModel environmentTraceReturnModel = new EnvironmentTraceReturnModel(environmentTraceModel);
        return JudgeUtil.JudgeFind(environmentTraceReturnModel , environmentTraceModel );
    }


    /**
     * 定时任务
     * 5天备份一次
     * 备份文件名形式:"../DatabaseStatics/EnvironmentTrace/env_trace_20180501120220"
     * @throws InterruptedException 冲突异常
     */
    @Scheduled(cron = "0 0 0 0/5 * ?")
    public void backUpMysql() throws InterruptedException{
        String hostIP = "180.76.180.95";
        String username = "root";
        String password = "hzau2018";
        String database = "demo1";
        String savePath = "../DatabaseStatics/";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhMMss");
        if (BackupUtil.sqlBackup(savePath+"EnvironmentTrace/",hostIP,username,password,database,"env_trace_",simpleDateFormat.format(new Timestamp(System.currentTimeMillis())))){
//删除数据
//            if (co2DataMapper.deleteCO2Data() == 1
//                    && humDataMapper.deleteHumData() == 1
//                    && tempDataMapper.deleteTempData() == 1
//                    && nh3DataMapper.deleteNH3Data() == 1){
//                System.out.println("Success");
//            }
            System.out.println("Success");

        }else {
            System.out.println("Fail");
        }
    }

    @Permit(authorities = "download_database")
    @GetMapping("/download")
    public Response download(){
        return null;
    }
}

package com.deep.api.resource;

import com.deep.api.Utils.SqlSessionFactoryUtils;
import com.deep.api.response.Response;
import com.deep.domain.model.*;
import com.deep.domain.service.EnvironmentService;
import com.deep.domain.util.BackupUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.infra.persistence.sql.mapper.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private static SqlSession session = SqlSessionFactoryUtils.openSqlSession();
    private static CO2DataMapper co2DataMapper = session.getMapper(CO2DataMapper.class);
    private static HumDataMapper humDataMapper = session.getMapper(HumDataMapper.class);
    private static NH3DataMapper nh3DataMapper = session.getMapper(NH3DataMapper.class);
    private static TempDataMapper tempDataMapper = session.getMapper(TempDataMapper.class);

    /* 获取最新数据 包装 */
    @Resource
    private EnvironmentService environmentService;
    /**
     * 从another数据库中采集最新数据 包装
     * 按需求 主动/被动 提交给前端
     * 未完成:多个厂的数据 怎么实现最新数据的筛选(多表/单表)
     * @return JudgeUtil.JudgeSuccess("data",environmentModel);
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response getLatest(){
        //System.out.println("in this interface");
        logger.info("invoke getLatest {}");
        EnvironmentModel environmentModel = new EnvironmentModel();
        CO2DataModel co2DataModel;
        HumDataModel humDataModel;
        NH3DataModel nh3DataModel;
        TempDataModel tempDataModel;
        try {
            co2DataModel = co2DataMapper.getCO2DataLatest();
            humDataModel = humDataMapper.getHumDataLatest();
            nh3DataModel = nh3DataMapper.getNH3DataLatest();
            tempDataModel = tempDataMapper.getTempDataLatest();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
            environmentModel.setTime(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
            environmentModel.setFactoryNum(new BigInteger("1"));
            environmentModel.setInTemp(tempDataModel.getTemp());
            environmentModel.setInHum(humDataModel.getHum());
            environmentModel.setPm(2);
            environmentModel.setWaterPH(4);
            environmentModel.setWaterTemp(1);
            environmentModel.setSoilPH(1);
            environmentModel.setSoilTemp(1);
            environmentModel.setSoilHum(1);
            environmentModel.setNh3(nh3DataModel.getNh3());

        }finally {
            if (session != null){
                session.close();
            }
        }
        this.environmentService.setEnvironmentModel(environmentModel);
        return JudgeUtil.JudgeSuccess("data",environmentModel);
    }



    /**
     * 定时任务
     * 备份another数据库中co2_data hum_data temp_data nh3_data
     * @throws InterruptedException 冲突异常
     */
    @Scheduled(cron = "0 0 0 0/5 * ?")
    public void backUpMysql() throws InterruptedException{
        String hostIP = "193.112.112.149";
        String username = "root";
        String password = "hzau2018";
        String database = "demo1";
        String savePath = "../DatabaseStatics/";
        if (BackupUtil.sqlBackup(savePath+"co2/",hostIP,username,password,database,"co2_data","_co2")
                && BackupUtil.sqlBackup(savePath+"hum/",hostIP,username,password,database,"hum_data","_hum")
                && BackupUtil.sqlBackup(savePath+"temp/",hostIP,username,password,database,"temp_data","_temp")
                && BackupUtil.sqlBackup(savePath+"nh3/",hostIP,username,password,database,"nh3_data","_nh3")){
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

}

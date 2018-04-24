package com.deep.api.resource;

import com.deep.api.Utils.SqlSessionFactoryUtils;
import com.deep.api.response.Response;
import com.deep.domain.model.*;
import com.deep.domain.service.EnvironmentService;
import com.deep.domain.util.BackupUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.infra.persistence.sql.mapper.*;
import org.apache.ibatis.session.SqlSession;
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

    private static String hostIP = "193.112.112.149";
    private static String username = "root";
    private static String password = "hzau2018";
    private static String database = "demo1";
    private static String savePath = "../DatabaseStatics/";
    //获取最新数据 包装
    @Resource
    private EnvironmentService environmentService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response getLatest(){
        System.out.println("in this interface");
        SqlSession session = null;

        EnvironmentModel environmentModel = new EnvironmentModel();
        CO2DataModel co2DataModel;
        HumDataModel humDataModel;
        NH3DataModel nh3DataModel;
        TempDataModel tempDataModel;
        try {
            session = SqlSessionFactoryUtils.openSqlSession();
            CO2DataMapper co2DataMapper = session.getMapper(CO2DataMapper.class);
            HumDataMapper humDataMapper = session.getMapper(HumDataMapper.class);
            NH3DataMapper nh3DataMapper = session.getMapper(NH3DataMapper.class);
            TempDataMapper tempDataMapper = session.getMapper(TempDataMapper.class);
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
     * 备份another数据库中co2_data hum_data temp_data nh3_data
     * @throws InterruptedException
     */

    @Scheduled(cron = "0/5 * * * * ?")
    public void backUpMysql() throws InterruptedException {

        if (BackupUtil.sqlBackup(savePath+"co2/",hostIP,username,password,database,"co2_data","_co2")
                && BackupUtil.sqlBackup(savePath+"hum/",hostIP,username,password,database,"hum_data","_hum")
                && BackupUtil.sqlBackup(savePath+"temp/",hostIP,username,password,database,"temp_data","_temp")
                && BackupUtil.sqlBackup(savePath+"nh3/",hostIP,username,password,database,"nh3_data","_nh3")){
            //删除数据
//            if (this.humDataService.deleteHumData() == 1
//                    ){
//                System.out.println("Success");
//            }
            System.out.println("Success");

        }else {
            System.out.println("Fail");
        }
    }

}

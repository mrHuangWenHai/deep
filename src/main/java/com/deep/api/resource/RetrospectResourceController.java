package com.deep.api.resource;

import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.model.GenealogicalFilesTransModel;
import com.deep.domain.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用于追溯系统的查询
 * 用到六大生产模块的数据
 * 单独操作(如系谱档案/系谱档案转手) 通过耳牌号追溯
 * 可给予商标耳牌查询入口以及免疫耳牌查询入口
 * ！！由于转手时 耳牌号不会发生转变 所以通过耳牌可以查到所有信息
 *
 * 批量操作(如消毒档案/驱虫档案) 通过工厂号查询
 * ！！通过工厂号查询时 由于会存在工厂号更改的情况
 * 1 通过系谱档案查出初始耳牌以及建表时间/转厂时间
 * 2 通过系谱档案转手查出初始耳牌以及建表时间/转厂时间
 * 3 查询批量操作表格中gmt_create在系谱档案转手表格中gmt_create和gmt_trans之间的数据
 * 4 在查询的结果中 再在耳牌附件中查询 如果有该商标耳牌号则保留
 *
 * create by zhongrui on 18-3-25.
 */

@Controller
public class RetrospectResourceController {
    //通过耳牌号查询
    @Resource
    private GenealogicalFilesService genealogicalFilesService;
    @Resource
    private GenealogicalFilesTransService genealogicalFilesTransService;
    //通过工厂号 时间间隔查询后
    //再通过耳牌筛选
    @Resource
    private DisinfectFilesService disinfectFilesService;
    @Resource
    private ImmunePlanService immunePlanService;
    @Resource
    private RepellentPlanService repellentPlanService;

    @ResponseBody
    @RequestMapping(value = "/retro")
    public String Retro(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-MM-ss");
        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelBytradeMarkEartag("201102023456789");
        List<GenealogicalFilesTransModel> genealogicalFilesTransModels = genealogicalFilesTransService.getGenealogicalFilesTransModelBytrademarkEartag(genealogicalFilesModel.getTradeMarkEartag());

        List<DisinfectFilesModel> disinfectFilesModels;
        for( int i = 0; i < genealogicalFilesTransModels.size(); i++){
            //如果gmtTrans为空 说明该羊仍在该厂内
            //时间间隔由gmtCreate到现在
            if(genealogicalFilesTransModels.get(i).getGmtTrans() == null){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                disinfectFilesModels = disinfectFilesService.getDisinfectFilesModelByfactoryNumAnddisinfectTime(
                        new BigInteger(genealogicalFilesTransModels.get(i).getBeforeOwnerFactory()),
                        simpleDateFormat.format(genealogicalFilesTransModels.get(i).getGmtCreate()),simpleDateFormat.format(timestamp));
            }
        }

        return null;
    }


}

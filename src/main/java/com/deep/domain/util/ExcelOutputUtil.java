package com.deep.domain.util;

import com.deep.api.Utils.ExcelData;
import com.deep.api.Utils.ExportExcelUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.*;
import com.deep.domain.service.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于导出
 * create by zhongrui on 18-5-4.
 */
@Component
public class ExcelOutputUtil {
//    agent_factory
//    @Resource
//    private
    @Resource
    private BreedingPlanService breedingPlanService;
    @Resource
    private DiagnosisPlanService diagnosisPlanService;
    @Resource
    private DisinfectFilesService disinfectFilesService;
    @Resource
    private EnvironmentTraceService environmentTraceService;
//    factory_manage
//    @Resource
//    private
    @Resource
    private GenealogicalFilesService genealogicalFilesService;
    @Resource
    private GenealogicalFilesTransService genealogicalFilesTransService;
    @Resource
    private ImmunePlanService immunePlanService;
    @Resource
    private NoticePlanService noticePlanService;
    @Resource
    private NutritionPlanService nutritionPlanService;
    @Resource
    private OperationFileService operationFileService;
    @Resource
    private RepellentPlanService repellentPlanService;
    @Resource
    private TypeBriefService typeBriefService;
    @Resource
    private  UserService userService;

    private static ExcelOutputUtil excelOutputUtil;

    @PostConstruct
    public void init() {
        excelOutputUtil = this;
    }

    //role_user   permit_manage  message  talk
    // talk_account  useful_expressions 不能/不用导出
    private static Map<String, String> map = new HashMap<>();
    static {
        map.put("总公司及代理管理表", "1");
        map.put("育种实施档案表", "2");
        map.put("症疗档案表", "3");
        map.put("消毒档案表", "4");
        map.put("环境追溯表", "5");
        map.put("工厂管理表", "6");
        map.put("系谱档案表", "7");
        map.put("系谱档案转移表", "8");
        map.put("免疫档案表", "9");
        map.put("方案发布表","10");
        map.put("营养档案表", "11");
        map.put("动物福利档案表", "12");
        map.put("驱虫档案表","13");
        map.put("羊羔种类-特征表", "14");
        map.put("用户管理表", "15");
    }

    /**
     * 为前端返回表格名称
     * @return tableName
     */
    public static Response typeReturn(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("list", map);
        return Responses.successResponse(data);
    }


    /**
     * 按照Excel格式
     * 将数据库数据输出
     * @param response HttpServletResponse
     * @param factoryNum 工厂号
     * @param tableName 表名
     * @return 下载结果
     * @throws Exception 异常
     */
    public Response excelOutput(HttpServletResponse response, String factoryNum, String tableName) throws Exception{
        ExcelData data = new ExcelData();
        switch(tableName){
            case "1":{
                data.setName("agent_factory");
                //List<Ag>
            }
            case "2":{
                data.setName("breeding_plan");
                //List<BreedingPlanModel> list = breedingPlanService.
            }
            case "3":{
                data.setName("diagnosis_plan");
                //List<DiagnosisPlanModel> list = diagnosisPlanService.
            }
            case "4":{
                data.setName("disinfect_files");
                List<DisinfectFilesModel> list = excelOutputUtil.disinfectFilesService.getDisinfectFilesModelByFactoryNum(new BigInteger(factoryNum));
                DisinfectFilesModel disinfectFilesModel;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (DisinfectFilesModel disinfectFilesModel1 : list){
                    List<Object> row = new ArrayList<>();
                    disinfectFilesModel = disinfectFilesModel1;
                    for (Field field : disinfectFilesModel.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(disinfectFilesModel));
                        if (count == 0) {
                            titles.add(field.getName());
                        }
                    }
                    count++;
                    rows.add(row);
                }
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"消毒档案表格.xlsx",data);
                return Responses.successResponse();
            }
            case "5":{
                data.setName("env_trace");
                List<EnvironmentTraceModel> list = excelOutputUtil.environmentTraceService.getEnvironmentTraceModelByFactoryNum(new BigInteger(factoryNum));
                EnvironmentTraceModel environmentTraceModel;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (EnvironmentTraceModel environmentTraceModel1 : list){
                    List<Object> row = new ArrayList<>();
                    environmentTraceModel = environmentTraceModel1;
                    for (Field field : environmentTraceModel.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(environmentTraceModel));
                        if (count == 0) {
                            titles.add(field.getName());
                        }
                    }
                    count ++;
                    rows.add(row);
                }
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"环境追溯表.xlsx",data);
                return Responses.successResponse();
            }
            case "6":{

            }
            case "7":{
                data.setName("genealogical_files");
                List<GenealogicalFilesModel> list = excelOutputUtil.genealogicalFilesService.getGenealogicalFilesModelByFactoryNum(new Long(factoryNum));
                GenealogicalFilesModel genealogicalFilesModel;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (GenealogicalFilesModel genealogicalFilesModel1 : list){
                    List<Object> row = new ArrayList<>();
                    genealogicalFilesModel = genealogicalFilesModel1;
                    for (Field field : genealogicalFilesModel.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(genealogicalFilesModel));
                        if (count == 0){
                            titles.add(field.getName());
                        }
                    }
                    count ++ ;
                    rows.add(row);
                }
                System.out.println("running here");
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"系谱档案表.xlsx",data);
                return Responses.successResponse();
            }
            case "8":{
                //
            }
            case "9":{
                data.setName("immune_plan");
                List<ImmunePlanModel> list = excelOutputUtil.immunePlanService.getImmunePlanModelByFactoryNum(new BigInteger(factoryNum));
                ImmunePlanModel immunePlanModel;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (ImmunePlanModel immunePlanModel1 : list){
                    List<Object> row = new ArrayList<>();
                    immunePlanModel = immunePlanModel1;
                    for (Field field : immunePlanModel.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(immunePlanModel));
                        if (count == 0) {
                            titles.add(field.getName());
                        }
                    }
                    count ++;
                    rows.add(row);
                }
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"免疫档案表.xlsx",data);
                return Responses.successResponse();
            }
            case "10":{
                //
            }
            case "11":{
                //
            }
            case "12":{
                data.setName("operation_file");
                List<OperationFile> list = excelOutputUtil.operationFileService.getOperationFileByFactoryNum(new BigInteger(factoryNum));
                OperationFile operationFile;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (OperationFile operationFile1 : list){
                    List<Object> row = new ArrayList<>();
                    operationFile = operationFile1;
                    for (Field field : operationFile.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(operationFile));
                        if (count == 0) {
                            titles.add(field.getName());
                        }
                    }
                    count ++;
                    rows.add(row);
                }
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"动物福利档案表.xlsx",data);
                return Responses.successResponse();

            }
            case "13":{
                data.setName("repellent_plan");
                List<RepellentPlanModel> list = excelOutputUtil.repellentPlanService.getRepellentPlanModelByFactoryNum(new BigInteger(factoryNum));
                RepellentPlanModel repellentPlanModel;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (RepellentPlanModel repellentPlanModel1 : list){
                    List<Object> row = new ArrayList<>();
                    repellentPlanModel = repellentPlanModel1;
                    for (Field field : repellentPlanModel.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(repellentPlanModel));
                        if (count == 0) {
                            titles.add(field.getName());
                        }
                    }
                    count ++;
                    rows.add(row);
                }
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"驱虫档案表.xlsx",data);
                return Responses.successResponse();
            }
            case "14":{
                data.setName("type_brief");
                List<TypeBriefModel> list = excelOutputUtil.typeBriefService.getAllType();
                TypeBriefModel typeBriefModel;
                List<List<Object>> rows = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int count = 0;
                for (TypeBriefModel typeBriefModel1 : list){
                    List<Object> row = new ArrayList<>();
                    typeBriefModel = typeBriefModel1;
                    for (Field field : typeBriefModel.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        row.add(field.get(typeBriefModel));
                        if (count == 0) {
                            titles.add(field.getName());
                        }
                    }
                    count ++;
                    rows.add(row);
                }
                data.setTitles(titles);
                data.setRows(rows);
                ExportExcelUtil.exportExcel(response,"羊羔种类-特征表.xlsx",data);
                return Responses.successResponse();
            }
            case "15":{
                //
            }
            default:
                return Responses.errorResponse("Error");
        }

    }

}

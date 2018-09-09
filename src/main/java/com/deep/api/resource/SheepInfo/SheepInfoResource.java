package com.deep.api.resource.SheepInfo;

import com.deep.api.Utils.FileUtil;
import com.deep.api.Utils.ReadExcel;
import com.deep.api.Utils.TimeUtil;
import com.deep.api.request.NoBuildingRequest;
import com.deep.api.request.SheepUpdateRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.constant.FileTypeEnum;
import com.deep.domain.model.sheepInfo.SheepInformationModel;
import com.deep.domain.service.SheepInfo.BuildingFactoryService;
import com.deep.domain.service.SheepInfo.SheepInformationService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By LeeBoom On 2018/7/26 19:02
 */
@RestController
@RequestMapping(value = "/s")
public class SheepInfoResource {
    private static Logger logger = LoggerFactory.getLogger(SheepInfoResource.class);
    @Resource
    private SheepInformationService sheepInformationService;
    @Resource
    private BuildingFactoryService buildingFactoryService;

//    @PostMapping(value = "/u")
//    public Response updateLamb2Sheep(@RequestBody SheepInfo sheepInfo) {
//        int id = sheepInfoService.updateSheepInfoById(sheepInfo);
//        Response response = Responses.successResponse();
//        HashMap<String,Object> data = new HashMap<>();
//        data.put("sheepId",id);
//        response.setData(data);
//        return response;
//    }

    /**
     * 添加一批羊
     * @param factory 羊长编号
     * @param file 文件
     * @param request 请求
     * @return 返回的数据
     */
    @PostMapping(value = "/sps/{factory}")
    public Response addSheep(@PathVariable("factory") Long factory,
                                 @RequestParam("file") MultipartFile file,
                              HttpServletRequest request) {
        try {
            String Header = FileUtil.getFileHeader(file);
            if (!Header.equals(FileTypeEnum.XLS.value)&&!Header.equals(FileTypeEnum.XLSX.value)) {
                throw new Exception("文件格式错误！");
            }
            String filepath = request.getSession().getServletContext().getContextPath() + "../process/";

            String dateString = TimeUtil.translateToString(new Date());
            String fileDate = dateString.replaceAll(":", "-");
            String fileName = fileDate+'-'+file.getOriginalFilename();
            String fileAddress = filepath + fileName;

            File dir = new File(filepath);
            if(!dir.exists()){
                dir.mkdir();
            }
            File tempFile = new File(fileAddress);

            FileUtils.copyInputStreamToFile(file.getInputStream(),tempFile);

            //多线程不安全
            List<List<Object>> excelList = ReadExcel.readExcel(tempFile);

            List<Object> list ;
            int count = 0;
            for(int i =1;i < excelList.size();i++) {
                System.out.println("excelList = " + excelList.size());
                System.out.println("list = " + excelList.get(i).size());
                list = excelList.get(i);
                SheepInformationModel model = new SheepInformationModel();
                for (int j = 0; j< list.size();j++) {
                    if(j == 0) {
                        System.out.println(list.get(j).toString());
                        model.setTrademarkEarTag(list.get(j).toString());
                    } else if(j == 1) {
                        System.out.println(list.get(j).toString());
                        model.setImmuneEarTag(list.get(j).toString());
                    } else if(j == 2) {
                        System.out.println(list.get(j).toString());
                        model.setType(list.get(j).toString());
                    }

                }
                model.setFactory(factory);
                sheepInformationService.insertSheepInformation(model);
                count++;
            }
            if(tempFile.delete()) {
                logger.info(tempFile.getName() + " is deleted!");
            }else {
                logger.error("Delete operation is failed.");
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("number", count);
            response.setData(data);
            return response;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Responses.errorResponse(e.getMessage());
        }
    }

    /**
     * 添加一只羊
     * @param model 羊只的信息
     * @return 返回信息
     */
    @PostMapping(value = "/sp")
    public Response addSheep(@RequestBody SheepInformationModel model) {
        // 根据栋号栏号找到id
        Long buildingAndColId = buildingFactoryService.findIdByBuildingAndCol(model.getFactory(), model.getBuilding(), model.getCol());
        model.setBuildingColumn(buildingAndColId);
        Long insert = sheepInformationService.insertSheepInformation(model);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("add", insert);
        response.setData(data);
        return response;
    }

    /**
     * 获取没有分配栏栋的羊
     * @param id 羊场号
     * @return 返回羊只列表
     */
    @GetMapping(value = "/n/{id}")
    public Response getNoBuilding(@PathVariable("id") Long id) {
        Response response = Responses.successResponse();
        Map<String, Object> data = new HashMap<>();
        data.put("data", sheepInformationService.getNoBuildingColumn(id));
        response.setData(data);
        return response;
    }

    /**
     * 给一群羊分配栏栋
     * @param request 前端请求
     * @return 返回结果
     */
    @PostMapping(value = "/s")
    public Response setBuildings(@RequestBody @Valid NoBuildingRequest request, BindingResult bindingResult) {
        System.out.println(request.toString());
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("分配栏栋失败, 验证错误!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long buildingAndColId = buildingFactoryService.findIdByBuildingAndCol(request.getFactory(), request.getBuilding(), request.getCol());
        sheepInformationService.setNoBuildingColumn(request.getSheeps(), buildingAndColId, request.getFactory());
        return Responses.successResponse();
    }

    @PostMapping(value = "/u")
    public Response updateSheepInfo(@RequestBody @Valid SheepUpdateRequest request, BindingResult bindingResult) {
        System.out.println(request.toString());
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("修改羊只信息失败！");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long buildingAndColId = sheepInformationService.updateSheepInfo(request);
        if (buildingAndColId > 0) return Responses.successResponse();
        else return Responses.errorResponse("修改羊只信息失败");
    }
}



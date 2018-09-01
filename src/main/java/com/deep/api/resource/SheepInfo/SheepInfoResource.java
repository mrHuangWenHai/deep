package com.deep.api.resource.SheepInfo;

import com.deep.api.Utils.FileUtil;
import com.deep.api.Utils.ReadExcel;
import com.deep.api.Utils.TimeUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.constant.FileTypeEnum;
import com.deep.domain.model.SheepInfo;
import com.deep.domain.model.sheepInfo.SheepInformationModel;
import com.deep.domain.service.SheepInfo.SheepInformationService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/26 19:02
 */
@RestController
@RequestMapping(value = "/s")
public class SheepInfoResource {
    private static Logger logger = LoggerFactory.getLogger(SheepInfoResource.class);
    @Resource
    private SheepInformationService sheepInformationService;

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
                list = excelList.get(i);
                SheepInformationModel model = new SheepInformationModel();
                for (int j = 0; j< list.size();j++) {
                    if(j == 0) {
                        model.setTrademarkEarTag(list.get(j).toString());
                    } else if(j == 1) {
                        model.setImmuneEarTag(list.get(j).toString());
                    } else if(j == 2) {
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
        Long insert = sheepInformationService.insertSheepInformation(model);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("add", insert);
        response.setData(data);
        return response;
    }
}



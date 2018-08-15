package com.deep.api.resource;

import com.deep.api.Utils.FileUtil;
import com.deep.api.Utils.ReadExcel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.constant.FileTypeEnum;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.SheepInfo;
import com.deep.domain.service.SheepInfoService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/26 19:02
 */
@RestController
@RequestMapping(value = "/sheep")
public class SheepInfoResource {
    private static Logger logger = LoggerFactory.getLogger(SheepInfoResource.class);
    @Resource
    private
    SheepInfoService sheepInfoService;

    @PostMapping(value = "/update")
    public Response updateLamb2Sheep(@RequestBody SheepInfo sheepInfo)
    {
        int id = sheepInfoService.updateSheepInfoById(sheepInfo);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("sheepId",id);
        response.setData(data);
        return response;
    }
    @PostMapping(value = "/insertsheeps")
    public Response addSheeps(FactoryModel factoryModel,
                              @RequestParam("file") MultipartFile file,
                              HttpServletRequest request)
    {
        try {
            String Header = FileUtil.getFileHeader(file);
            if (!Header.equals(FileTypeEnum.XLS.value)&&!Header.equals(FileTypeEnum.XLSX.value)) {
                throw new Exception("文件格式错误！");
            }
            String filepath = request.getSession().getServletContext().getContextPath() + "../process/";
            Date udate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(udate);
            String fileDate = dateString.replaceAll(":", "-");
            String fileName = fileDate+'-'+file.getOriginalFilename();
            String fileAddress = filepath + fileName;
            File dir = new File(filepath);
            if(!dir.exists()){
                dir.mkdir();
            }
            File tempFile;
            tempFile = new File(fileAddress);
            FileUtils.copyInputStreamToFile(file.getInputStream(),tempFile);
            //多线程不安全
            List<List<Object>> excelList = ReadExcel.readExcel(tempFile);
            List<Object> list ;
            int count = 0;
            for(int i =1;i < excelList.size();i++)
            {
                list = excelList.get(i);
                SheepInfo sheepInfo = new SheepInfo();
                for (int j = 0; j< list.size();j++)
                {

                    if(j == 0)
                    {
                        sheepInfo.setBrand(list.get(j).toString());
                    }
                    else if(j == 1)
                    {
                        sheepInfo.setVaccine(list.get(j).toString());
                    }
                    else if(j == 2)
                    {
                        sheepInfo.setType(list.get(j).toString());
                    }

                }
                sheepInfo.setFactory(factoryModel.getId().intValue());
                sheepInfo.setDate(dateString);
                sheepInfoService.insertRecord(sheepInfo);
                count ++;
            }

            if(tempFile.delete()) {
                logger.info(tempFile.getName() + " is deleted!");
            }else {
                logger.error("Delete operation is failed.");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("addSheeps", count);
            response.setData(data);
            return response;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Responses.errorResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/insertsheep")
    public Response addsheep( @RequestBody SheepInfo sheepInfo)
    {
        Date udate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(udate);
        sheepInfo.setDate(dateString);
        sheepInfoService.insertRecord(sheepInfo);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("addSheep", sheepInfo);
        response.setData(data);
        return response;
    }
}



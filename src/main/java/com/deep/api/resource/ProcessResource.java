package com.deep.api.resource;

import com.deep.api.Utils.FileUtil;
import com.deep.api.Utils.ReadExcel;
import com.deep.api.Utils.UploadFileUtil;
import org.apache.commons.io.FileUtils;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.ProcessReviewModel;
import com.deep.domain.service.ProcessReviewService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/process")
public class ProcessResource {

    @Resource
    private ProcessReviewService processReviewService;

    private Logger logger = LoggerFactory.getLogger(ProcessResource.class);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response addProcessReview(ProcessReviewModel processReviewModel,
                                     @RequestParam("file") MultipartFile file,
                                     HttpServletRequest request) {
        logger.info("invoke /process/insert {}", processReviewModel);

        try {
            String Header = FileUtil.getFileHeader(file);
            if (!Header.equals("D0CF11E0")&&!Header.equals("504B0304")) {
                throw new Exception("文件格式错误！");
            }
            String filepath = request.getSession().getServletContext().getContextPath() + "../process/";
            String fileName = processReviewModel.getCompanyName()+'-'+file.getOriginalFilename();
            String fileAddress = filepath + fileName;
            File dir = new File(filepath);
            if(!dir.exists()){
                dir.mkdir();
            }
            File tempFile = null;
            tempFile = new File(fileAddress);
            FileUtils.copyInputStreamToFile(file.getInputStream(),tempFile);
            //多线程不安全
            StringBuilder stringBuilder = new StringBuilder();
            List<List<Object>> excelList = ReadExcel.readExcel(tempFile);
            List<Object> list ;
            for(int i =0;i < excelList.size();i++)
            {
                list = excelList.get(i);
                for (int j = 0; j< list.size();j++)
                {
                    stringBuilder.append(list.get(i).toString());
                    if(j == 0) {
                        stringBuilder.append(",");
                    }

                }
                stringBuilder.append(";");
            }
            processReviewModel.setEartagAddress(stringBuilder.toString());

            if(tempFile.delete()) {
                logger.info(tempFile.getName() + " is deleted!");
            }else {
                logger.error("Delete operation is failed.");
            }

            processReviewModel.setUploadTime(new Date());
            Integer id = processReviewService.insertReview(processReviewModel);
            Integer processId = processReviewModel.getReviewId();
            if (id != 0) {
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("process", processId);
                response.setData(data);
                return response;
            } else {
                Response response = Responses.errorResponse("数据插入失败");
                return response;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Response response = Responses.errorResponse(e.getMessage());
            return response;
        }
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Response updateRecord (ProcessReviewModel record)
    {
        logger.info("invoke /process/update {}",record);

        record.setUploadTime(new Date());

        Integer id = processReviewService.updateReview(record);

        if (id != 0) {
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("process", record);
            response.setData(data);
            return response;
        } else {
            Response response = Responses.errorResponse("数据更新失败");
            return response;
        }
    }

}

package com.deep.api.resource;

import com.deep.api.Utils.FileUtil;
import com.deep.api.Utils.UploadFileUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.CertificationModel;
import com.deep.domain.model.CertificationModelExample;
import com.deep.domain.service.CertificationModelService;
import com.deep.infra.persistence.sql.mapper.CertificationModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/certificate")
public class CertificationResource {
    @Resource
    private CertificationModelService certificationModelService;

    private final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Response uploadCertificate(CertificationModel certificationModel,
                                      @RequestParam("file") MultipartFile file,
                                      HttpServletRequest request) {
        logger.info("invoke /certificate/upload {}", certificationModel);
        try {
            String Header = FileUtil.getFileHeader(file);
            if (!Header.equals("89504E47") && !Header.equals("47494638") &&
                    !Header.equals("49492A00") && !Header.equals("4D546864") &&
                    !Header.equals("57415645") && !Header.equals("41564920") &&
                    !Header.equals("2E7261FD") && !Header.equals("2E524D46") &&
                    !Header.equals("000001BA") && !Header.equals("6D6F6F76") &&
                    !Header.equals("3026B27") && !Header.equals("58E66CF11") &&
                    !Header.equals("00000020") && !Header.equals("FFD8FFE0")
                    ) {
                throw new Exception("文件格式错误！");
            }
            String filepath = request.getSession().getServletContext().getContextPath() + "../certificate/";
            String fileName = file.getOriginalFilename();
            String fileAddress = filepath + fileName;
            UploadFileUtil.upload(file, fileAddress);
            certificationModel.setCertificateAddress(fileAddress);

            int id = certificationModelService.insertCertification(certificationModel);
            Integer returnId = certificationModel.getReturnId();

            if (id != 0) {
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("Certificate", returnId);
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

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public Response findAll(@RequestBody CertificationModel certificationModel) {
        logger.info("invoke /certificate/findAll {}", certificationModel);
        if (certificationModel.getPlantId().equals(0)) {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        CertificationModelExample certificationModelExample = new CertificationModelExample();
        CertificationModelExample.Criteria criteria = certificationModelExample.createCriteria();
        criteria.andPlantIdEqualTo(certificationModel.getPlantId());

        List<CertificationModel> select = certificationModelService.findAllCertification(certificationModelExample,
                certificationModel.getPageNumb(),
                certificationModel.getLimit());

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("findAll", select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Response deleteById(@RequestBody CertificationModel certificationModel) {
        logger.info("invoke /certificate/deleteById {}", certificationModel);
        if (certificationModel.getId().equals(0)) {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        CertificationModelExample certificationModelExample = new CertificationModelExample();
        CertificationModelExample.Criteria criteria = certificationModelExample.createCriteria();
        criteria.andIdEqualTo(certificationModel.getId());

        int returnId = certificationModel.getId();
        int id = certificationModelService.deleteCertification(certificationModelExample);

        if (id != 0) {
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("deleteById", returnId);
            response.setData(data);
            return response;
        } else {
            Response response = Responses.errorResponse("数据删除失败");
            return response;
        }

    }
}


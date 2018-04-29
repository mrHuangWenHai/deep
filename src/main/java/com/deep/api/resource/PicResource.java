package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Pic;
import com.deep.domain.model.PicExample;
import com.deep.domain.service.PicService;
import com.deep.api.Utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PicResource {
    @Resource
    private PicService picService;

    private final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    @RequestMapping(value = "/uploadFile/upload",method = RequestMethod.POST)
    public @ResponseBody Response addPic(@Valid Pic pic,
                           @RequestParam("file")MultipartFile file,
                           HttpServletRequest request) {

        logger.info("invoke /uploadFile/upload [{},{},{}]",pic,file,request);


        try {
            String Header = FileUtil.getFileHeader(file);
            if ( !Header.equals("89504E47")  && !Header.equals("47494638") &&
                    !Header.equals("49492A00")  && !Header.equals("4D546864")&&
                    !Header.equals("57415645")  && !Header.equals("41564920") &&
                    !Header.equals("2E7261FD")  && !Header.equals("2E524D46") &&
                    !Header.equals("000001BA")  && !Header.equals("6D6F6F76") &&
                    !Header.equals("3026B27") && !Header.equals("58E66CF11") &&
                    !Header.equals("00000020") && !Header.equals("FFD8FFE0")
                    ) {
                throw new Exception("文件格式错误！");

            }


            Date udate = new Date();
            pic.setUdate(udate);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(udate);
            String fileDate = dateString.replaceAll(":", "-");
            String filepath = request.getSession().getServletContext().getContextPath() + "../file/";
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = pic.getBrand() + "_" + pic.getVaccine() + "_" + fileDate + "." + suffix;
            String fileAddress = filepath + fileName;
            if (!file.isEmpty()) {
                File saveFile = new File(fileAddress);
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                try {
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(saveFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("上传失败，" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("上传失败，" + e.getMessage());
                }
            }
            pic.setAddress(fileAddress);
            pic.setFilename(fileName);
            int id = picService.insertPic(pic);
            Integer returnId = pic.getReturnId();

            if (id != 0) {
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("Pic", returnId);
                response.setData(data);
                return response;
            } else {
                Response response = Responses.errorResponse("数据插入失败");
                return response;
            }
        }catch (Exception e){

            System.out.println(e.getMessage());
            Response response = Responses.errorResponse(e.getMessage());
            return response;
        }
    }

    @RequestMapping(value = "/searchfile/searchByExpert",method = RequestMethod.POST)
    public @ResponseBody Response getByExpert(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByExpert {}",pic);
        if (pic.getExpert().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andExpertLike("%"+pic.getExpert()+"%");
        List<Pic> select = picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByExpert",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchByDate",method = RequestMethod.POST)
    public @ResponseBody Response getByDate(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByDate [{},{}]",pic.getSdate(),pic.getEdate());
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andUdateBetween(pic.getSdate(),pic.getEdate());
        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUdate",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchByBrand",method = RequestMethod.POST)
    public @ResponseBody Response getByBrand(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByBrand {}",pic.getBrand());
        if (pic.getBrand().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andBrandLike("%"+pic.getBrand()+"%");
        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByBrand",select);
        response.setData(data);
        return response;
    }
    @RequestMapping(value = "/searchfile/searchByVaccine",method = RequestMethod.POST)
    public @ResponseBody Response getByVaccine(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByVaccine {}",pic.getVaccine());
        if (pic.getVaccine().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andVaccineLike("%"+pic.getVaccine()+"%");
        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByVaccine",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchBySymptom",method = RequestMethod.POST)
    public @ResponseBody Response getBySymptom(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchBySymptom {}",pic.getSymptom());
        if (pic.getSymptom().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andSymptomLike("%"+pic.getSymptom()+"%");
        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchBySymptom",select);
        response.setData(data);
        return response;
    }

//    @RequestMapping(value = "/searchFile/searchByUploader",method = RequestMethod.GET)
//    public String searchByUploader(){
//
//        return "searchByUploader";
//
//    }
    @RequestMapping(value = "/searchfile/searchByUploader",method = RequestMethod.POST)
    public @ResponseBody Response getByUploader(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByUploader {}",pic.getUploader());
        if (pic.getUploader().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andUploaderLike("%"+pic.getUploader()+"%");
        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUploader",select);
        response.setData(data);
        return response;
    }

}

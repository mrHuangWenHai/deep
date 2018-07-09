package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.constant.FileTypeEnum;
import com.deep.domain.model.Pic;
import com.deep.domain.model.PicExample;
import com.deep.domain.service.PicService;
import com.deep.api.Utils.FileUtil;
import com.deep.domain.util.JudgeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
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

    private final String filePath = "./pic/";

    @Permit(authorities = {"add_video"})
    @RequestMapping(value = "/uploadFile/upload",method = RequestMethod.POST)
    public @ResponseBody Response addPic(Pic pic,
                           @RequestParam("file")MultipartFile file) {

        logger.info("invoke /uploadFile/upload [{}]",pic);

        if (!JudgeUtil.isBrand(pic.getBrand()))
        {
            return Responses.errorResponse("商标耳牌不符合规则");
        }

        try {
            String Header = FileUtil.getFileHeader(file);

            if (    Header.equals(FileTypeEnum.PNG.value)  || Header.equals(FileTypeEnum.GIF.value) ||
                    Header.equals(FileTypeEnum.TIF.value) || Header.equals(FileTypeEnum.MPG.value)  ||
                    Header.equals(FileTypeEnum.JPG.value) || Header.equals(FileTypeEnum.JPG1.value)) {
                    pic.setFiletype(0);
            }
            else if(Header.equals(FileTypeEnum.AVI.value) || Header.equals(FileTypeEnum.RM.value)  ||
                    Header.equals(FileTypeEnum.MOV.value) || Header.equals(FileTypeEnum.ASF.value) ||
                    Header.equals(FileTypeEnum.MP4.value) || Header.equals(FileTypeEnum.MP4_1.value)){
                    pic.setFiletype(1);
            }
            else{

                throw new Exception("文件格式错误！");
            }


            Date udate = new Date();
            pic.setUdate(udate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(udate);
            String fileDate = dateString.replaceAll(":", "-");
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = pic.getBrand() + "_" + pic.getVaccine() + "_" + fileDate + "." + suffix;
            String fileAddress = filePath + fileName;
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
                return Responses.errorResponse("数据插入失败");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return Responses.errorResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/searchfile/searchAll")
    @ResponseBody
    public Response getAll(@RequestBody Pic pic) {

      logger.info("/searchfile/searchAll {}", pic);
        List<Pic> sizeOfAll = null;
      if(pic.getFiletype() != 2)
      {
          PicExample picExample = new PicExample();
          PicExample.Criteria criteria = picExample.createCriteria();
          criteria.andFiletypeEqualTo(pic.getFiletype());
          sizeOfAll = picService.findPicSelective(picExample);
      }
      else{
          sizeOfAll = picService.getAll();
      }
      int size = sizeOfAll.size();
      int page = pic.getPageNumb();
      int pageSize = pic.getLimit();
      int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
      List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);

      Response response = Responses.successResponse();
      HashMap<String,Object>data = new HashMap<>();
      data.put("List",select);
      data.put("size",size);
      response.setData(data);
      return response;

    }

    @RequestMapping(value = "/searchfile/searchByExpert",method = RequestMethod.POST)
    public @ResponseBody Response getByExpert(@RequestBody Pic pic) {
        logger.info("invoke /searchfile/searchByExpert {}",pic);
        if (pic.getExpert().isEmpty()) {
            return Responses.errorResponse("查询条件不能为空！");
        }
        if (pic.getExpert() == null) {
            return Responses.errorResponse("查询条件不能为空！");
        }

        PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        if(pic.getFiletype() != 2)
        {
            criteria.andFiletypeEqualTo(pic.getFiletype());
        }
        criteria.andExpertLike("%"+pic.getExpert()+"%");
        List<Pic> sizeOfAll = picService.findPicSelective(picExample);
        int size = sizeOfAll.size();
        int page = pic.getPageNumb();
        int pageSize = pic.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);
//        Integer size = sizeOfAll.size();
//        List<Pic> select = picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("size",size);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchByDate",method = RequestMethod.POST)
    public @ResponseBody Response getByDate(@RequestBody Pic pic) {
        logger.info("invoke /searchfile/searchByDate [{},{}]",pic.getSdate(),pic.getEdate());
        if (pic.getSdate() == null || pic.getEdate() == null) {
            return Responses.errorResponse("查询条件不能为空！");
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andUdateBetween(pic.getSdate(),pic.getEdate());
        if(pic.getFiletype() != 2)
        {
            criteria.andFiletypeEqualTo(pic.getFiletype());
        }
        List<Pic> sizeOfAll = picService.findPicSelective(picExample);
//        Integer size = sizeOfAll.size();
//        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());
        int size = sizeOfAll.size();
        int page = pic.getPageNumb();
        int pageSize = pic.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);
        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("size",size);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchByBrand",method = RequestMethod.POST)
    public @ResponseBody Response getByBrand(@RequestBody Pic pic) {
        logger.info("invoke /searchfile/searchByBrand {}",pic.getBrand());
        if (pic.getBrand().isEmpty())
        {
            return Responses.errorResponse("查询条件不能为空！");
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andBrandLike("%"+pic.getBrand()+"%");
        if(pic.getFiletype() != 2)
        {
            criteria.andFiletypeEqualTo(pic.getFiletype());
        }
        List<Pic> sizeOfAll = picService.findPicSelective(picExample);
//        Integer size = sizeOfAll.size();
//        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());
        int size = sizeOfAll.size();
        int page = pic.getPageNumb();
        int pageSize = pic.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);
        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("size",size);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchByVaccine",method = RequestMethod.POST)
    public @ResponseBody Response getByVaccine(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByVaccine {}",pic.getVaccine());
        if (pic.getVaccine().isEmpty())
        {
            return Responses.errorResponse("查询条件不能为空！");
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andVaccineLike("%"+pic.getVaccine()+"%");
        if(pic.getFiletype() != 2)
        {
            criteria.andFiletypeEqualTo(pic.getFiletype());
        }
        List<Pic> sizeOfAll = picService.findPicSelective(picExample);
//        Integer size = sizeOfAll.size();
//        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());
        int size = sizeOfAll.size();
        int page = pic.getPageNumb();
        int pageSize = pic.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);
        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("size",size);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchBySymptom",method = RequestMethod.POST)
    public @ResponseBody Response getBySymptom(@RequestBody Pic pic) {

        logger.info("invoke /searchfile/searchBySymptom {}",pic.getSymptom());
        if (pic.getSymptom().isEmpty()) {
            return Responses.errorResponse("查询条件不能为空！");
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andSymptomLike("%"+pic.getSymptom()+"%");
        if(pic.getFiletype() != 2)
        {
            criteria.andFiletypeEqualTo(pic.getFiletype());
        }
        List<Pic> sizeOfAll = picService.findPicSelective(picExample);
//        Integer size = sizeOfAll.size();
//        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());
        int size = sizeOfAll.size();
        int page = pic.getPageNumb();
        int pageSize = pic.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);
        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("size",size);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/searchfile/searchByUploader",method = RequestMethod.POST)
    public @ResponseBody Response getByUploader(@RequestBody Pic pic){
        logger.info("invoke /searchfile/searchByUploader {}",pic.getUploader());
        if (pic.getUploader().isEmpty()) {
            return Responses.errorResponse("查询条件不能为空！");
        }
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andUploaderLike("%"+pic.getUploader()+"%");
        if(pic.getFiletype() != 2)
        {
            criteria.andFiletypeEqualTo(pic.getFiletype());
        }
        List<Pic> sizeOfAll = picService.findPicSelective(picExample);
//        Integer size = sizeOfAll.size();
//        List<Pic> select=picService.findPicSelectiveWithRowbounds(picExample,pic.getPageNumb(),pic.getLimit());
        int size = sizeOfAll.size();
        int page = pic.getPageNumb();
        int pageSize = pic.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Pic> select = sizeOfAll.subList(page * pageSize, destIndex);
        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("size",size);
        response.setData(data);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/pic/delete/{id}",method = RequestMethod.DELETE)
    public Response deletePicById(@PathVariable(value = "id")Integer id)
    {
        if (picService.deleteById(id) != 0 )
        {
            return Responses.successResponse();
        }
        else {
            return Responses.errorResponse("删除失败");
        }
    }
}

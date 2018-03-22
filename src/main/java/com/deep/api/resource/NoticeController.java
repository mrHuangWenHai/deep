package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NoticePlan;
import com.deep.domain.model.NoticePlanExample;
import com.deep.domain.service.NoticePlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: Created  By  Caojiawei
 * date: 2018/3/8  20:14
 */
@Controller
public class NoticeController {
    @Resource
    private NoticePlanService noticePlanService;

    @ResponseBody
    @RequestMapping(value = "/NoticePlan",method = RequestMethod.GET)
    public String helloNotice() {
        return "Hello NoticePlan!";
    }

//    按主键删除的接口：/NoticeInsert
//    按主键删除的方法名：addPlan()
//    接收参数：整个表单信息（所有参数必填）
//    参数类型为：String professor;Byte type;String title;String content;
    @RequestMapping(value = "/NoticeInsert",method = RequestMethod.GET)
    public String addPlan(){
        return "NoticeInsert";
    }
    @ResponseBody
    @RequestMapping(value = "/NoticeInsert/show",method = RequestMethod.POST)
    public Response addPlan(@RequestBody @Valid NoticePlan insert){
        insert.setGmtCreate(new Date());
        insert.setProfessor(insert.getProfessor());
        insert.setType(insert.getType());
        insert.setTitle(insert.getTitle());
        insert.setContent(insert.getContent());
        noticePlanService.addPlan(insert);

        NoticePlanExample insertExample = new NoticePlanExample();
        NoticePlanExample.Criteria criteria = insertExample.createCriteria();
        criteria.andTitleEqualTo(insert.getTitle());
        criteria.andTypeEqualTo(insert.getType());
        criteria.andProfessorEqualTo(insert.getProfessor());
        List<NoticePlan> select = noticePlanService.findPlanSelective(insertExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",select);
        response.setData(data);
        return response;
    }

//    按主键删除的接口：/NoticeDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/NoticeDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "NoticeDeleteById";
    }
    @ResponseBody
        @RequestMapping(value = "/NoticeDeleteById/show",method = RequestMethod.DELETE)
    public Response dropPlan(@RequestBody @Valid NoticePlan delete){
        noticePlanService.dropPlan(delete.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",delete);
        response.setData(data);
        return response;
    }

//    按主键修改的接口：/NoticeUpdate
//    按主键修改的方法名：changePlan()
//    接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/NoticeUpdate",method = RequestMethod.GET)
    public String changePlan(){
        return "NoticeUpdate";
    }
    @ResponseBody
    @RequestMapping(value = "/NoticeUpdate/show",method = RequestMethod.POST)
    public Response changePlan(@RequestBody @Valid NoticePlan update,HttpServletRequest request){
        update.setId(update.getId());
        update.setGmtModified(new Date());
        update.setType(update.getType());
        update.setTitle(update.getTitle());
        update.setContent(update.getContent());
        /*List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        String filename = null;
        String filepath = request.getSession().getServletContext().getContextPath()+"../picture/"+update.getProfessor()+"/";
        file = files.get(0);
        filename = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                noticePlanService.uploadFile(file.getBytes(), filepath, filename);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }*/
        noticePlanService.changePlan(update);
        NoticePlan selectById = noticePlanService.findPlanById(update.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",selectById);
        response.setData(data);
        return response;
    }

//    按主键查询的接口：/NoticeSelectById
//    按主键查询的方法名：findPlanById()
//    接收参数：整型的主键号（保留接口查询，前端不调用此接口）
    @RequestMapping(value = "/NoticeSelectById",method = RequestMethod.GET)
    public String findPlanById(){
        return "NoticeSelectById";
    }
    @ResponseBody
    @RequestMapping(value = "/NoticeSelectById/show",method = RequestMethod.GET)
    public Response findPlanById(@RequestParam("id") Integer id){
        NoticePlan selectById = noticePlanService.findPlanById(id);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",selectById);
        response.setData(data);
        return response;
    }

//    按条件查询接口：/NoticeSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及四个时间字符串（所有参数可以选填）
    @RequestMapping(value = "/NoticeSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "NoticeSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/NoticeSelective/show",method = RequestMethod.GET)
    public Response findPlanSelective(@RequestBody @Valid NoticePlan noticePlan,
                                      @RequestParam ("s_gmtCreate1") String s_gmtCreate1,
                                      @RequestParam ("s_gmtCreate2") String s_gmtCreate2,
                                      @RequestParam ("s_gmtModified1") String s_gmtModified1,
                                      @RequestParam ("s_gmtModified2") String s_gmtModified2
                                      ) throws ParseException {
        Date gmtCreate1 = null;
        Date gmtCreate2 = null;
        Date gmtModified1 = null;
        Date gmtModified2 = null;
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        NoticePlanExample noticePlanExample = new NoticePlanExample();
        NoticePlanExample.Criteria criteria = noticePlanExample.createCriteria();
        if (s_gmtCreate1 != "" && s_gmtCreate2 != ""){
            gmtCreate1 =  formatter.parse(s_gmtCreate1);
            gmtCreate2 =  formatter.parse(s_gmtCreate2);
        }
        if (s_gmtModified1 != "" && s_gmtModified2 != ""){
            gmtModified1 =  formatter.parse(s_gmtModified1);
            gmtModified2 =  formatter.parse(s_gmtModified2);
        }
        if(noticePlan.getGmtCreate() != null && noticePlan.getGmtCreate().toString() !=""){
            criteria.andGmtCreateBetween(gmtCreate1,gmtCreate2);
        }
        if(noticePlan.getGmtModified() != null && noticePlan.getGmtModified().toString() !=""){
            criteria.andGmtModifiedBetween(gmtModified1,gmtModified2);
        }
        if(noticePlan.getId() != null && noticePlan.getId().toString() !=""){
            criteria.andIdEqualTo(noticePlan.getId());
        }
        if(noticePlan.getProfessor() != null && noticePlan.getProfessor() !=""){
            criteria.andProfessorEqualTo(noticePlan.getProfessor());
        }
        if(noticePlan.getType() != null && noticePlan.getType().toString() !=""){
            criteria.andTypeEqualTo(noticePlan.getType());
        }
        if(noticePlan.getTitle() != null && noticePlan.getTitle() !=""){
            criteria.andTitleLike('%'+noticePlan.getTitle()+'%');
        }
        List<NoticePlan> select = noticePlanService.findPlanSelective(noticePlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",select);
        response.setData(data);
        return response;
    }

//    站内搜索接口：/SearchInSite
//    站内搜索方法名：searchInSite()
//    接收的参数：用户在搜索栏输入的信息（字符串）
    @RequestMapping(value = "/SearchInSite",method = RequestMethod.GET)
    public String searchInSite(){
        return "SearchInSite";
    }
    @ResponseBody
    @RequestMapping(value = "/SearchInSite/show",method = RequestMethod.GET)
    public Response searchInSite(@Valid NoticePlan noticePlan){
        NoticePlanExample noticePlanExample = new NoticePlanExample();
        NoticePlanExample.Criteria criteria = noticePlanExample.createCriteria();
        if(noticePlan.getTitle() != null && noticePlan.getTitle() !=""){
            criteria.andTitleLike('%'+noticePlan.getTitle()+'%');
        }
        List<NoticePlan> select = noticePlanService.findPlanSelective(noticePlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",select);
        response.setData(data);
        return response;
    }

//    上传接口：/Upload
//    上传方法名：uploadFile()
//    接收的参数：用户浏览本地文件选择文件上传
    @RequestMapping(value = "/Upload",method = RequestMethod.GET)
    public String uploadFile(){
        return "Upload";
    }
    @ResponseBody
    @RequestMapping(value = "/Upload/show",method = RequestMethod.POST)
    public Response uploadFile(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filepath = request.getSession().getServletContext().getContextPath()+"../picture/";
        List<String> path = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String filename = file.getOriginalFilename();
            /*String suffixname = filename.substring(filename.lastIndexOf("."));*/
            if (!file.isEmpty()) {
                try {
                    noticePlanService.uploadFile(file.getBytes(), filepath, filename);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            path.add(filepath+filename);
            System.out.println(path);
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("address",path);
        response.setData(data);
        return response;
    }

//    下载接口：/Download
//    下载方法名：downloadFile()
//    接收的参数：文件在服务器的相对路径
    @RequestMapping(value = "/Download",method = RequestMethod.GET)
    public String downloadFile(){
        return "Download";
    }
    @ResponseBody
    @RequestMapping(value = "/Download/show",method = RequestMethod.GET)
    public String downloadFile(@RequestParam ("filePath") String filePath,
                               HttpServletResponse response){
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;file=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        return null;
    }
}

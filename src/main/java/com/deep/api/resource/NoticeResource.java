package com.deep.api.resource;

import com.deep.api.Utils.FileUtil;
import com.deep.api.request.NoticePlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NoticePlan;
import com.deep.domain.model.NoticePlanExample;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.NoticePlanService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/3/8  20:14
 */
@Controller
public class NoticeResource {
    @Resource
    private NoticePlanService noticePlanService;

    @ResponseBody
    @RequestMapping(value = "/noticePlan",method = RequestMethod.GET)
    public String helloNotice() {
        return "Hello NoticePlan!";
    }

//    按主键删除的接口：/noticeInsert
//    按主键删除的方法名：addPlan()
//    接收参数：整个表单信息（所有参数必填）
//    参数类型为：String professor;Byte type;String title;String content;
    @RequestMapping(value = "/noticeInsert",method = RequestMethod.GET)
    public String addPlan(){
        return "NoticeInsert";
    }
    @ResponseBody
    @RequestMapping(value = "/noticeInsert/show",method = RequestMethod.POST)
    public Response addPlan(@Valid NoticePlan insert,
                            HttpServletRequest request,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("信息发布失败！");
        }else {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            if (files.size() !=0){
                MultipartFile file = files.get(0);
                String filename = file.getOriginalFilename();
                String filepath = "../"+"picture/"+insert.getProfessor()+"/";
                String suffixname = "";
                if (insert.getProfessor().isEmpty()){
                    insert.setProfessor("Unknown");
                    filepath = "../"+"picture/Unknown/";
                }
                if (!filename.isEmpty()){
                    suffixname = filename.substring(filename.lastIndexOf("."));
                }
                try {
                    String Header = FileUtil.getFileHeader(file);
                    if (!Header.equals("FFD8FF")
                            && !Header.equals("89504E47")
                            && !Header.equals("47494638")
                            && !Header.equals("49492A00")
                            && !Header.equals("424D")
                            && !Header.equals("57415645")
                            && !Header.equals("41564920")
                            && !Header.equals("2E7261FD")
                            && !Header.equals("2E524D46")
                            && !Header.equals("000001BA")
                            && !Header.equals("6D6F6F76")
                            && !Header.equals("3026B2758E66CF11")
                            && !Header.equals("4D546864")
                            && !Header.equals("00000020")
                            && !Header.equals("FFD8FFE0")) {
                        throw new Exception("上传文件格式错误!");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return Responses.errorResponse(e.getMessage());
                }
                if (!file.isEmpty()) {
                    try {
                        noticePlanService.uploadFile(file.getBytes(), filepath, filename);
                    } catch (Exception e) {
                        System.out.println("文件上传错误，请重新上传！");
                    }
                }
                if (!filename.isEmpty()){
                    insert.setFilepath(filepath+filename);
                }else {
                    insert.setFilepath(null);
                }
                if (!filename.isEmpty()){
                    insert.setSuffixname(suffixname);
                }else {
                    insert.setSuffixname(null);
                }
            }
            insert.setGmtCreate(new Date());
            noticePlanService.addPlan(insert);

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("notice_plan",insert);
            response.setData(data);
            return response;
        }
    }

//    按主键删除的接口：/noticeDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/noticeDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "NoticeDeleteById";
    }
    @ResponseBody
        @RequestMapping(value = "/noticeDeleteById/show",method = RequestMethod.DELETE)
    public Response dropPlan(@RequestBody @Valid NoticePlan delete){
        noticePlanService.dropPlan(delete.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice_plan",delete);
        response.setData(data);
        return response;
    }

//    按主键修改的接口：/noticeUpdate
//    按主键修改的方法名：changePlan()
//    接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/noticeUpdate",method = RequestMethod.GET)
    public String changePlan(){
        return "NoticeUpdate";
    }
    @ResponseBody
    @RequestMapping(value = "/noticeUpdate/show",method = RequestMethod.POST)
    public Response changePlan(@Valid NoticePlan update,
                               HttpServletRequest request,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("信息修改失败！");
        }else {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            if (files.size() !=0){
                MultipartFile file = files.get(0);
                String filename = file.getOriginalFilename();
                String filepath = "../"+"picture/"+update.getProfessor()+"/";
                String suffixname = "";
                if (update.getProfessor().isEmpty()){
                    update.setProfessor("Unknown");
                    filepath = "../"+"picture/Unknown/";
                }
                if (!filename.isEmpty()){
                    suffixname = filename.substring(filename.lastIndexOf("."));
                }
                try {
                    String Header = FileUtil.getFileHeader(file);
                    if (!Header.equals("FFD8FF")
                            && !Header.equals("89504E47")
                            && !Header.equals("47494638")
                            && !Header.equals("49492A00")
                            && !Header.equals("424D")
                            && !Header.equals("57415645")
                            && !Header.equals("41564920")
                            && !Header.equals("2E7261FD")
                            && !Header.equals("2E524D46")
                            && !Header.equals("000001BA")
                            && !Header.equals("6D6F6F76")
                            && !Header.equals("3026B2758E66CF11")
                            && !Header.equals("4D546864")
                            && !Header.equals("00000020")
                            && !Header.equals("FFD8FFE0")) {
                        throw new Exception("上传文件格式错误！");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return Responses.errorResponse(e.getMessage());
                }
                if (!file.isEmpty()) {
                    try {
                        noticePlanService.uploadFile(file.getBytes(), filepath, filename);
                    } catch (Exception e) {
                        System.out.println("文件上传错误，请重新上传！");
                    }
                }
                if (!filename.isEmpty()){
                    update.setFilepath(filepath+filename);
                }else {
                    update.setFilepath(null);
                }
                if (!filename.isEmpty()){
                    update.setSuffixname(suffixname);
                }else {
                    update.setSuffixname(null);
                }
            }
            update.setGmtModified(new Date());
            noticePlanService.changePlan(update);
            NoticePlan selectById = noticePlanService.findPlanById(update.getId());

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("notice_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按主键查询的接口：/noticeSelectById
//    按主键查询的方法名：findPlanById()
//    接收参数：整型的主键号（保留接口查询，前端不调用此接口）
    @RequestMapping(value = "/noticeSelectById",method = RequestMethod.GET)
    public String findPlanById(){
        return "NoticeSelectById";
    }
    @ResponseBody
    @RequestMapping(value = "/noticeSelectById/show",method = RequestMethod.GET)
    public Response findPlanById(@Valid NoticePlan noticePlan,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("根据主键查询失败！");
        }else {
            NoticePlan selectById = noticePlanService.findPlanById(noticePlan.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("notice_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按条件查询接口：/noticeSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及四个时间字符串（所有参数可以选填）
    @RequestMapping(value = "/noticeSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "NoticeSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/noticeSelective/show",method = RequestMethod.POST)
    public Response findPlanSelective(@RequestBody @Valid NoticePlanModel planModel,
                                      BindingResult bindingResult) throws ParseException{
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("根据条件查询失败！");
        }else {
            //将planModel部分变量拆分传递给对象insert
            NoticePlan noticePlan = new NoticePlan();
            noticePlan.setId(planModel.getId());
            noticePlan.setGmtCreate(planModel.getGmtCreate());
            noticePlan.setGmtModified(planModel.getGmtModified());
            noticePlan.setProfessor(planModel.getProfessor());
            noticePlan.setType(planModel.getType());
            noticePlan.setTitle(planModel.getTitle());
            noticePlan.setFilepath(planModel.getFilepath());
            noticePlan.setSuffixname(planModel.getSuffixname());
            noticePlan.setContent(planModel.getContent());
            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setSearch_string(planModel.getSearch_string());
            otherTime.setS_breedingT(planModel.getS_breedingT());
            System.out.println(otherTime.getS_breedingT());
            otherTime.setS_gestationT(planModel.getS_gestationT());
            System.out.println(otherTime.getS_gestationT());
            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
            System.out.println(otherTime.getS_prenatalIT());
            otherTime.setS_cubT(planModel.getS_cubT());
            System.out.println(otherTime.getS_cubT());
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
            otherTime.setS_nutritionT(planModel.getS_nutritionT());
            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
            otherTime.setS_breedingT1(planModel.getS_breedingT1());
            otherTime.setS_breedingT2(planModel.getS_breedingT2());
            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
            otherTime.setS_gestationT1(planModel.getS_gestationT1());
            otherTime.setS_gestationT2(planModel.getS_gestationT2());
            otherTime.setS_cubT1(planModel.getS_cubT1());
            otherTime.setS_cubT2(planModel.getS_cubT2());
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
            otherTime.setDownloadPath(planModel.getDownloadPath());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

            Date gmtCreate1 = null;
            Date gmtCreate2 = null;
            Date gmtModified1 = null;
            Date gmtModified2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            NoticePlanExample noticePlanExample = new NoticePlanExample();
            NoticePlanExample.Criteria criteria = noticePlanExample.createCriteria();
            if (otherTime.getS_gmtCreate1() != null && !otherTime.getS_gmtCreate1().isEmpty() && otherTime.getS_gmtCreate2() != null && !otherTime.getS_gmtCreate2().isEmpty()){
                gmtCreate1 =  formatter.parse(otherTime.getS_gmtCreate1());
                gmtCreate2 =  formatter.parse(otherTime.getS_gmtCreate2());
            }
            if (otherTime.getS_gmtModified1() != null && !otherTime.getS_gmtModified1().isEmpty() && otherTime.getS_gmtModified2() != null && !otherTime.getS_gmtModified2().isEmpty()){
                gmtModified1 =  formatter.parse(otherTime.getS_gmtModified1());
                gmtModified2 =  formatter.parse(otherTime.getS_gmtModified2());
            }
            if(noticePlan.getGmtCreate() != null && !noticePlan.getGmtCreate().toString().isEmpty()){
                criteria.andGmtCreateBetween(gmtCreate1,gmtCreate2);
            }
            if(noticePlan.getGmtModified() != null && !noticePlan.getGmtModified().toString().isEmpty()){
                criteria.andGmtModifiedBetween(gmtModified1,gmtModified2);
            }
            if(noticePlan.getId() != null && !noticePlan.getId().toString().isEmpty()){
                criteria.andIdEqualTo(noticePlan.getId());
            }
            if(noticePlan.getProfessor() != null && !noticePlan.getProfessor().isEmpty()){
                criteria.andProfessorEqualTo(noticePlan.getProfessor());
            }
            if(noticePlan.getType() != null && !noticePlan.getType().toString().isEmpty()){
                criteria.andTypeEqualTo(noticePlan.getType());
            }
            List<NoticePlan> selective = noticePlanService.findPlanSelective(noticePlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("notice_plan",selective);
            response.setData(data);
            return response;
        }
    }

//    站内搜索接口：/searchInSite
//    站内搜索方法名：searchInSite()
//    接收的参数：用户在搜索栏输入的信息（字符串）
    @RequestMapping(value = "/searchInSite",method = RequestMethod.GET)
    public String searchInSite(){
        return "SearchInSite";
    }
    @ResponseBody
    @RequestMapping(value = "/searchInSite/show",method = RequestMethod.POST)
    public Response searchInSite(@RequestBody @Valid OtherTime otherTime,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("输入有误，站内搜索失败！");
        }else {
            List<NoticePlan> selectInSite = noticePlanService.selectInSite(otherTime.getSearch_string());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("notice_plan",selectInSite);
            response.setData(data);
            return response;
        }
    }

//    上传接口：/upload
//    上传方法名：uploadFile()
//    接收的参数：用户浏览本地文件选择文件上传
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String uploadFile(){
        return "Upload";
    }
    @ResponseBody
    @RequestMapping(value = "/upload/show",method = RequestMethod.POST)
    public Response uploadFile(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filepath = "../picture/rich_text_format/";
        List<String> path = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String filename = file.getOriginalFilename();
            try {
                String Header = FileUtil.getFileHeader(file);
                if (!Header.equals("FFD8FF")
                        && !Header.equals("89504E47")
                        && !Header.equals("47494638")
                        && !Header.equals("49492A00")
                        && !Header.equals("424D")
                        && !Header.equals("57415645")
                        && !Header.equals("41564920")
                        && !Header.equals("2E7261FD")
                        && !Header.equals("2E524D46")
                        && !Header.equals("000001BA")
                        && !Header.equals("6D6F6F76")
                        && !Header.equals("3026B2758E66CF11")
                        && !Header.equals("4D546864")
                        && !Header.equals("00000020")
                        && !Header.equals("FFD8FFE0")) {
                    throw new Exception("文件格式错误！");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                return Responses.errorResponse(e.getMessage());
            }
            if (!file.isEmpty()) {
                try {
                    noticePlanService.uploadFile(file.getBytes(), filepath, filename);
                } catch (Exception e) {
                    System.out.println("文件上传失败，请重新上传！");
                }
            }
            path.add(filepath+filename);
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("photos_address",path);
        response.setData(data);
        return response;
    }

//    下载接口：/download
//    下载方法名：downloadFile()
//    接收的参数：文件在服务器的相对路径
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String downloadFile(){
        return "Download";
    }
    @ResponseBody
    @RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
    public String downloadFile(@RequestBody @Valid OtherTime otherTime,
                               BindingResult bindingResult,
                               HttpServletResponse response){
        if (bindingResult.hasErrors()) {
            System.out.println("下载文件失败，请重试!");
        }else {
            File file = new File(otherTime.getDownloadPath());
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
        }
        return null;
    }
}

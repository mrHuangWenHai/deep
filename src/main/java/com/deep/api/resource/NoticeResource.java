package com.deep.api.resource;

import com.deep.api.Utils.FileUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NoticePlan;
import com.deep.domain.service.NoticePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Created  By  Caojiawei
 * date: 2018/3/8  20:14
 */
@RestController

@RequestMapping(value = "notice")

public class NoticeResource {

    @Resource
    private NoticePlanService noticePlanService;

    private final Logger logger = LoggerFactory.getLogger(NoticeResource.class);

    /**
     * @param insert 整个表单信息（所有参数必填）, 参数类型为：String professor;Byte type;String title;String content;
     * @param bindingResult bindingResult
     * @return response
     */
    @Permit(authorities = "add_release")
    @PostMapping(value = "")
    public Response addPlan(@RequestBody @Valid NoticePlan insert, BindingResult bindingResult){
        logger.info("invoke addPlan {}, the url is notice", insert);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("信息发布失败!");
            Map<String, Object> map = new HashMap<>();
            map.put("errorMessage", bindingResult.getAllErrors());
            response.setData(map);
            return response;
        }else {
            insert.setGmtCreate(new Date());
            insert.setGmtModified(new Date());
            int success =  noticePlanService.insertNoticePlan(insert);
            if (success <= 0) {
                return Responses.errorResponse("添加失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();

            data.put("addID", success);

            response.setData(data);
            return response;
        }
    }

    /**
     * @param id 接收参数：id，根据主键号删除
     * @return response
     */
    @Permit(authorities = "query_publishing")
    @DeleteMapping(value = "/{id}")
    public Response dropPlan(@PathVariable("id") String id) {

        logger.info("invoke deleteOne {}, url is notice/{id}", id);

        int uid = StringToLongUtil.stringToInt(id);
        System.out.println("uid is " + uid);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }

        int success = noticePlanService.deleteNoticePlan((long)uid);
        if (success <= 0) {

            return Responses.errorResponse("删除通知失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();

        data.put("delete_id", success);

        response.setData(data);
        return response;
    }

    /**
     * 按主键修改的接口：/noticeUpdate
     * 按主键修改的方法名：changePlan()
     * 接收参数：整个表单信息（整型id必填，各参数选填）
     * @param update update
     * @param bindingResult bindingResult
     * @return response
     */
    @Permit(authorities = "modify_release")
    @PatchMapping(value = "/{id}")
    public Response changePlan(@RequestBody @Valid NoticePlan update, @PathVariable("id") String id, BindingResult bindingResult){
        logger.info("invoke changePlan {}, url is notice/{id}", id);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("信息修改失败！");
        }else {
            int uid = StringToLongUtil.stringToInt(id);
            if (uid == -1) {
                return Responses.errorResponse("查询错误");
            }
            update.setId(uid);
            update.setGmtModified(new Date());
            // 首先需要获取数据库中该条记录的信息
            NoticePlan oldPlan = noticePlanService.getOneNotice(uid);
            if (oldPlan == null) {
                return Responses.errorResponse("修改失败");
            }

            update.setType(update.getType() == null ? oldPlan.getType() : update.getType());
            update.setContent(update.getContent() == null ? oldPlan.getContent() : update.getContent());
            update.setOperatorName(update.getOperatorName() == null ? oldPlan.getOperatorName() : update.getOperatorName());
            update.setOperatorId(update.getOperatorId() == null ? oldPlan.getOperatorId() : update.getOperatorId());
            update.setGmtCreate(update.getGmtCreate() == null ? oldPlan.getGmtCreate() : update.getGmtCreate());
            update.setTitle(update.getTitle() == null ? oldPlan.getTitle() : update.getTitle());

            int updateID = noticePlanService.updateNoticePlan(update);
            if (updateID < 0) {
                return Responses.errorResponse("更新失败");
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", updateID);

            response.setData(data);
            return response;
        }
    }

    /**
     * 按主键查询的接口：/noticeSelectById
     * 按主键查询的方法名：findPlanById()
     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）
     * @return response
     */
    @Permit(authorities = "query_publishing")
    @GetMapping(value = "/find/{id}")
    public Response findPlanById(@PathVariable("id") String id){
        logger.info("invoke findPlanById {}, url is notice/find/{id}", id);
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        NoticePlan selectById = noticePlanService.getOneNotice(uid);
        if (selectById == null) {
            return Responses.errorResponse("无此记录");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model",selectById);
        response.setData(data);
        return response;
    }

    /**
     * 查询所有的通告
     * @return response
     */
    @Permit(authorities = "query_publishing")
    @GetMapping(value = "")
    public Response getAll() {
        logger.info("invoke geAll, url is notice");
        List<NoticePlan> all = noticePlanService.getAll();
        if (all == null) {
            return Responses.errorResponse("查询失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List",all);
        response.setData(data);
        return response;
    }

    /**
     * 查找某种类型的通知公告
     * @param type 通知公告类型, 需要跟前端协商
     * @return response
     */
    @Permit(authorities = "query_publishing")
    @GetMapping(value = "/type/{type}")
    public Response getAllOfOneType(@PathVariable("type") String type) {
        logger.info("invoke getAllOneType {}, url is notice/type/{type}", type);
        if (type == null || type.equals("")) {
            return Responses.errorResponse("error!");
        }
        List<NoticePlan> all = noticePlanService.getNoticesByOneType(type);
        if (all == null) {
            return Responses.errorResponse("查询错误");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List", all);
        data.put("size", all.size());
        response.setData(data);
        return response;
    }

//    /**
//     * 按条件查询接口：/noticeSelective
//     * 按条件查询方法名：findPlanSelective()
//     * 接收的参数：前端的各参数，以及四个时间字符串（所有参数可以选填）
//     * @param planModel
//     * @param bindingResult
//     * @return
//     * @throws ParseException
//     */

//    @PostMapping(value = "/bySelective")
//    public Response findPlanSelective(@RequestBody @Valid NoticePlanModel planModel, BindingResult bindingResult) throws ParseException{
//        if (bindingResult.hasErrors()) {
//            return Responses.errorResponse("根据条件查询失败！");
//        }else {
//            // 设置单页的记录数为10
//            if (planModel.getSize() == 0) {
//                planModel.setSize(10);
//            }
//            //将planModel部分变量拆分传递给对象insert
//            NoticePlan noticePlan = new NoticePlan();
//
//            noticePlan.setId(planModel.getId());
//            noticePlan.setGmtCreate(planModel.getGmtCreate());
//            noticePlan.setGmtModified(planModel.getGmtModified());
////            noticePlan.setProfessor(planModel.getProfessor());
//            noticePlan.setType(planModel.getType());
//            noticePlan.setTitle(planModel.getTitle());
////            noticePlan.setFilepath(planModel.getFilepath());
////            noticePlan.setSuffixname(planModel.getSuffixname());
////            noticePlan.setContent(planModel.getContent());
//
//            //将planModel部分变量拆分传递给对象otherTime
//            OtherTime otherTime = new OtherTime();
//            otherTime.setSearch_string(planModel.getSearch_string());
//            otherTime.setS_breedingT(planModel.getS_breedingT());
//            otherTime.setS_gestationT(planModel.getS_gestationT());
//            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
//            otherTime.setS_cubT(planModel.getS_cubT());
//            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
//            otherTime.setS_nutritionT(planModel.getS_nutritionT());
//            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
//            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
//            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
//            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
//            otherTime.setS_breedingT1(planModel.getS_breedingT1());
//            otherTime.setS_breedingT2(planModel.getS_breedingT2());
//            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
//            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
//            otherTime.setS_gestationT1(planModel.getS_gestationT1());
//            otherTime.setS_gestationT2(planModel.getS_gestationT2());
//            otherTime.setS_cubT1(planModel.getS_cubT1());
//            otherTime.setS_cubT2(planModel.getS_cubT2());
//            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
//            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
//            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
//            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
//            otherTime.setDownloadPath(planModel.getDownloadPath());
//            otherTime.setPage(planModel.getPage());
//            otherTime.setSize(planModel.getSize());
//
//            Date gmtCreate1 = null;
//            Date gmtCreate2 = null;
//            Date gmtModified1 = null;
//            Date gmtModified2 = null;
//            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
//
//            NoticePlanExample noticePlanExample = new NoticePlanExample();
//            NoticePlanExample.Criteria criteria = noticePlanExample.createCriteria();
//            if (otherTime.getS_gmtCreate1() != null && !otherTime.getS_gmtCreate1().isEmpty() && otherTime.getS_gmtCreate2() != null && !otherTime.getS_gmtCreate2().isEmpty()){
//                gmtCreate1 =  formatter.parse(otherTime.getS_gmtCreate1());
//                gmtCreate2 =  formatter.parse(otherTime.getS_gmtCreate2());
//            }
//            if (otherTime.getS_gmtModified1() != null && !otherTime.getS_gmtModified1().isEmpty() && otherTime.getS_gmtModified2() != null && !otherTime.getS_gmtModified2().isEmpty()){
//                gmtModified1 =  formatter.parse(otherTime.getS_gmtModified1());
//                gmtModified2 =  formatter.parse(otherTime.getS_gmtModified2());
//            }
//            if(noticePlan.getGmtCreate() != null && !noticePlan.getGmtCreate().toString().isEmpty()){
//                criteria.andGmtCreateBetween(gmtCreate1,gmtCreate2);
//            }
//            if(noticePlan.getGmtModified() != null && !noticePlan.getGmtModified().toString().isEmpty()){
//                criteria.andGmtModifiedBetween(gmtModified1,gmtModified2);
//            }
//            if(noticePlan.getId() != null && !noticePlan.getId().toString().isEmpty()){
//                criteria.andIdEqualTo(noticePlan.getId());
//            }
////            if(noticePlan.getProfessor() != null && !noticePlan.getProfessor().isEmpty()){
////                criteria.andProfessorEqualTo(noticePlan.getProfessor());
////            }
//            if(noticePlan.getType() != null && !noticePlan.getType().toString().isEmpty()){
//                criteria.andTypeEqualTo(noticePlan.getType());
//            }
//            List<NoticePlan> selective = noticePlanService.findPlanSelective(noticePlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
//            if (selective == null) {
//                return Responses.errorResponse("查询错误");
//            }
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("notice_plan", selective);
//            data.put("size", selective.size());
//            response.setData(data);
//            return response;
//        }
//    }

    /**
     * 站内搜索接口：/inSite
     * 站内搜索方法名：searchInSite()
     * 接收的参数：用户在搜索栏输入的信息（字符串）
     * @return response
     */
    @Permit(authorities = "query_publishing")
    @GetMapping(value = "/inSite")
    public Response searchInSite(@RequestParam(value = "content", defaultValue = "") String content){
        logger.info("invoke searchInSite {}, url is notice/inSite", content);
        List<NoticePlan> selectInSite = noticePlanService.selectInSite("%" + content + "%");
        if (selectInSite == null) {
            return Responses.errorResponse("没有查到信息");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List", selectInSite);
        data.put("size", selectInSite.size());
        response.setData(data);
        return response;
    }

    /**
     * 上传接口：/upload
     * 上传方法名：uploadFile()
     * 接收的参数：用户浏览本地文件选择文件上传
     * @param request Request
     * @return response
     */
    @Permit(authorities = {"add_release", "modify_release"})
    @PostMapping(value = "/upload")
    public Response uploadFile(HttpServletRequest request){

        logger.info("invoke uploadFile, no params");

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String path = System.currentTimeMillis() + filename;
            System.out.println(path);
            try {
                String Header = FileUtil.getFileHeader(file);
                System.out.println(Header);

                if (!Header.equals("89504E47")

                        && !Header.equals("47494638")
                        // TIF文件格式
                        && !Header.equals("49492A00")
                        && !Header.equals("57415645")
                        // PDF文件格式
                        && !Header.equals("25504446")
                        // xls or doc文件格式
                        && !Header.equals("D0CF11E0")
                        && !Header.equals("41564920")
                        && !Header.equals("2E7261FD")
                        && !Header.equals("2E524D46")
                        && !Header.equals("000001BA")
                        && !Header.equals("6D6F6F76")
                        && !Header.equals("3026B275")
                        && !Header.equals("4D546864")
                        && !Header.equals("00000020")
                        && !Header.equals("8E66CF11")
                        // JPG文件格式
                        && !Header.equals("FFD8FFE0")
                        && !Header.equals("FFD8FFE1")
                        && !Header.equals("FFD8FFE8")
                        ) {
                    throw new Exception("文件格式错误,只允许上传图片！");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return Responses.errorResponse(e.getMessage());
            }
            if (!file.isEmpty()) {
                try {
                    noticePlanService.uploadFile(file.getBytes(), Constants.filePath, path);
                    Response response = Responses.successResponse();
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("List", Constants.serverUrl + path);
                    response.setData(data);
                    return response;
                } catch (Exception e) {
                    System.out.println("文件上传失败，请重新上传！");
                    return Responses.errorResponse(e.getMessage());
                }
            }
        }

        return Responses.errorResponse("上传失败");

    }

//    /**
//     * 下载接口：/download
//     * 下载方法名：downloadFile()
//     * 接收的参数：文件在服务器的相对路径
//     * @param otherTime
//     * @param bindingResult
//     * @param response
//     * @return
//     */

//    @PostMapping(value = "/download")
//    public String downloadFile(@RequestBody @Valid OtherTime otherTime, BindingResult bindingResult, HttpServletResponse response){
//        if (bindingResult.hasErrors()) {
//            System.out.println("下载文件失败，请重试!");
//        }else {
//            File file = new File(otherTime.getDownloadPath());
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
////                response.addHeader("Content-Disposition", "attachment;file=" + fileName);// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    System.out.println("success");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }
}

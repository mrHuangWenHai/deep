package com.deep.api.resource;

import com.alibaba.fastjson.JSON;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.*;
import com.deep.domain.service.EvaluationService;
import com.deep.domain.service.ExpressionService;
import com.deep.domain.service.MyWebSocket;
import com.deep.domain.service.TalkService;
import com.deep.domain.util.TalkFileUtil;
import com.deep.domain.util.WebSocketUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@MapperScan("com.example.demo.mapper")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class TalkController {
    protected static Logger logger = LoggerFactory.getLogger(TalkController.class);

    @Autowired
    TalkService talkService;
    @Autowired
    EvaluationService evaluationService;
    @Autowired
    ExpressionService expressionService;

    @PostMapping("/talk/upload")
    @ResponseBody
    public Response fileUpload(@RequestParam("file") MultipartFile file, String user_id, String user_name, String talk_id, boolean isExpert, String mode) {
        logger.info("fileUpload --- user_id:" + user_id + " user_name:" + user_name + " talk_id:" + talk_id + " isExpert:" + isExpert + " mode:" + mode);
        if (!TalkFileUtil.isSafeFile(file)) {
            logger.info("The file does not conform to the format.");
            return Responses.errorResponse("The file does not conform to the format.");
        }
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("-yyyy-MM-dd-HH-mm-ss-");
            String formatDate = format.format(date);
            String realName = file.getOriginalFilename();
            String fileName = user_id + formatDate + realName;
            String filePath = "../file/" + fileName;
            String realPath;
            if (!file.isEmpty()) {
                File pFile = new File(filePath);
                if (!pFile.getParentFile().exists()) {
                    pFile.getParentFile().mkdirs();
                }
                realPath = pFile.getAbsolutePath();
                System.out.println(realPath);
                try {
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(pFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    if (mode.equals("0")) {
                        logger.info("private file transport");
                        Long target_id = MyWebSocket.initPrivateState(Long.valueOf(user_id), Long.valueOf(talk_id), isExpert);
                        MyWebSocket role1 = WebSocketUtil.get(Long.valueOf(user_id));
                        MyWebSocket role2 = WebSocketUtil.get(Long.valueOf(talk_id));
                        if (role1 != null) {
                            role1.getSession().getAsyncRemote().sendText(com.alibaba.fastjson.JSON.toJSONString(new ResponseBean(realPath + ":" + realName, "link", talk_id, null)));
                            MyWebSocket.record(MyWebSocket.getPersonalMap().get(target_id), user_name, Long.valueOf(user_id), Long.valueOf(talk_id), filePath, date, true, true);
                        } else
                            MyWebSocket.record(MyWebSocket.getPersonalMap().get(target_id), user_name, Long.valueOf(user_id), Long.valueOf(talk_id), filePath, date, true, false);
                        if (role2 != null)
                            role2.getSession().getAsyncRemote().sendText(com.alibaba.fastjson.JSON.toJSONString(new ResponseBean(realPath + ":" + realName, "link", user_id, null)));
                    } else if (mode.equals("2")) {
                        logger.info("group file transport");
                        List<Long> members = new ArrayList<>(MyWebSocket.getChatMap().get(talk_id));
                        MyWebSocket.initGroupState(Long.valueOf(user_id), talk_id, members);
                        for (Long member : members) {
                            MyWebSocket account = WebSocketUtil.get(member);
                            if (account != null)
                                account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(filePath, "link", talk_id, null)));
                        }
                        MyWebSocket.record(talk_id, user_name, Long.valueOf(user_id), 0L, filePath, date, true, true);
                    }
                    return Responses.successResponse();
                } catch (IOException e) {
                    return Responses.errorResponse(e.getMessage());
                }
            }
        } catch (Exception e) {
            return Responses.errorResponse(e.getMessage());
        }
        return Responses.errorResponse("parameter error");
    }

    @GetMapping("/downloadFile")
    @ResponseBody
    public Response fileDownload(String file_path, HttpServletResponse res) {
        byte[] buff = new byte[1024];
        //String[] fileWords = file_path.split("/");
        //String fileName = fileWords[fileWords.length - 1];
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + file_path);
        BufferedInputStream bis = null;
        //BufferedOutputStream bos = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
//            File getFile = new File("D://downloadFile//" + fileName);
//            if (!getFile.getParentFile().exists()) {
//                getFile.getParentFile().mkdirs();
//            }
            bis = new BufferedInputStream(new FileInputStream(new File(file_path)));
            //bos = new BufferedOutputStream(new FileOutputStream(getFile));
            int i = bis.read(buff);
            while (i != -1) {
//                bos.write(buff, 0, buff.length);
//                bos.flush();
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
            return Responses.successResponse();
        } catch (IOException e) {
            return Responses.errorResponse(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("/evaluation")
    @ResponseBody
    //public Response evaluation(String expert_id, String expert_name, String evaluation, String description) {
    public Response evaluation(@RequestBody Evaluation evaluation) {
        //System.out.println(data);
        //Evaluation eval = JSON.parseObject(data, Evaluation.class);
        logger.info(JSON.toJSONString(evaluation));
        Calendar cale = Calendar.getInstance();
        cale.setTime(new Date());
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH);
        int season = getSeason(month);
        return evaluationService.evaluation_record(evaluation.getExpert_id(), evaluation.getExpert_name(), evaluation.getEvaluation(), evaluation.getDescription(), year, season, month + 1) > 0 ? Responses.successResponse() : Responses.errorResponse("insert error");
        //return evaluationService.evaluation_record(Long.valueOf(expert_id), expert_name, evaluation, description, year, season, month + 1) > 0 ? Responses.successResponse() : Responses.errorResponse("insert error");
    }

    @PostMapping("/expression")
    @ResponseBody
    //public Response expression(String expert_id, String expression) {
    public Response expression(@RequestBody Expression expression) {
        //Expression ex = JSON.parseObject(data, Expression.class);
        //return expressionService.expression_record(Long.valueOf(expert_id), expression) > 0 ? Responses.successResponse() : Responses.errorResponse("insert error");
        return expressionService.expression_record(Long.valueOf(expression.getExpert_id()), expression.getExpression()) > 0 ? Responses.successResponse() : Responses.errorResponse("insert error");
    }

    @GetMapping("/getExpression")
    @ResponseBody
    //public Response expression(String expert_id, String expression) {
    public Response getExpressions(String expert_id) {
        HashMap<String, List<Expression>> map = new HashMap<>();
        map.put("List", expressionService.getExpression(Long.valueOf(expert_id)));
        return map.get("List") != null ? Responses.successResponse(map) : Responses.errorResponse("no expression");
    }

//    @GetMapping("/getTalkRecords")
//    @ResponseBody
//    public Response summaryRecord(String user_id) {
//        List<String> record_ids = talkService.getTalkRecordIDs(Long.valueOf(user_id));
//        HashMap<String, List<Talk>> map = new HashMap<>();
//        for (String record_id : record_ids) {
//            List<Talk> talks = talkService.getSummaryTalkRecord(record_id);
//            map.put(record_id, talks);
//        }
//        return Responses.successResponse(map);
//    }

    @GetMapping("/getTalkRecord/{talk_id}")
    @ResponseBody
    public Response recordDetails(@PathVariable("talk_id") String talk_id) {
        String record_id = MyWebSocket.getChatMap().get(talk_id) == null ? MyWebSocket.getPersonalMap().get(Long.valueOf(talk_id)) : talk_id;
        HashMap<String, List<Talk>> map = new HashMap<>();
        map.put("List", talkService.getTalkRecord(record_id));
        return Responses.successResponse(map);
        //return talkService.getTalkRecord(record_id) != null ? Responses.successResponse(map) : Responses.errorResponse("no any records");
    }

    @GetMapping("/getClientList/{expert_id}")
    @ResponseBody
    public Response getClientList(@PathVariable("expert_id") String expert_id) {
        //List<Client> clients = new ArrayList<>();
        logger.info(expert_id + "get list");
        List<Client> list = talkService.getClientList(Long.valueOf(expert_id));
//        Set<Long> set = MyWebSocket.getPersonalMap().keySet();
//        if (set.size() > 0) {
//            for (Client client : list) {
//                if (set.contains(client.getId()))
//                    clients.add(client);
//            }
//        }

        HashMap<String, List<Client>> map = new HashMap<>();
        map.put("List", list);
        return Responses.successResponse(map);
    }

    private static int getSeason(int month) {
        int season = 0;
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

//    @PostMapping("/getLeaveWord")
//    @ResponseBody
//    public List<String> getLeaveWord(String receiver_id) {
//        List<Long> talker_ids = talkService.leaveWordSender(Long.valueOf(receiver_id));
//        if (talker_ids != null) {
//            List<String> wordList = new ArrayList<>();
//            for (Long talker_id : talker_ids) {
//                List<Talk> talks = talkService.leaveWord(talker_id, Long.valueOf(receiver_id));
//                String name = accountService.getName(talker_id);
//                for (Talk talk : talks) {
//                    String meta = JSON.toJSONString(new ResponseBean(talk.getContent(), null, talker_id.toString(), name));
//                }
//            }
//            talkService.changeStatus(Long.valueOf(receiver_id));
//            return wordList;
//        }
//        return null;
//    }
//
//    @PostMapping("/getGroup")
//    @ResponseBody
//    public String getGroup(String user_id) {
//        return new ArrayList<>(MyWebSocket.getAccountMap().get(Long.valueOf(user_id))).get(0);
//    }

}

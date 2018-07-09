package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.MobileAnnouncementModel;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.io.*;

/**
 * 用于主动发短信
 * create by zhongrui on 18-3-29.
 */
@RestController
public class PositiveSendResource {
    private Logger logger = LoggerFactory.getLogger(PositiveSendResource.class);

    private static String convertStreamToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
    /**
     * 手机号为手动输入
     * @param mobile 手机号
     * @param message  短信信息
     * @return  短信发送结果
     */
    @Permit(authorities = "send_messages")
    @RequestMapping(value = "/psend",method = RequestMethod.GET)
    public Response PositiveSend(@RequestParam(value = "mobile" ,defaultValue= "") String mobile,
                                 @RequestParam(value = "message",defaultValue = "") String message){

        logger.info("invoke positiveSend {}", mobile, message);
        if ("".equals(mobile) || "".equals(message)){
            return Responses.errorResponse("Lack Item");
        }
        MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(mobile,message);
        if(JedisUtil.manyMessageSendResult(mobileAnnouncementModel)){
            return JudgeUtil.JudgeSuccess("Send","Success");
        }else {
            return Responses.errorResponse("Send Error");
        }
    }

//    /**
//     * 手机号为文件上传
//     * @param mobile 手机号
//     * @param message  短信信息
//     * @return  短信发送结果
//     */
//    @RequestMapping(value = "/psendf",method = RequestMethod.POST)
//    public Response PositiveSend(@RequestParam("mobile") MultipartFile mobile,
//                                 @RequestParam(value = "message",defaultValue = "") String message) {
//        logger.info("invoke positiveSend {}", mobile, message);
//        if (mobile.isEmpty()|| "".equals(message)){
//            return Responses.errorResponse("Lack Item");
//        }
//        try {
//            //将文件内容转为字符串
//            String mobile_list = convertStreamToString(mobile.getInputStream());
//            System.out.println(mobile_list);
//            MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(mobile_list,message);
//            if(JedisUtil.manyMessageSendResult(mobileAnnouncementModel)){
//                return JudgeUtil.JudgeSuccess("Send","Success");
//            }else {
//                return Responses.errorResponse("Send Error");
//            }
//            //System.out.println(""+oo);
//            //System.out.println(""+oo.length());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Responses.errorResponse("Send Error");
//        }
//    }


}

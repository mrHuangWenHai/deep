package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.MobileAnnouncementModel;
import com.deep.domain.model.PositiveSendModel;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用于主动发短信
 * create by zhongrui on 18-3-29.
 */
@Controller
public class PositiveSendResource {


    private Logger logger = LoggerFactory.getLogger(PositiveSendResource.class);
    /**
     * positiveSendModel中手机号以","相隔
     * @param positiveSendModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/psend",method = RequestMethod.POST)
    public Response PositiveSend(@RequestBody PositiveSendModel positiveSendModel){

        logger.info("invoke positiveSend {}", positiveSendModel);
        MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(positiveSendModel.getMobile_list(),positiveSendModel.getMessage());
        mobileAnnouncementModel.testSendMany();
        mobileAnnouncementModel.testStatus();
        if(JedisUtil.manyMessageSendResult(mobileAnnouncementModel)){
            return JudgeUtil.JudgeSuccess("Send","Success");
        }else {
            return Responses.errorResponse("Send Error");
        }
    }
}

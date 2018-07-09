package com.deep.api.Utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

import java.util.Random;
@Component
public class MobileAnnouncementUtil {
    private String identityCode;    //随机生成验证码
    private String message;         //生成短信内容
    private String mobile;      //注册时填写手机号
    public MobileAnnouncementUtil(){

    }
    public MobileAnnouncementUtil(String mobile){
        this.identityCode = "";
        this.mobile = mobile;
        this.message ="验证码:";
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile_list(String mobile) {
        this.mobile = mobile;
    }

    public String testSend(){
        // just replace key here
        Random random = new Random();
        for(int i=0; i<6; i++){
            identityCode = identityCode+random.nextInt(10);
        }
        message = message+identityCode+"【东翔验证】";
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-c4e35f50b45ba853c3d6eff6633f42a7"));
        WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        //System.out.println("testSend中:"+mobile_list);
        formData.add("mobile", mobile);
        formData.add("message", message);
        ClientResponse response =  webResource.type(MediaType.APPLICATION_FORM_URLENCODED ).
                post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }

    public String testStatus(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-c4e35f50b45ba853c3d6eff6633f42a7"));
        WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        ClientResponse response =  webResource.get( ClientResponse.class );
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(status);
        //System.out.print(textEntity);
        return textEntity;
    }

}

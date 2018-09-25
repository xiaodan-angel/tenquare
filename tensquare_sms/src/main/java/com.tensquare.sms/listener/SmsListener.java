package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "SMS")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;
    @Value("${aliyun.sms.template_code}")
    private String template_code; //模板
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;//签名
    @RabbitHandler
    public void receiver(Map<String,String> map){
        String mobile = map.get("mobile");
        String checkCode = map.get("checkCode");
        System.out.println(mobile);
        System.out.println(checkCode);
        try {
            smsUtil.sendSms(mobile,template_code,sign_name,checkCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}

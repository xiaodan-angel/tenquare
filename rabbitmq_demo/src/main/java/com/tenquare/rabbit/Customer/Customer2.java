package com.tenquare.rabbit.Customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "itheima")
public class Customer2 {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("itheima接受到的消息是："+message);
    }
}

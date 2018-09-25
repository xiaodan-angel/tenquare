package com.tenquare.rabbit.Customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "budingyu")
public class Customer3 {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("budingyu接受到的消息是："+message);
    }
}

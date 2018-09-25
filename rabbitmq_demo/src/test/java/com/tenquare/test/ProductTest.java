package com.tenquare.test;

import com.tenquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend("itcast","我要红包");

    }
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("chuanzhi","","分列模式走起");
    }
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topicTest","goods.log","主题模式");
    }
}

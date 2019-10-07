package com.yain.boot.activemq.topic.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:57
 */
@Component
public class TopicConsumer {

    @JmsListener(destination = "${myTopic}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("*******消费者收到订阅的主题：" + textMessage.getText());
    }

}

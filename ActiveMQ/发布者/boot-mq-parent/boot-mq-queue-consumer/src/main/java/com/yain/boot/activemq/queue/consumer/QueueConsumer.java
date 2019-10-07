package com.yain.boot.activemq.queue.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:37
 */
@Component
public class QueueConsumer {

    @JmsListener(destination = "${myQueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("*******消费者收到消息：" + textMessage.getText());
    }

}

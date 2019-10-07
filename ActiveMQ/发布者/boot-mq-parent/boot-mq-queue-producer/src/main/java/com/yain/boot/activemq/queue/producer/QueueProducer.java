package com.yain.boot.activemq.queue.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:31
 */
@Component
public class QueueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "*****: " + UUID.randomUUID().toString().substring(0, 6));
    }

    /**
     * 间隔时间3秒钟定投
     */
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        produceMsg();
    }

}

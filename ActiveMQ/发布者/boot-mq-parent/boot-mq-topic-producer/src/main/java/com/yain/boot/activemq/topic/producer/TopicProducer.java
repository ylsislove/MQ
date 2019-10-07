package com.yain.boot.activemq.topic.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:43
 */
@Component
public class TopicProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    private String[] topicMsgs = {"特朗普联大批中国，外交部反击！",
            "国庆大阅兵，这个方队有27名将军！",
            "玉兔二号在月球背面发现不明胶状物质",
            "弹劾特朗普调查，正式启动",
            "突发判决：英国首相违法！"};

    private int msgIndex = 0;

    public void produceTopic() {
        if (msgIndex > 4) {
            msgIndex = 0;
        }
        String msg = "订阅消息：" + topicMsgs[msgIndex];
        jmsMessagingTemplate.convertAndSend(topic, msg);
        System.out.println(msg);
        msgIndex ++;
//        jmsMessagingTemplate.convertAndSend(topic, "订阅消息：" + UUID.randomUUID().toString().substring(0, 6));
    }

    @Scheduled(fixedDelay = 10000)
    public void produceTopicScheduled() {
        produceTopic();
    }
}

package com.boot.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 16:08
 */

public class ConfigConsumer {

    private static final String URL = "tcp://129.211.10.165:61616";
    private static final String TOPIC_NAME = "topic-1";

    private Connection connection = null;
    private Session session = null;
    private TopicSubscriber topicSubscriber = null;


    public void createConsumer(String name) throws JMSException {

        System.out.println("用户 " + name + " 已订阅");

        //1 创建连接工厂ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2 使用连接工厂创建连接
        connection = connectionFactory.createConnection();
        connection.setClientID(name);
        //4 创建会话
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5 创建消息发送的目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        topicSubscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();

        topicSubscriber.setMessageListener((message) -> {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    String msg = "用户 " + name + " 收到消息: " + textMessage.getText();
                    System.out.println(msg);
                    Map<String, List<String>> listMap = MessageList.getMessageMap();
                    List<String> list = listMap.get("name");
                    if (null == list) {
                        list = new ArrayList<>();
                        list.add(msg);
                        listMap.put(name, list);
                    }
                    else {
                        list.add(msg);
                    }

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void close() throws JMSException {
        topicSubscriber.close();
        session.close();
        connection.close();
    }

}

package com.yain.boot.activemq.queue.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:30
 */
@Component
@EnableJms
public class ConfigBean {

    @Value("${myQueue}")
    private String myQueue;

    /**
     * <bean id="..." class="...">
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }

}

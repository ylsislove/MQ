package com.yain.boot.activemq.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 发布/订阅模式的消费者
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:57
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

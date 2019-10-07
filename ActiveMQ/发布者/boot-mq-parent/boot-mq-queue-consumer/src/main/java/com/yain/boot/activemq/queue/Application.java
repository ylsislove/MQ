package com.yain.boot.activemq.queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消费者应用程序
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 3:36
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

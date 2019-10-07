package com.boot.jms.controller;

import com.boot.jms.config.ConfigConsumer;
import com.boot.jms.config.MessageList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 12:58
 */
@RestController
public class MainController {

    private Map<String, ConfigConsumer> map = new HashMap<>();

    @RequestMapping("/submitEmail")
    public void submitEmail(@RequestParam(name = "email") String email) {

        if (map.containsKey(email)) {
            return;
        }

        ConfigConsumer configConsumer = new ConfigConsumer();
        try {
            configConsumer.createConsumer(email);
            map.put(email, configConsumer);
            Map<String, List<String>> listMap = MessageList.getMessageMap();
            List<String> list = new ArrayList<>();
            listMap.put(email, list);

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/close")
    public void close(@RequestParam(name = "email") String email) {
        ConfigConsumer configConsumer = map.get(email);
        try {
            configConsumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        map.remove(email);
        System.out.println(email + " 已取消订阅");
    }

    @RequestMapping("/getMessage")
    public String getMessage(@RequestParam(name = "email") String email) {
        Map<String, List<String>> listMap = MessageList.getMessageMap();
        List<String> list = listMap.get(email);
        String msg;
        if (null != list && list.size() > 0) {
            msg = list.remove(0);
        }
        else {
            msg = "";
        }
        return msg;
    }

}

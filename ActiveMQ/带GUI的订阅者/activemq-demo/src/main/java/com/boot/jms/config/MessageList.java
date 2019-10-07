package com.boot.jms.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 17:35
 */
public class MessageList {

    private static Map<String, List<String>> messageMap = new HashMap<>();

    private MessageList() {}

    public static Map<String, List<String>> getMessageMap() {
        return messageMap;
    }

}

package com.boot.jms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/9/25 13:12
 */
@Controller
public class PageController {

    @RequestMapping("/hello")
    public String hello() {
        return "demo";
    }

}

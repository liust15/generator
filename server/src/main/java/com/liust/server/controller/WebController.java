package com.liust.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: server
 * @description:
 * @author: liust
 * @create: 2019-04-05 13:09
 **/
@Controller
public class WebController {

    @GetMapping("index")
    public String getIndex(){
        return "index";
    }
}

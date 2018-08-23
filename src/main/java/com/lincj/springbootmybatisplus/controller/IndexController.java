package com.lincj.springbootmybatisplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String gotoIndex(){
        return "index";
    }
}

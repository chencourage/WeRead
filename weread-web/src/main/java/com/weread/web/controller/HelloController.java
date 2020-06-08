package com.weread.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
public class HelloController {

	@RequestMapping("/hello")  
    public String hello(Map<String,Object> map){ 
        map.put("name", "[Angel -- 守护天使]");  
        return "hello";  
    }
}

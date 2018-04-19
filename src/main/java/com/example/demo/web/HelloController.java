package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class HelloController {

    @RequestMapping(value = "/hello_world", method = RequestMethod.GET)
    public String index(@RequestParam("user_id") Long userId) throws Exception {
        System.out.println("userId:" + userId);
        return "hello world";
    }
}

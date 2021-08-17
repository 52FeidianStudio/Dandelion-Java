package com.example.demo1.controller;


import cn.hutool.http.HttpRequest;
import com.example.demo1.service.ThreadTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class test1 {

    @Autowired
    ThreadTest threadTest;

    @ResponseBody
    @RequestMapping("/testredirect")
    public void testredirect(HttpServletRequest request ) {

        System.out.println("uri: "+request.getRequestURI());
        System.out.println("url: "+request.getRequestURL());
        System.out.println(request.getQueryString());
        String queryString = request.getQueryString();
        System.out.println("转换成功");
    }

    @RequestMapping("/test")
    public void test(){
        System.out.println(new Date());
        threadTest.update();
        System.out.println(new Date());
    }

}

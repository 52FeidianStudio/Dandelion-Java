package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;

@Controller
public class test {

    @ResponseBody
    @RequestMapping("/user/users")
    public void test1(@RequestParam( value = "test1",required = false ,defaultValue = " " )String str1,@RequestParam( value = "test2",required = false ,defaultValue = " " )String str2,HttpServletRequest request ) {
        System.out.println("url: "+request.getRequestURL());
        System.out.println("quary: "+request.getQueryString());
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("转换成功");
    }

    @ResponseBody
    @RequestMapping("/user/user")
    public void test2(@RequestParam( required = false ,defaultValue = " " )String str, HttpServletRequest request) {
        System.out.println("url: "+request.getRequestURL());
        System.out.println("quary: "+request.getQueryString());
        System.out.println(str);
    }

    @ResponseBody
    @RequestMapping("/user/users/{str1}/{str2}")
    public void test3(@PathVariable( required = false )String str1,@PathVariable( required = false )String str2){
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("转换成功");
    }

}

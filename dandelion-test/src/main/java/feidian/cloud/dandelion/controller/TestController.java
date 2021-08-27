package feidian.cloud.dandelion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:40
 * @description aaa
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class TestController {
    @RequestMapping("/test")
    public Object test() {
        System.out.println("请求成功");
        return "转发成功";
    }
}

package feidian.cloud.dandelion.controller;

import cn.hutool.core.lang.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:40
 * @description aaa
 */
@Slf4j
@RestController
public class TestController {

    @RequestMapping("/test")
    public Object test(String username, String password) {
        //System.out.println(map);
        return "转发成功";
    }

}

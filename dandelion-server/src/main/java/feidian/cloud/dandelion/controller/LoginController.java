package feidian.cloud.dandelion.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-27 19:33
 * @description
 */
@RestController
public class LoginController {
    @PostMapping("/login")
    public Object login(String username,String password) {
        HashMap<Object, Object> map = new HashMap<>();
        if (username.equals("dandelion")&&password.equals("dandelion")) {
            map.put("code",200);
        } else {
            map.put("code",400);
        }
        return map;
    }
}

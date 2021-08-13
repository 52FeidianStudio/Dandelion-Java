package feidian.cloud.autoconfigure.service;

import feidian.cloud.autoconfigure.peoperties.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;


/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 19:39
 * @description
 */
@Component
public class DandelionService {
    @Autowired
    ServerProperties serverProperties;
    @Autowired
    RestTemplate restTemplate;
    /**
     * 初始化的时候，检查服务端是否是开启状态
     * @return
     */
    @PostConstruct
    public static void getClient() {
        System.out.println("注册到Dandelion服务端");
        //String serverAddr = serverProperties.getServerAddr();
        ///**
        // * 将返回结果通过map解析
        // */
        //Map<String, String> result = restTemplate.getForObject(serverAddr, Map.class);
        ///**
        // * 如果连接超时就抛出异常
        // */
    }
}

package feidian.cloud.dandelion.dandelion;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.autoconfigure.peoperties.ServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 20:07
 * @description 这是客户端的服务类，用于向服务端传递配置信息，如果服务器不存在就抛出异常
 */
@Service
@Slf4j
public class DandelionService {
    @Autowired
    ServerProperties serverProperties;
    @Autowired
    RouteProperties routeProperties;
    /**
     * 初始化的时候，检查服务端是否是开启状态
     */
    @PostConstruct
    public void getClient() {
        String serverAddr = serverProperties.getServerAddr();
        try {
            String jsonStr = JSONUtil.toJsonStr(routeProperties);
            String result = HttpRequest.post(serverAddr+"/dandelion/client")
                    .body(jsonStr)
                    .timeout(3000)
                    .execute().body();
            Map<String, Integer> map = JSONUtil.toBean(result, Map.class);
            int code = map.get("code");
            //如果服务端返回的code是400表示转化成RouteDefinition的时候出了问题
            if (code==400) {
                throw new RuntimeException("配置信息有误");
            }
            log.info("Connect to Dandelion server successfully!");
        } catch (Exception e) {
            //如过连接超时就抛出异常
            e.printStackTrace();
            throw new RuntimeException("Connect to Dandelion server timeout!");
        }
    }
}

package feidian.cloud.dandelion.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-20 16:13
 * @description 通过getPostData可以获取axios原生POST请求参数
 */

public class ServletUtils {
    public static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}

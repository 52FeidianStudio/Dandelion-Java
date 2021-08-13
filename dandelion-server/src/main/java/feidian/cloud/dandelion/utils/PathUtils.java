package feidian.cloud.dandelion.utils;

import feidian.cloud.dandelion.controller.DandelionController;

import java.util.Set;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-10 17:17
 */

public class PathUtils {
    /**
     * 根据用户请求的路径，返回对应的path断言的路径，然后程序员就可以通过path获取到route对象了
     *
     * @return
     */
    public static String matchPath(String urlPath) {
        Set<String> pathKeySet = DandelionController.pathRouteMap.keySet();
        /**
         * 存放可能出现的路径断言列表，举个例子：
         * 访问/order/findById，这时候有两个路径断言：/order/**、/order/findById，我们应该匹配/order/findById
         * 不过仔细想想也没人会把这两个路径断言放在不同的服务，因此我们现在就先默认用户是正常的
         */
        for (String s : pathKeySet) {
            if (urlPath.startsWith(stripTail(s))) {
                return s;
            }
        }
        return null;
    }

    /**
     * 将路径的/**进行处理，返回干净的路径
     */
    public static String stripTail(String path) {
        if (path.endsWith("**")) {
            return path.substring(0, path.length() - 2);
        } else {
            return path;
        }
    }
}

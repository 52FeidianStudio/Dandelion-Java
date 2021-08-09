# 项目命名

![在这里插入图片描述](https://img-blog.csdnimg.cn/211f8a3a0d6645d397656702204f1218.png)

Dandelion是蒲公英的意思，蒲公英的形状与网关组件在服务中的地位类似，茎部代表用户入口，绒毛代表服务，网关的内部逻辑就是连接的部分

![在这里插入图片描述](https://img-blog.csdnimg.cn/2daae5e734f943ddac23c408d995d350.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzQ2NTIxNzg1,size_16,color_FFFFFF,t_70)

# 项目组成员介绍

| 姓名   | 方向 | 主要任务           |
| ------ | ---- | ------------------ |
| 张瑞龙 | 后端 | 架构设计、断言功能 |
| 赵永升 | 后端 | 心跳检查、URL解析  |
| 赵永升 | 后端 | starter的实现      |
| 李沛璋 | 前端 | 登录页、配置列表页 |

# 本次网关组件设计简介 

为了更加符合分布式组件的开发生态，本网关组件采用服务端和客户端分离的形式，某个服务通过引入feidian依赖，填写服务器配置，即可注册到网关组件的服务器。

网关组件会做成网页的形式，方便配置，由前端同学配合。配置分为三种类型：服务注册到网关组件服务器、用户在网页手动添加配置、导入保存的配置文件

在本人看来，网关组件在整个项目所处的地位是比较独立的，作为项目的入口，我觉得网关组件和浏览器的血缘关系，比整个项目的血缘关系更近一些，因此，在设计上网关只和服务有关联，不和其他组件产生关系

![在这里插入图片描述](https://img-blog.csdnimg.cn/5547d6fd32084757b5c42439245d482c.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzQ2NTIxNzg1,size_16,color_FFFFFF,t_70)

# 项目亮点

架构设计：

- 使用服务端和客户端分分离的设计思想，使得配置解耦

用户体验：

- 提供可视化界面形式进行配置 
- 提供了三种配置形式（服务写配置、用户自己写配置、导入保存的配置文件）
- 用户可以可动态修改配置，使得网关组件具有灵活性
- 内部j集成了断言过滤并且支持用户自己设计断言过滤
- 提供了一些基础的负载均衡和服务容错策略
- 用户可以选择是否与注册中心集成

# 一问一答

为什么不引入注册中心？

- 从注册中心那边拿到的信息很少，只有ip端口号
- 我的服务端还需要配置服务的服务名，配置又耦合回来了

为什么要多做心跳检查？

- 为用户提供更好的体验，服务一旦不可用了就直接剔除

为什么不使用注册中心的心跳检查？

- 并不是每个注册中心的服务都会配置到网关中
- 和注册中心对接起来不是很方便
- 从整体架构上看，本人想让网关组件设计的更独立一些，不产生不必要的关联

# 项目弊端

- 不支持自定义断言和过滤
- 目前不支持自定义fallback

# 项目规范

- 代码使用`Alibaba Java Coding Guidelines`插件进行规范
- Spring Boot版本使用`2.3.12.RELEASE`版本
- 要加入必要的类注释、方法注释、字段注释，类注释要添加个人信息（维权+溯源）

```java
/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-08 10:06
 */
/**
 * @author  Zhang Ruilong
 * @email  970586718@qq.com
 * @date  ${DATE} ${TIME}
 */
```

# 需要实现的基本功能

## 微服务配置（程家伟）

某个服务引入starter，在配置文件中配置相关属性，配置参考：

```yml
feidian: 
    gateway:
      discovery:
        server-addr: localhost:8848 # 将服务注册到gateway，这个是gateway的服务地址
      route:
        id: user_route							# 指定路由唯一标识
        uri: http://localhost:9999/				# 指定路由服务的地址，记得是uri不是url
        predicates:
          - Path=/user/**,/product	  # 指定路由规则，匹配以/user或以/product开头的请求
```


> 任务要点：starter的实现

## 下游服务暴露（程家伟）

对于下游的微服务接⼝路由，添加服务名作为前缀，暴露接⼝给外部，暴露格式：

```json
[
    # 暂时先return一下保存route的list
]
```

> 任务要点：字符串处理

## 请求转发（赵永升）

对于⼊⼝流量，⽹关本身则作为反向代理解析路由。

例如：⽹关暴露出去的 user 服务，对应的服务 URL 都是 http://ip:port/user/xxx 形式。当⽹关解析到这个 URL 的时候，提取出路由的 user，再从内部维护的 HashMap 映射关系找到现有的机器ip和port，并且把 URL 中的前缀 user 服务名去掉， 再将请求根据负载均衡策略转发到合适的机器即可，目前先创建几个spring boot项目，实现一个本机转发即可。没有合适的转发对象就xxx

```java
public class URLDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://i-beta.cnblogs.com/posts/edit;postId=12299874");
            System.out.println("URL为：" + url.toString());
            System.out.println("协议为：" + url.getProtocol());
            System.out.println("验证信息：" + url.getAuthority());
            System.out.println("文件名及请求参数：" + url.getFile());
            System.out.println("主机名：" + url.getHost());
            System.out.println("路径：" + url.getPath());
            System.out.println("端口：" + url.getPort());
            System.out.println("默认端口：" + url.getDefaultPort());
            System.out.println("请求参数：" + url.getQuery());
            System.out.println("定位位置：" + url.getRef());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

> 任务要点：URL类的使用、请求转发

# 拓展功能

## 心跳机制（赵永升）

客户端写一个接口，服务端通过这个接口定时检测后端服务是否可⽤，例如每5s发送⼀个请求，如果60s内收到响应即可证明服务正常，如果超时没有响应，则将这台机器从服务列表剔除。

交互数据示例：

```json
# 服务端通过get请求直接访问客户端

# 客户端返回数据格式
{
    "code":200
}
```

> 任务要点：使用quartz框架实现定时任务、服务端引入依赖后自动调用创建

## 断言与过滤（张瑞龙）

通过设计断言器和过滤器，实现请求的断言与过滤，目前我们先实现几个断言和过滤器，用户只能选用我们自己写好的内部断言器和过滤器，后续我再考虑如何让用户自定义配置

> 任务要点：设计模式

## 配置信息的持久化（张瑞龙）

因为从设计上来讲，我们还有两种添加配置的方式：从注册中心拉取和用户手动添加。如果每次启动，配置信息都丢失的话是很不友好的，我们应该提供本地持久化机制。

初步想法是生成一个JSON文件，如果用户指明了JSON文件地址就进行读取，如果发生了冲突（某个服务持久化的配置和配置文件写的配置不同），我们以服务提供的配置信息为主

> 任务要点：io操作

## 负载均衡策略（张瑞龙）

提供⾼扩展插件机制，⽀持多种负责均衡策略 ，仿照gateway，集成Ribbon组件，Ribbon组件可以根据服务名去注册中心上进行拉取，并且帮我们实现负载均衡，可以通过设置动态路由以及负载均衡转发配置，后面再考虑这个功能

> 任务要点：线程池、并发控制

## 服务容错（张瑞龙）

初步想法是：内部集成sentinel组件，实现熔断、降级和限流，或者参考sentinel前端设计样式和后端的设计模式，手写其实现的功能，最后再考虑给组件添加这个功能

> 任务要点：设计模式

# 成果考核

- 是否完成基本功能 
- 使⽤到哪些设计模式 
- 使⽤ jmeter 做压测


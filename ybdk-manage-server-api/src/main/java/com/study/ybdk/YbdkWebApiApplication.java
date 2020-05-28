package com.study.ybdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 如果不需要数据库需要过滤掉这两个东西
//  MongoDB虽然查询性能好但是消耗内存这里不考虑
//  统一异常处理 ==> 已解决
// 统一结果返回(controller直接返回实体类,用controllerAdvice来进行结构封装) ==> 已解决
// TODO： 缓存 *
// TODO: graphQL *
// TODO: 用FastDFS存储(熟悉fastdfs的部署，部署到本机上 )。
// TODO: 日志相关的知识需要学习 *
// TODO: 网关 **
// TODO: WebSocket ***
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableSwagger2
public class YbdkWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(YbdkWebApiApplication.class, args);
    }
}

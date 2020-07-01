---
title: SpringBoot使用Swagger2文档访问404错误
tags:
  - bug
  - springboot
  - swagger
categories:
  - bug解决集锦
copyright: true
keywords:
  - bug
  - springboot
  - swagger
description: "<pre>\t\t\t\t\tSpringBoot配置Swagger文档后访问Swagger404[已解决]</pre>"
abbrlink: 3a300b1
date: 2020-06-17 17:45:01
---

## 访问Swagger文档出现如下错误



{% asset_img psc.jpg 截图 %}

添加WebMvcConfigurer配置类 继承 WebMvcConfigurationSupport

~~~~java
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    /**
     * 重新指定静态资源
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
~~~~

## 再次访问Swagger文档即可


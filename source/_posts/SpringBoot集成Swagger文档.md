---
title: SpringBoot集成Swagger文档
tags:
  - SpringBoot
  - swagger
categories: SpringBoot
copyright: true
description: "<pre>\t\t\t\t通过代码生成接口文档，提供给其他开发人员使用</pre>"
abbrlink: 3c8d244d
date: 2020-07-07 14:03:57
keywords:
---

## 引入maven依赖

~~~pom
<!--swagger依赖-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>
~~~

## 创建Swagger配置类

~~~java

/**
 * . 创建配置类对 swagger进行基本的配置
 */
@ComponentScan(basePackages = {"com.sb.userlink.controller"})
@EnableSwagger2
@Configuration
public class Swagger extends WebMvcConfigurationSupport {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                /*
                访问swagger
                http://localhost:端口号/swagger-ui.html
                * */
                .title("标题")
                .description("详细说明")
                .version("1.0")
                .build();
    }
}
~~~

## Swagger常用注解

- @Api()用于类； 
  表示标识这个类是swagger的资源 
-  @ApiOperation()用于方法； 
  表示一个http请求的操作 
- @ApiParam()用于方法，参数，字段说明； 
  表示对参数的添加元数据（说明或是否必填等）  
-  @ApiModel()用于类 
  表示对类进行说明，用于参数用实体类接收 
-  @ApiModelProperty()用于方法，字段 
  表示对model属性的说明或者数据操作更改 
-  @ApiIgnore()用于类，方法，方法参数 
  表示这个方法或者类被忽略 
-  @ApiImplicitParam() 用于方法 
  表示单独的请求参数 
- @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam

~~~java
package com.sb.userlink.controller;

import com.sb.userlink.pojo.ItripUserLinkUser;
import com.sb.userlink.service.userlinkuser.ItripUserLinkUserService;
import com.sb.userlink.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Api(value = "UserLinkController", description = "接口文档")
public class UserLinkController {

    @Autowired
    ItripUserLinkUserService itripUserLinkUserService;

    @ApiOperation(value = "获取客户信息", notes = "返回Result对象")
    @GetMapping("/getUserLinkMap")
    public Result getUserLinkMap() {
        try {
            return Result.ok("获取数据成功", itripUserLinkUserService.getItripUserLinkUserList());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage(), null);
        }
    }

    @ApiOperation(value = "新增客户信息", notes = "返回业务实况")
    @PostMapping("/addItripUserLinkUser")
    public Result addUserLink(ItripUserLinkUser itripUserLinkUser) {
        try {
            itripUserLinkUserService.insertItripUserLinkUser(itripUserLinkUser);
            return Result.ok("添加成功", itripUserLinkUser);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage(), null);
        }
    }
}

~~~

## 访问swagger文档

http://localhost:端口号/项目名/swagger-ui.html

如图所示

{% asset_img m.jpg 访问swagger接口文档 %}

访问》》》[404解决方案](https://jzai.xyz/post/3a300b1.html)


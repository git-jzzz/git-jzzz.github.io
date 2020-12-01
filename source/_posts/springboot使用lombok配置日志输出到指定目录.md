---
title: springboot使用lombok配置日志输出到指定目录
tags:
  - SpringBoot
  - 日志
categories: SpringBoot
copyright: true
description: "<pre>\t\t\t\t\t使用lombok记录日志，控制台打印日志高亮，输出文件到指定目录</pre>"
abbrlink: f400a6d1
date: 2020-07-25 14:12:57
keywords:
---

## pom文件添加依赖

~~~pom
 		<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
           <version>1.18.12</version>
        </dependency>
~~~

## resources目录下创建logback.xml文件

~~~xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration scan="true" scanPeriod="60 seconds" debug="false">

    <contextName>logback</contextName>
    <!--定义日志文件的存储地址目录-->
    <property name="LOG_HOME" value="D:/logs/"/>


    <!--输出到控制台-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO</level>
        </filter>
        <withJansi>true</withJansi>
        <encoder>
            <!--<pattern>%d %p (%file:%line\)- %m%n</pattern>-->
            <!--格式化输出：%d:表示日期    %thread:表示线程名     %-5level:级别从左显示5个字符宽度  %msg:日志消息    %n:是换行符-->
            <pattern>%black(控制台-) %red(%d{yyyy-MM-dd HH:mm:ss}) %green([%thread]) %highlight(%-5level)
                %boldMagenta(%logger) - %cyan(%msg%n)
            </pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!--输出到文件-->
    <appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">

        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}logback.%d{yyyy-MM-dd-HH-mm}[%i].log</fileNamePattern>
            <maxFileSize>10kb</maxFileSize>
            <maxHistory>30</maxHistory>
            <totalSizeCap>1GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <!--格式化输出：%d:表示日期    %thread:表示线程名     %-5level:级别从左显示5个字符宽度  %msg:日志消息    %n:是换行符-->
            <pattern>文件记录-%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="console"/>
        <appender-ref ref="file"/>
    </root>

    <logger name="com.sxd.controller"/>
    <logger name="com.sxd.util.LogTestController" level="WARN" additivity="false">
        <appender-ref ref="console"/>
    </logger>

</configuration>
~~~

## 类上面注解@Slf4j   直接使用log记录日志:

{% asset_img psa.jpg 图片1 %}

{% asset_img psb.jpg 图片2 %}

## 效果展示:

{% asset_img psc.jpg 图片3 %}

{% asset_img psd.jpg 图片4 %}

{% asset_img pse.jpg 图片5 %}


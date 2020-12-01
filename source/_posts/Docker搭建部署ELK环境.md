---
title: docker搭建部署ELK环境-SpringBoot集成ELK
tags:
  - Docker
categories: 工具
copyright: false
description: "<pre>\t\t\t\tDocker快速部署ELK环境 SpringBoot日志集成</pre>"
abbrlink: e611022d
date: 2020-07-27 13:57:29
keywords:
---

## 使用pip安装docker-compose

~~~linux
yum -y install epel-release
yum -y install python-pip

#查看版本  
pip --version

#更新pip
pip  install --upgrade pip

#安装docker-compose
pip install docker-compose

#查看版本
docker-compose version


~~~

## 拉取ELK(elasticsearch   logstash   kibana)镜像

~~~linux
docker pull docker.elastic.co/elasticsearch/elasticsearch:7.1.1

docker pull docker.elastic.co/logstash/logstash:7.1.1

docker pull docker.elastic.co/kibana/kibana:7.1.1
~~~

## 创建一个目录存放docker-compose.yml文件  

### 注意yml格式缩进以及  复制时首行丢失

~~~linux
mkdir  /opt/elkDocker
cd /opt/elkDocker
vi docker-compose.yml   
~~~

~~~yml
version: "3.1"   #Docker版本   docker --version查看

services:
 elasticsearch:
  image: docker.elastic.co/elasticsearch/elasticsearch:7.1.1
  container_name: 'elasticsearch7.1.1'
  environment:
   - discovery.type=single-node
   - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
  volumes:
   - esdata:/usr/share/elasticsearch/data
  hostname: elasticsearch
  restart: always
  ports:
   - 9200:9200	
   - 9300:9300
 kibana:
  image: docker.elastic.co/kibana/kibana:7.1.1
  container_name: 'kibana7.1.1'
  environment:
   - elasticsearch.hosts=http://192.168.194.128:9200
  hostname: kibana
  depends_on:
   - elasticsearch
  restart: always
  ports:
   - "5601:5601"
 logstash:
  image: docker.elastic.co/logstash/logstash:7.1.1
  container_name: 'logstash7.1.1'
  hostname: logstash
  restart: always
  depends_on:
   - elasticsearch
  ports:
   - 9600:9600
   - 5044:5044
volumes:
 esdata:
  driver: local
~~~

## docker-compose.yml文件目录执行命令

~~~linux
docker-compose up -d

等待启动...   出现done即成功:
[root@192 elkDocker]# docker-compose up -d
/usr/lib64/python2.7/site-packages/cryptography/__init__.py:39: CryptographyDeprecationWarning: Python 2 is no longer supported by the Python core team. Support for it is now deprecated in cryptography, and will be removed in a future relse.
  CryptographyDeprecationWarning,
Starting elasticsearch7.1.1 ... done
Starting logstash7.1.1      ... done
Starting kibana7.1.1        ... done


~~~

## 查看容器

~~~linux
[root@192 elkDocker]# docker ps
CONTAINER ID        IMAGE                                                 COMMAND                  CREATED             STATUS              PORTS                                            NAMES
53b1c7fd8ae2        docker.elastic.co/logstash/logstash:7.1.1             "/usr/local/bin/dock…"   31 minutes ago      Up 7 seconds        0.0.0.0:5044->5044/tcp, 0.0.0.0:9600->9600/tcp   logstash7.1.1
98d13c114d90        docker.elastic.co/kibana/kibana:7.1.1                 "/usr/local/bin/kiba…"   31 minutes ago      Up 7 seconds        0.0.0.0:5601->5601/tcp                           kibana7.1.1
8b075e22e32a        docker.elastic.co/elasticsearch/elasticsearch:7.1.1   "/usr/local/bin/dock…"   31 minutes ago      Up 8 seconds        0.0.0.0:9200->9200/tcp, 0.0.0.0:9300->9300/tcp   elasticsearch7.1.1
~~~

## 打开浏览器,访问路径  http://ip:9200

返回以下json数据  启动成功

{% asset_img psa.jpg 图示1 %}

## 

## 访问路径  logstash：http://ip:9600  以及 kibana： http://ip:5601  

{% asset_img psb.jpg 图示2 %}

{% asset_img psc.jpg 图示3 %}

**ps：如果访问被拒   多半是防火墙的问题   可以开放端口   也可以关闭防火墙（个人使用）**

**启动会有时间   很大概率都在延迟  多刷新亿下下**

## Docker安装elasticsearch-head: 一个数据可视化工具

```linux
docker pullmobz/elasticsearch-head:5
.....省略
docker run -d -name es_admin -p 9100:9100 mobz/elasticsearch-head:5
#查看容器
docker ps

6d4cd3907f6b        mobz/elasticsearch-head:5                             "/bin/sh -c 'grunt s…"   27 hours ago        Up About an hour    0.0.0.0:9100->9100/tcp                           es_admin


```

**浏览器访问   http://ip:9100**

{% asset_img psd.jpg 图示4 %}

**填写自己的elasticsearch的ip地址  访问  连接失败 多半是跨域的问题 **

**解决方案：配置elasticsearch.yml 文件**

~~~linux
#拷贝配置文件至宿主机
docker cp contain_id:/usr/share/elasticsearch/config/elasticsearch.yml /tmp
cd /tmp
ls
vi elasticsearch.yml
#添加配置
http.cors.enabled: true
http.cors.allow-origin: "*"
#覆盖容器内配置
docker cp /tmp/elasticsearch.yml contain_id:/usr/share/elasticsearch/config/

#重启容器
docker restart contain_id
~~~

**重新访问即可   可能有延迟 多刷新**

## 配置logstash

~~~
docker cp contain_id:/usr/share/logstash/config/logstash-sample.conf /tmp
docker cp contain_id:/usr/share/logstash/config/pipelines.yml /tmp
scd /tmp
ls
vi logstash-sample.conf

# Sample Logstash configuration for creating a simple
# Beats -> Logstash -> Elasticsearch pipeline.

input {
  tcp {
      port => 5044
      codec => json_lines
  }
}

output {
  elasticsearch {
      action => "index"
      index => "%{[appname]}-%{+YYYY.MM.dd}"
      hosts => ["*.*.*.*:9200"]
  }
}
#*.*.*.*替换为elasticsearch的ip

vi pipelines.yml


- pipeline.id: main
  path.config: "/usr/share/logstash/config/logstash-sample.conf"
  

docker cp /tmp/logstash-sample.conf contain_id:/usr/share/logstash/config/
docker cp /tmp/pipelines.yml contain_id:/usr/share/logstash/config/

#重启容器
  

~~~

**path.config**指定自己配置的**conf文件路径**  大部分报错可能问题就出在这

**例如  SoringBoot 启动项目后  显示依已连接 ip:5044  但是kibana就是查询不到索引**

~~~linux
cd /opt/elkDocker 
docker-compose logs  #打印日志查看  好家伙  异常一大堆 如下:  网上搜 信息也是寥寥无几
~~~



***

~~~linux
logstash7.1.1    | [2020-07-29T02:38:18,466][INFO ][org.logstash.beats.BeatsHandler] [local: 172.18.0.4:5044, remote: 172.18.0.1:39412] Handling exception: org.logstash.beats.BeatsParser$InvalidFrameProtocolException: Invalid Frame Type, received: 64
logstash7.1.1    | [2020-07-29T02:38:18,466][WARN ][io.netty.channel.DefaultChannelPipeline] An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.
logstash7.1.1    | io.netty.handler.codec.DecoderException: org.logstash.beats.BeatsParser$InvalidFrameProtocolException: Invalid Frame Type, received: 64
logstash7.1.1    | 	at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:472) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.handler.codec.ByteToMessageDecoder.channelInputClosed(ByteToMessageDecoder.java:405) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.handler.codec.ByteToMessageDecoder.channelInputClosed(ByteToMessageDecoder.java:372) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.handler.codec.ByteToMessageDecoder.channelInactive(ByteToMessageDecoder.java:355) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:245) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.channel.AbstractChannelHandlerContext.access$300(AbstractChannelHandlerContext.java:38) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.channel.AbstractChannelHandlerContext$4.run(AbstractChannelHandlerContext.java:236) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.util.concurrent.DefaultEventExecutor.run(DefaultEventExecutor.java:66) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:897) [netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) [netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_212]
logstash7.1.1    | Caused by: org.logstash.beats.BeatsParser$InvalidFrameProtocolException: Invalid Frame Type, received: 64
logstash7.1.1    | 	at org.logstash.beats.BeatsParser.decode(BeatsParser.java:92) ~[logstash-input-beats-6.0.0.jar:?]
logstash7.1.1    | 	at io.netty.handler.codec.ByteToMessageDecoder.decodeRemovalReentryProtection(ByteToMessageDecoder.java:502) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:441) ~[netty-all-4.1.30.Final.jar:4.1.30.Final]
logstash7.1.1    | 	... 10 more
~~~

## 配置kibana.yml文件

~~~linux
docker cp contain_id:/usr/share/kibana/config/kibana.yml /tmp
cd /tmp
ls
vi kibana.yml


#
# ** THIS IS AN AUTO-GENERATED FILE **
#

# Default Kibana configuration for docker target
server.name: kibana
server.host: "0.0.0.0"
server.port: 5601
elasticsearch.hosts: [ "http://*.*.*.*:9200" ]
xpack.monitoring.ui.container.elasticsearch.enabled: true



#*.*.*.*修改为elasticsearch的ip

docker cp /tmp/kibana.yml contain_id:/usr/share/kibana/config/

#重启容器
~~~

## 创建SpringBoot项目 集成ELK日志管理

**pom文件**

~~~pom
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.2.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>ELKDemo</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
		</dependency>
		<dependency>
			<groupId>net.logstash.logback</groupId>
			<artifactId>logstash-logback-encoder</artifactId>
			<version>4.10</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>


~~~

**logback.xml**

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!--该日志将日志级别不同的log信息保存到不同的文件中 -->
<configuration>
    <appender name="LOGSTASH"
              class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <!-- 你的ip和logstash-sample.conf中配置的端口	-->
        <destination>ip:5044</destination>
        <!-- encoder必须配置,有多种可选 -->
        <encoder charset="UTF-8"
                 class="net.logstash.logback.encoder.LogstashEncoder" >
            <!-- "appname":"bootlogs" 的作用是指定创建索引的名字时用，并且在生成的文档中会多了这个字段
 logstash-sample.conf中有配置 -->
            <customFields>{"appname":"bootlogs"}</customFields>
        </encoder>
    </appender>

    <!-- ch.qos.logback.core.ConsoleAppender 表示控制台输出 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
            %d表示日期时间，
            %thread表示线程名，
            %-5level：级别从左显示5个字符宽度
            %logger{50} 表示logger名字最长50个字符，否则按照句点分割。
            %msg：日志消息，
            %n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ----> [%thread] ---> %-5level %logger{50} - %msg%n</pattern>
        </layout>
    </appender>

    <!--
        logger主要用于存放日志对象，也可以定义日志类型、级别
        name：表示匹配的logger类型前缀，也就是包的前半部分
        level：要记录的日志级别，包括 TRACE < DEBUG < INFO < WARN < ERROR
        additivity：作用在于children-logger是否使用 rootLogger配置的appender进行输出，
        false：表示只用当前logger的appender-ref，true：
        表示当前logger的appender-ref和rootLogger的appender-ref都有效
    -->
    <!-- hibernate logger -->
    <logger name="com.shawwang" level="debug" />
    <!-- Spring framework logger -->
    <logger name="org.springframework" level="debug" additivity="false"></logger>

    <root level="INFO">
        <appender-ref ref="stdout" />
        <appender-ref ref="LOGSTASH" />
    </root>
</configuration>
~~~

**测试**

~~~java
package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component

public class logTest {

    private Logger logger = LoggerFactory.getLogger(logTest.class);

    @Scheduled(fixedRate = 1000)
    public void logtest() {
        logger.trace("trace日志");
        logger.debug("debug日志");
        logger.info("info日志");
        logger.warn("warn日志");
        logger.error("error日志");
    }
}

~~~

**启动项目**

控制台打印信息

{% asset_img pse.jpg 图示5 %}

**访问kibana   如图所示  创建索引  查看数据**

{% asset_img psf.jpg 图示6 %}

{% asset_img psg.jpg 图示7 %}

{% asset_img psh.jpg 图示8 %}






---
title: Docker 安装 Rabbitmq
tags:
  - Docker
categories: Docker
copyright: false
description: "<pre>\t\t\t\t\t	使用Docker安装Rabbitmq</pre>"
abbrlink: b3b7cc65
date: 2020-07-24 11:17:23
keywords:
---

## 访问[hub.docker.com](https://hub.docker.com/)查看需要的版本

选择带有management的  有web管理

~~~linux
[root@192 /]# docker pull rabbitmq:3.7.27-rc.1-management-alpine
3.7.27-rc.1-management-alpine: Pulling from library/rabbitmq
df20fa9351a1: Pull complete 
6a4ed0140701: Pull complete 
6d5c7c78b67f: Pull complete 
7ea126e61bcf: Pull complete 
0be712904441: Pull complete 
11ecc3358c20: Pull complete 
458347edfc65: Pull complete 
b1878f623a16: Pull complete 
ed6d2988e61a: Pull complete 
Digest: sha256:f5bdefeb16b294796bc7ea0b03ca2d2b3c2507ba812b59e48d856108ecf622af
Status: Downloaded newer image for rabbitmq:3.7.27-rc.1-management-alpine
~~~

## 查看镜像

~~~linux
[root@192 /]# docker images
REPOSITORY          TAG                             IMAGE ID            CREATED             SIZE
rabbitmq            3.7.27-rc.1-management-alpine   d2e6c12987b8        9 days ago          144MB
~~~

## 创建并启动容器  后台挂起

~~~linux
[root@192 /]# docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:3.7.27-rc.1-management-alpine
2888f6d4a75bab5640955b2548e798ea0dd5a7931464a531b7fa5801dff4b2f0
~~~

## 查看运行中容器

~~~linux
[root@192 /]# docker ps
CONTAINER ID        IMAGE                                    COMMAND                  CREATED             STATUS              PORTS                                                                                        NAMES
2888f6d4a75b        rabbitmq:3.7.27-rc.1-management-alpine   "docker-entrypoint.s…"   11 seconds ago      Up 7 seconds        4369/tcp, 5671/tcp, 0.0.0.0:5672->5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15672->15672/tcp   rabbitmq
c2d8883595f4        mysql:5.7                                "docker-entrypoint.s…"   2 hours ago         Up 2 hours          33060/tcp, 0.0.0.0:3307->3306/tcp                                                            mysql01
~~~



## 访问Rabbitmq管理界面

~~~http
http://{ip}:15672
用户名/密码   guest/guest
~~~

{% asset_img psc.jpg web界面 %}

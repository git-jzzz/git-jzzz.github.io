---
title: Docker命令attach卡住解决
tags:
  - Docker
  - bug解决大全
categories: Docker
copyright: true
description: "<pre>\t\t\t\t解决docker attach进入一个打开的容器卡住</pre>"
abbrlink: '61750088'
date: 2020-07-24 10:25:14
keywords:
---

## 解决

 当启动容器时加入 -d  参数  用户则无法直接进入容器中

  因为此时容器运行的进程是ssh  而不是/bin/bash

使用docker exec -it containerId /bin/bash进入



## run、attach、exec区别

run  用于创建容器  每次使用会创建一个新的容器

attach通过连接stdin，连接到容器内输入输出流，会在输入`exit`后终止进程.

exec 会连接到容器，可以像SSH一样进入容器内部，进行操作，可以通过`exit`退出容器，不影响容器运行。



可使用快捷键ctrl+P+q 使容器在后台运行
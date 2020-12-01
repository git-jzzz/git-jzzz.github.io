---
title: 本机能ping虚拟机 telnet不到虚拟机端口
tags:
  - linux
  - net
  - bug
categories:
  - bug解决大全
description: "<pre>\t\t\t\tcentos7设置固定ip后网络正常访问，能ping到ip，telnet不到具体端的问题。</pre>"
abbrlink: a7ec9c11
date: 2020-06-29 11:41:21
copyright: false
keywords:
---

## 虚拟机能ping通 连接不上具体端口

在本机cmd窗口执行命令

~~~cmd
ipconfig/all
~~~

查看vmnet8的端口ip

查看虚拟机ip

~~~cmd
ifconfig
~~~

发现ip相同  冲突了 。

## 修改本机vmnet8ip

打开windows系统设置-更改网络适配器-右键属性

{% asset_img ip.jpg ip设置 %}

修改ip  例如虚拟机ip为192.169.194.122 

物理ip则修改为192.169.194.123即可

重启vmnet8服务后问题解决


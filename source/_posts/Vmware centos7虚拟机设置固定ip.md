---
title: centos7虚拟机设置固定ip
tags:
  - centos
  - 静态ip
copyright: true
description: "<pre>\t\t\t\t在程序中使用虚拟机时，经常发现连接不上，检查后发现是虚拟机自动获取的原因。<br/>\t\t\t\t只有一两个的话，修改即可，代码多了后就有点力不从心了，所以可以修改虚拟机ip为固定的ip</pre>"
abbrlink: 72a467c6
date: 2020-06-30 08:50:35
categories: 工具
keywords:
---



[链接直达](https://www.cnblogs.com/lfhappy/p/10798400.html)

 ~~~linux
 #编辑网络配置
 vi /etc/sysconfig/network-scripts/ifcfg-ens33
 ~~~

~~~txt
TYPE=Ethernet
BOOTPROTO=static
DEFROUTE=yes
PEERDNS=yes
PEERROUTES=yes
IPV4_FAILURE_FATAL=no
#IPV6INIT=yes
#IPV6_AUTOCONF=yes
#IPV6_DEFROUTE=yes
#IPV6_PEERDNS=yes
#IPV6_PEERROUTES=yes
#IPV6_FAILURE_FATAL=no
#IPV6_ADDR_GEN_MODE=stable-privacy
#static ip
IPADDR=192.168.194.***
NETMASK=255.255.255.0
GATEWAY=192.168.194.2
DNS1=192.168.194.2

IPV6_PEERDNS=yes
IPV6_PEERROUTES=yes
ZONE=

NAME=ens33
UUID=2b5cf656-32da-406f-bfcb-97fcaf9570b0
DEVICE=ens33
ONBOOT=yes

~~~

~~~linux
#重启网络配置
systemctl restart network
ip addr   
[root@192 ~]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN qlen 1
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: ens33: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP qlen 1000
    link/ether 00:0c:29:9a:14:dc brd ff:ff:ff:ff:ff:ff
    inet 192.168.194.***/24 brd 192.168.194.255 scope global ens33
       valid_lft forever preferred_lft forever
    inet6 fe80::20c:29ff:fe9a:14dc/64 scope link 
       valid_lft forever preferred_lft forever
[root@192 ~]# 
~~~


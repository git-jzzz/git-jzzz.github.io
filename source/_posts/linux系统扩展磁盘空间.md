---
title: linux系统扩展磁盘空间
tags: 
  - linux
  - 磁盘
categories: bug解决大全
copyright: true
description："<pre>\t\t\t\tVmware使用docker拉取镜像时失败: no space left on device</pre>"
abbrlink: 270c8d8b
date: 2020-07-27 09:22:55
keywords:
---

##  no space left on device:磁盘空间不足

## 扩展磁盘空间

### 在系统关机状态扩展系统的磁盘空间 shutdown -h now

{% asset_img psa.jpg 图示 %}

### 打开虚拟机，登录root用户，执行命令fdisk -l  查看扩展后的结果

{% asset_img psb.jpg 图示2 %}

### 从主磁盘空间创建新的分区，fdisk /dev/sda  依次执行命令  w保存

{% asset_img psc.jpg 图示3 %}

### 检查执行结果  fdisk -l

{% asset_img psd.jpg 图示4 %}

### 重启系统  格式化新的分区  mkfs.ext3 /dev/sda3

{% asset_img pse.jpg 图示5 %}

### 进入lvm管理  pvcreate /dev/sda3

{% asset_img psf.jpg 图示6 %}

#### 查看物理卷组  记录VGName

{% asset_img psg.jpg 图示7 %}

### 将初始化的分区加入虚拟卷组  vgextend el /dev/sda3

{% asset_img psh.jpg 图示8 %}

#### 查看磁盘 已成功添加

{% asset_img psi.jpg 图示9 %}

### 扩展已有卷的容量    以你添加的容量为准  lvextend -: +numG /dev/mapper/cl-root

{% asset_img psj.jpg 图示10 %}

### 查看添加结果

{% asset_img psk.jpg 图示11 %}

### 查看df -h  查看大小无变化

{% asset_img psk.jpg 图示11 %}

#### 使用命令进行同步 xfs_growfs /dev/mapper/cl-root

{% asset_img psn.jpg 图示12 %}
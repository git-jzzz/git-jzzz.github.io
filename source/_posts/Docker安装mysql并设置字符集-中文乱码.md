---
title: Docker安装mysql并设置字符集(中文乱码)
tags:
  - Docker
  - mysql
categories:
  - Docker
copyright: false
description: "<pre>\t\t\t\tDocker安装mysql，快速部署，设置字符集防止中文乱码</pre>"
abbrlink: 5655b191
date: 2020-07-24 09:35:45
keywords:
---

## Docker安装mysql

~~~linux
docker search mysql    #查找mysql   
~~~

可在 [hub.docker](https://hub.docker.com)查看详细版本信息

~~~linux
docker pull mysql:5.7    #选择版本  拉取镜像   不指定版本会拉取最新版本  latest
mkdir -p /data/mysql01		#创建同步mysql的文件夹
docker run --name mysql01 -p 3307:3306 -v /data/mysql01:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7

# -v 容器内的 /var/lib/mysql 在宿主机上 /data/mysql01 做映射  
# -e MYSQL_ROOT_PASSWORD 设置初始密码
# -p 将宿主机3307的端口映射到容器3306端口

~~~

从MySQL8.0 开始，默认的加密规则使用的是 caching_sha2_password  需要修改加密算法

~~~linux
mysql> grant all PRIVILEGES on *.* to root@'%' WITH GRANT OPTION;
Query OK, 0 rows affected (0.01 sec)
 
mysql> ALTER user 'root'@'%' IDENTIFIED BY '123456' PASSWORD EXPIRE NEVER;
Query OK, 0 rows affected (0.11 sec)
 
mysql> ALTER user 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
Query OK, 0 rows affected (0.11 sec)
 
mysql>  FLUSH PRIVILEGES;
Query OK, 0 rows affected (0.01 sec)
~~~

## 修改字符集

登录mysql查看字符集  默认为latin1

~~~linux
mysql> show variables like'%char%';
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | latin1                     |
| character_set_connection | latin1                     |
| character_set_database   | latin1                     |
| character_set_filesystem | binary                     |
| character_set_results    | latin1                     |
| character_set_server     | latin1                     |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+
8 rows in set (0.00 sec)
~~~

### 查找my.cnf文件位置

~~~linux
[root@192 etc]# docker exec -it c2d8883595f4 /bin/bash
root@c2d8883595f4:/# ls 
bin   docker-entrypoint-initdb.d  home	 media	proc  sbin  tmp
boot  entrypoint.sh		  lib	 mnt	root  srv   usr
dev   etc			  lib64  opt	run   sys   var
root@c2d8883595f4:/# cd /etc
root@c2d8883595f4:/etc# ls
X11			hostname       mtab	      resolv.conf
adduser.conf		hosts	       mysql	      rmt
alternatives		init.d	       nsswitch.conf  securetty
apt			inputrc        opt	      security
bash.bashrc		issue	       os-release     selinux
bindresvport.blacklist	issue.net      pam.conf       shadow
cron.daily		kernel	       pam.d	      shadow-
debconf.conf		ld.so.cache    passwd	      shells
debian_version		ld.so.conf     passwd-	      skel
default			ld.so.conf.d   perl	      ssl
deluser.conf		ldap	       profile	      subgid
dpkg			libaudit.conf  profile.d      subuid
environment		localtime      rc0.d	      systemd
fstab			logcheck       rc1.d	      terminfo
gai.conf		login.defs     rc2.d	      timezone
group			logrotate.d    rc3.d	      update-motd.d
group-			machine-id     rc4.d	      xattr.conf
gshadow			mecabrc        rc5.d
gshadow-		mke2fs.conf    rc6.d
host.conf		motd	       rcS.d
root@c2d8883595f4:/etc# cd mysql
root@c2d8883595f4:/etc/mysql# ls
conf.d	my.cnf	my.cnf.fallback  mysql.cnf  mysql.conf.d
root@c2d8883595f4:/etc/mysql# cd conf.d
root@c2d8883595f4:/etc/mysql/conf.d# ls
docker.cnf  mysql.cnf  mysqldump.cnf
root@c2d8883595f4:/etc/mysql/conf.d# exit
~~~

### cp容器文件到物理机

~~~linux
[root@192 etc]# docker cp mysql01:/etc/mysql/conf.d/mysql/cnf /tmp
Error: No such container:path: mysql01:/etc/mysql/conf.d/mysql/cnf
[root@192 etc]# docker cp mysql01:/etc/mysql/conf.d/mysql.cnf  /tmp
[root@192 etc]# cd /tmp
[root@192 tmp]# ls
mysql.cnf                                                                vmware-root_655-4021587944  vmware-root_686-2689274894   vmware-root_9726-2824408292                yum_save_tx.2020-07-22.03-00.wpUrcx.yumtx
systemd-private-14b3f32ece2e42be8035dd16f67c30d9-chronyd.service-RWHYU3  vmware-root_682-2697467275  vmware-root_9706-2865892209  yum_save_tx.2020-07-22.02-59.A1CPz5.yumtx

~~~

### vi 编辑mysql.cnf

~~~linux
[root@192 tmp]# vi mysql.cnf
~~~

~~~cnf
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
~~~

esc  :wq退出

### 将mysql.cnf文件拷贝回容器

~~~linux
[root@192 tmp]# docker cp /tmp/mysql.cnf mysql01:/etc/mysql/conf.d/
[root@192 tmp]# docker restart mysql01   #重启容器  使配置生效
~~~

### 进入容器运行mysql查看字符集

~~~linux
[root@192 tmp]# docker exec -it c2d8883595f4 /bin/bash
root@c2d8883595f4:/# mysql -uroot -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 2
Server version: 5.7.31 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show variables like '%char%';
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | utf8                       |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+
8 rows in set (0.00 sec)
~~~


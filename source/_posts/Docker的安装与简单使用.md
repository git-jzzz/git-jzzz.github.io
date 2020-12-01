---
title: Docker的安装与简单使用
tags: 工具
categories:
  - Docker
  - 工具
copyright: false
description: "<pre>\t\t\t\tDocker是一个虚拟环境容器，可以将你的开发环境、代码、配置文件等一并打包到这个容器中,并发布到任意平台中</pre>"
abbrlink: 830c686a
date: 2020-07-23 09:32:38
keywords:
---

## Docker的三个概念

#### 镜像（Image）：

类似于虚拟机中的镜像，是一个包含有文件系统的面向Docker引擎的只读模板。任何应用程序运行都需要环境，而镜像就是用来提供这种运行环境的。例如一个Ubuntu镜像就是一个包含Ubuntu操作系统环境的模板，同理在该镜像上装上Apache软件，就可以称为Apache镜像。

#### 容器（Container）：

类似于一个轻量级的沙盒，可以将其看作一个极简的Linux系统环境（包括root权限、进程空间、用户空间和网络空间等），以及运行在其中的应用程序。Docker引擎利用容器来运行、隔离各个应用。容器是镜像创建的应用实例，可以创建、启动、停止、删除容器，各个容器之间是是相互隔离的，互不影响。注意：镜像本身是只读的，容器从镜像启动时，Docker在镜像的上层创建一个可写层，镜像本身不变。

#### 仓库（Repository）：

类似于代码仓库，这里是镜像仓库，是Docker用来集中存放镜像文件的地方。注意与注册服务器（Registry）的区别：注册服务器是存放仓库的地方，一般会有多个仓库；而仓库是存放镜像的地方，一般每个仓库存放一类镜像，每个镜像利用tag进行区分，比如Ubuntu仓库存放有多个版本（12.04、14.04等）的Ubuntu镜像。

## Docker的安装与卸载

Docker可以安装在Windows、linux、Mac等多个平台上，详细查看文档

[Install Docker Engine](https://docs.docker.com/engine/install/)

### centos:

- #### 更新yum包    

- yum update       (升级所有包同时升级软件和系统内核   生产环境慎用)

- yum upgrade     (只升级所有包，不升级软件和系统内核 )

~~~linux
yum update
~~~

-y  表示不询问，使用默认配置进行安装

#### 安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的

~~~linux
[root@localhost ~]# yum install -y yum-utils device-mapper-persistent-data lvm2
~~~

#### 设置yum源

~~~linux
[root@localhost ~]# yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
~~~

#### 查看仓库中所有docker版本

~~~linux
[root@192 ~]# yum list docker-ce --showduplicates | sort -r
已加载插件：fastestmirror, product-id, search-disabled-repos, subscription-manager
可安装的软件包
 * updates: ftp.sjtu.edu.cn
This system is not registered with an entitlement server. You can use subscription-manager to register.
Loading mirror speeds from cached hostfile
 * extras: ftp.sjtu.edu.cn
docker-ce.x86_64            3:19.03.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.7-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.6-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.5-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.4-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.3-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.2-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.1-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.12-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.11-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.10-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.0-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.7-3.el7                     docker-ce-stable

~~~

#### 选择版本安装 yum install docker-ce-version

~~~linux
[root@192 ~]# yum install docker-ce-18.03.1.ce
已加载插件：fastestmirror, product-id, search-disabled-repos, subscription-manager

This system is not registered with an entitlement server. You can use subscription-manager to register.

Loading mirror speeds from cached hostfile
 * base: ftp.sjtu.edu.cn
 * extras: ftp.sjtu.edu.cn
 * updates: ftp.sjtu.edu.cn
正在解决依赖关系
--> 正在检查事务
---> 软件包 docker-ce.x86_64.0.18.03.1.ce-1.el7.centos 将被 安装
--> 正在处理依赖关系 pigz，它被软件包 docker-ce-18.03.1.ce-1.el7.centos.x86_64 需要
--> 正在检查事务
---> 软件包 pigz.x86_64.0.2.3.3-1.el7.centos 将被 安装
--> 解决依赖关系完成

依赖关系解决
.........................
安装  1 软件包 (+1 依赖软件包)

总下载量：35 M
安装大小：35 M
Is this ok [y/d/N]: y

~~~





#### 安装之后查看版本信息  出现 Client  Server安装成功

~~~linux
[root@localhost ~]# docker version
Client:
 Version:      18.03.1-ce
 API version:  1.37
 Go version:   go1.9.5
 Git commit:   9ee9f40
 Built:        Thu Apr 26 07:20:16 2018
 OS/Arch:      linux/amd64
 Experimental: false
 Orchestrator: swarm

Server:
 Engine:
  Version:      18.03.1-ce
  API version:  1.37 (minimum version 1.12)
  Go version:   go1.9.5
  Git commit:   9ee9f40
  Built:        Thu Apr 26 07:23:58 2018
  OS/Arch:      linux/amd64
  Experimental: false
[root@localhost ~]# 

~~~

#### 启动Docker

~~~linux
[root@192 ~]# systemctl start docker
~~~

#### 查看镜像验证启动  (刚下载  无镜像)

~~~linux
[root@192 ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
[root@192 ~]# 
~~~

#### 开机启动docker

~~~linux
[root@192 ~]# systemctl enable docker
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.

~~~

### [Docker卸载](https://www.cnblogs.com/kingsonfu/p/11582495.html)

Docker 帮助信息

~~~linux
docker --help
~~~

## Docker关于镜像的基本操作

Docker每个仓库会有多个镜像，用tag表示，如果不加tag，默认使用latest镜像

### 搜索centos镜像并下载已有镜像

~~~linux
[root@localhost ~]# docker search centos
NAME                               DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
centos                             The official build of CentOS.                   6100                [OK]                
ansible/centos7-ansible            Ansible on Centos7                              132                                     [OK]
consol/centos-xfce-vnc             Centos container with "headless" VNC session…   117                                     [OK]
jdeathe/centos-ssh                 OpenSSH / Supervisor / EPEL/IUS/SCL Repos - …   115                                     [OK]
centos/systemd                     systemd enabled base container.                 86                                      [OK]
centos/mysql-57-centos7            MySQL 5.7 SQL database server                   77                                      
imagine10255/centos6-lnmp-php56    centos6-lnmp-php56                              58                                      [OK]
tutum/centos                       Simple CentOS docker image with SSH access      47                                      
[root@localhost ~]# docker pull centos
Using default tag: latest
latest: Pulling from library/centos
6910e5a164f7: Pull complete 
Digest: sha256:4062bbdd1bb0801b0aa38e0f83dece70fb7a5e9bce223423a68de2d8b784b43b
Status: Downloaded newer image for centos:latest

~~~

### 新建自有镜像

利用镜像启动一个容器后进行修改   利用commit提交更新后的副本

~~~linux
[root@localhost ~]# docker run -it centos:latest /bin/bash   #启动容器
[root@ea288062adff /]# docker				#root@后面参数变了  进入了容器
bash: docker: command not found  			#容器中没有docker了
[root@ea288062adff /]# git -version			
bash: git: command not found				#同理 git也没有了
[root@ea288062adff /]# yum install git     #yum安装git
Failed to set locale, defaulting to C.UTF-8
CentOS-8 - AppStream                                                                                                                                                                                             1.2 MB/s | 5.8 MB     00:04    
CentOS-8 - Base                                                                                                                                                                                                  471 kB/s | 2.2 MB     00:04    
CentOS-8 - Extras                                                                                                                                                                                                7.1 kB/s | 7.0 kB     00:00    
Dependencies resolved.
~~~

~~~linux
[root@ea288062adff /]# git --version
git version 2.18.4					#安装后已经有git了
[root@ea288062adff /]# exit			#exit退出容器
~~~

退出后查看Docker中运行的容器

~~~linux
[root@localhost ~]# docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                     PORTS               NAMES
ea288062adff        centos:latest       "/bin/bash"              2 minutes ago       Exited (0) 4 seconds ago                       goofy_yonath
567a31230ca6        mysql:latest        "docker-entrypoint.s…"   9 minutes ago       Exited (0) 4 minutes ago                       hungry_goodall
~~~

将容器转换为一个镜像，执行commit操作，完成后使用docker images查看

~~~linux
[root@localhost ~]# docker commit -m "centos with git" -a "gitjzz" ea288062adff gitjzz/centos:git
sha256:3fcad1be9818ef2cb6a975f499a4547e1080f136752af9660c57d13b930546f9
[root@localhost ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
gitjzz/centos       git                 3fcad1be9818        12 seconds ago      332MB
mysql               5.7                 8679ced16d20        5 hours ago         448MB
mysql               latest              e3fcc9e1cc04        5 hours ago         544MB
centos              latest              831691599b88        5 weeks ago         215MB

~~~

-m 指定说明信息   -a指定用户信息  ea288062adff 代表容器id   gitjzz/centos:git 指定目标镜像的用户名、仓库名和tag信息。此时Docker引擎中就有了新建的镜像gitjzz/centos:git   与之前的centos镜像多了个git工具

~~~linux
[root@localhost ~]# docker run  gitjzz/centos:git /bin/bash
[root@61bdaf0a9695 /]# git --version
git version 2.18.4
~~~



## Docker关于容器的基本操作

### 基于镜像启动一个容器

~~~linux
[root@localhost ~]# docker run -it centos:latest /bin/bash   #启动容器
~~~

-it是两个参数  -i和-t   -i:表示打开并保持stdout   -t:分配一个终端(pseudo-tty)

exit  退出  容器状态处于Exit   不是后台运行  让容器后台运行  使用快捷键ctrl+p+q退出  此时容器状态为Up

### -d后台运行

~~~linux
[root@localhost ~]# docker run -d centos:latest /bin/bash   #启动容器
~~~

使用了-d参数，使这个容器处于后台运行的状态，不会对当前终端产生任何输出，所有的stdout都输出到log，可以使用docker logs container_name/container_id查看

### 启动、停止、重启容器命令

~~~linux
[root@localhost ~]# docker start ba0f7e1e3f0d
ba0f7e1e3f0d
[root@localhost ~]# docker stop ba0f7e1e3f0d
ba0f7e1e3f0d
[root@localhost ~]# docker restart ba0f7e1e3f0d
ba0f7e1e3f0d
~~~

### 后台启动一个容器后  如果想进入这个容器  attach命令

~~~linux
[root@localhost ~]# docker attach ba0f7e1e3f0d
[root@ba0f7e1e3f0d /]# 				#已经进入到容器中
~~~

### 删除容器

~~~linux
[root@localhost ~]# docker rm ba0f7e1e3f0d
~~~

### 删除镜像

~~~linux
[root@localhost ~]# docker rmi 镜像id
~~~

## Docker关于仓库的基本操作

Docker官方维护了一个DockerHub的公共仓库，里边包含有很多平时用的较多的镜像。除了从上边下载镜像之外，我们也可以将自己自定义的镜像发布（push）到DockerHub上。

访问[hub.docker.com](https://hub.docker.com/)

### 注册账号后,docker login登录DockerHub  输入账号密码

~~~linux
[root@localhost ~]# docker login
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username: gitjzz
Password: 
Login Succeeded
[root@localhost ~]# 
~~~

### 将本地的镜像推送到DockerHub上,这里的gitjzzz要和你的username一致

~~~
[root@localhost ~]# docker push gitjzzz/centos:git
The push refers to repository [docker.io/gitjzzz/centos]
An image does not exist locally with the tag: gitjzzz/centos  #username不匹配
[root@localhost ~]# docker push gitjzz/centos:git
The push refers to repository [docker.io/gitjzz/centos]
2fafaaf7e942: Pushing [>                            	                      ]  2.212MB/117.2MB 					#匹配成功
eb29745b8228: Preparing 
~~~

### 别人就可以从你的仓库中下载这个镜像了，你也可以在别的环境拉取此镜像

~~~linux
[root@localhost ~]# docker pull gitjzz/centos:git
~~~

镜像有两种创建方法	  更新也有两种

[Dockerfile使用方法](https://www.runoob.com/docker/docker-dockerfile.html)

创建容器之后做更改，之后commit生成镜像，然后push到仓库中。

更新Dockerfile。在工作时一般建议这种方式，更简洁明了。

## 尾部

从仓库（一般为DockerHub）下载（pull）一个镜像，Docker执行run方法得到一个容器，用户在容器里执行各种操作。Docker执行commit方法将一个容器转化为镜像。Docker利用login、push等命令将本地镜像推送（push）到仓库。其他机器或服务器上就可以使用该镜像去生成容器，进而运行相应的应用程序了。
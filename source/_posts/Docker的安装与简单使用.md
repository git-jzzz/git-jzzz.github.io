---
title: Docker的安装与简单使用
tags: 工具
categories:
  - Docker
  - 工具
copyright: true
description: "<pre>\t\t\t\tDocker是一个虚拟环境容器，可以将你的开发环境、代码、配置文件等一并打包到这个容器中,并发布到任意平台中<pre>"
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

centos:

~~~linux
yum install -y docker
~~~

-y  表示不询问，使用默认配置进行安装

### 验证是否安装成功

~~~linux
[root@localhost ~]# yum list installed | grep docker
docker-ce.x86_64                     18.03.1.ce-1.el7.centos        @docker-ce-stable

~~~



安装之后查看版本信息  出现 Client  Server安装成功

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

### 启动docker服务

systemctl start docker

查看状态

~~~linux
[root@localhost ~]# systemctl status docker
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)
   Active: active (running) since Wed 2020-07-22 20:43:25 EDT; 1h 4min ago
     Docs: https://docs.docker.com
 Main PID: 2859 (dockerd)
    Tasks: 19
   Memory: 271.3M
   CGroup: /system.slice/docker.service
           ├─2859 /usr/bin/dockerd
           └─2863 docker-containerd --config /var/run/docker/containerd/containerd.toml

Jul 22 21:24:28 localhost.localdomain dockerd[2859]: time="2020-07-22T21:24:28.717465533-04:00" level=info msg="ignoring event" module=libcontainerd namespace=moby topic=/tasks/delete type="*events.TaskDelete"
Jul 22 21:25:43 localhost.localdomain dockerd[2859]: time="2020-07-22T21:25:43-04:00" level=info msg="shim docker-containerd-shim started" address="/containerd-shim/moby/ba0f7e1e3f0d9b1f5f3114a6b3d7d93ec5e9baa70dab0e1c9ec6.../tasks" pid=3424
Jul 22 21:28:28 localhost.localdomain dockerd[2859]: time="2020-07-22T21:28:28-04:00" level=info msg="shim reaped" id=ba0f7e1e3f0d9b1f5f3114a6b3d7d93ec5e9baa70dab0e1c9ec6439206e77d5c module="containerd/tasks"
Jul 22 21:28:28 localhost.localdomain dockerd[2859]: time="2020-07-22T21:28:28.481777187-04:00" level=info msg="ignoring event" module=libcontainerd namespace=moby topic=/tasks/delete type="*events.TaskDelete"
Jul 22 21:28:37 localhost.localdomain dockerd[2859]: time="2020-07-22T21:28:37-04:00" level=info msg="shim docker-containerd-shim started" address="/containerd-shim/moby/ba0f7e1e3f0d9b1f5f3114a6b3d7d93ec5e9baa70dab0e1c9ec6.../tasks" pid=3552
Jul 22 21:29:06 localhost.localdomain dockerd[2859]: time="2020-07-22T21:29:06.864033257-04:00" level=info msg="Attempting next endpoint for pull after error: manifest unknown: manifest unknown"
Jul 22 21:29:13 localhost.localdomain dockerd[2859]: time="2020-07-22T21:29:13.696139768-04:00" level=error msg="Not continuing with pull after error: errors:\ndenied: requested access to the resource is denied\nunauthoriz...tion required\n"
Jul 22 21:29:13 localhost.localdomain dockerd[2859]: time="2020-07-22T21:29:13.696184767-04:00" level=info msg="Ignoring extra error returned from registry: unauthorized: authentication required"
Jul 22 21:40:22 localhost.localdomain dockerd[2859]: time="2020-07-22T21:40:22-04:00" level=info msg="shim reaped" id=ba0f7e1e3f0d9b1f5f3114a6b3d7d93ec5e9baa70dab0e1c9ec6439206e77d5c module="containerd/tasks"
Jul 22 21:40:22 localhost.localdomain dockerd[2859]: time="2020-07-22T21:40:22.855205392-04:00" level=info msg="ignoring event" module=libcontainerd namespace=moby topic=/tasks/delete type="*events.TaskDelete"
Hint: Some lines were ellipsized, use -l to show in full.
~~~

### docker images  查看镜像

~~~linux
[root@localhost ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
gitjzz/centos       git                 3fcad1be9818        26 minutes ago      332MB
mysql               5.7                 8679ced16d20        6 hours ago         448MB
mysql               latest              e3fcc9e1cc04        6 hours ago         544MB
centos              latest              831691599b88        5 weeks ago         215MB
[root@localhost ~]# 

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
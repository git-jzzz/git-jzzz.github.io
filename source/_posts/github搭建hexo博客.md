---
title: github搭建hexo博客
date: 2020-06-01 20:14:55
tags: [hexo搭建]
categories:
- 博客
copyright: true
---

​		现在市面上的博客有很多，如CSBN，简书，博客园等平台,简单注册一下就可以使用了，可以直接在上面进行写作,但是呢总会有一点缺点，比如广告啊啥的。自己购买域名和服务器，搭建博客的成本又有点高。所以可以直接在github page平台上托管我们的博客,hexo就是一个可以快速部署的博客框架。

<!--more-->

# hexo搭建(这里给出几条链接 (前人栽树后人乘凉嘛hhhh))

搭建 hexo博客  需要 git、node.js环境

[hexo官方文档](https://hexo.io/zh-cn/docs/) 

[github新手详细教程](https://blog.csdn.net/Hanani_Jia/article/details/77950594)

[node.js官网](https://nodejs.org/zh-cn/)

这里有一个详细的链接 [使用github+hexo搭建自己的博客网站](使用github+hexo搭建自己的博客网站)

到这里呢  基本上是搭建好了  不喜欢作的话已经可以了（建议不要作~）

# 下面呢是一些主题风格样式的设置 

[hexo新建博客页面和next主题相关配置](https://blog.csdn.net/weixin_43216105/article/details/83449928)

​		[next主题的设置  超详细](https://blog.csdn.net/u012195214/article/details/79204088)

[Hexo 博客优化之博客美化系列（主要是背景呀  样式之类的）](https://www.cnblogs.com/TRHX/p/11699939.html)

[Hexo NexT主题中添加人体时钟 hone hone clock](https://www.jianshu.com/p/d0e3b55684b0)

[Hexo(next主题)博客加上stackexchange愚人节鼠标跟随特效](https://www.pianshen.com/article/3147325559/)

[Hexo NexT主题内网站运行时间的设置](https://blog.csdn.net/wugenqiang/article/details/88376089)

[给Hexo博客在复制时添加版权声明](https://blog.csdn.net/qq_33430083/article/details/105626840)

[Hexo Next主题  文章末尾添加版权信息](https://cloud.tencent.com/developer/article/1482137)

# hexo添加Aplayer播放器

​		你可以按照[官方文档](https://aplayer.js.org/#/home)去研究一下,也可以直接拷贝我github上面的[dist文件夹](https://github.com/git-jzzz/git-jzzz.github.io/tree/hexo/source)

​	放到你hexo/source目录下,在你想要放入的位置引用即可

##### 			自己写了一个[aplayer的管理](https://github.com/git-jzzz/git-jzzz.github.io/tree/hexo/AudioManager),方便歌曲的添加删除，以及设置到music.js的具体目录

​					springboot+layuiAdmin前后端分离

```htm
<link rel="stylesheet" href="/dist/APlayer.min.css">
<div   id="aplayer"></div>
<script type="text/javascript" src="/dist/APlayer.min.js"></script>
<script type="text/javascript" src="/dist/music.js"></script>


<!--aplayer封面自定义颜色   没啥用~-->
<script src="https://cdn.jsdelivr.net/npm/color-thief-don@2.0.2/src/color-thief.js"></script>

 <script type="text/javascript">
<!-- 根据封面自适应主题色，主要是作用于进度条 -->
                      const colorThief = new ColorThief();
                      const setTheme = (index) => {
                        if (!ap.list.audios[index].theme) {
                          colorThief.getColorAsync(ap.list.audios[index].cover, function(color) {
                            ap.theme(`rgb(${color[0]}, ${color[1]}, ${color[2]})`, index);
                          });
                        }
                      };
                      setTheme(ap.list.index);
                      ap.on('listswitch', (data) => {
                        setTheme(data.index);
                      });
                </script>
```

​			没有问题的话已经可以播放歌曲了,不过切换页面时,歌曲都会被重置~

逼格顿时就下来了,所以如果你想让歌曲全局播放的话,可以用一个 iframe 把整个博客包起来,这样hexo页面的切换就不会影响到歌曲播放。

也可以让hexo全站实现pjax无刷新加载~~

# 添加pjax实现页面无刷新加载

​		目前看到的很多的pc端页面点击页面某块链接的时候，原本应该是页面的某个部分更新的，但是却整个页面刷新，整个页面都闪了一下。特别是看某些图集的页面，一个页面本来就几十张图看，看完眼睛都闪瞎了。用ajax加载数据可以解决这个问题，但是也会造成另外的问题，页面无法前进和后退。支持浏览器历史的， 刷新页面的同时， 浏览器地址栏位上面的地址也是会更改， 用浏览器的回退功能也能够回退到上一个页面。要实现这样的功能， pjax就应运而生。

 	基于ajax+history.pushState的新技术，该技术可以无刷新改变页面的内容，并且可以改变页面的URL。HTML5的新API扩展了window.history，使历史记录点更加开放了。可以存储当前历史记录点、替换当前历史记录点、监听历史记录点。github上有开源 [项目地址](https://github.com/defunkt/jquery-pjax),可以查看文档，不过已经很久没有更新了。

#### 			这里写了一个小例子  

​				当点击超链接时 	pjax会获取url  发送返回后获取html片段

​				#pjax-container代表此页面接收的容器  

​				fragment属性 代表  获取属性fragment对应容器的html 填充到#pjax-container

​				不过运用到hexo中可能会有很多的js需要回调  需要手动填写回调函数,否则就失效了

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
	</head>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js" ></script>
	<script type="text/javascript" src="../js/jquery.pjax.js" ></script>
	<body>
		
<script type="text/javascript">


(function ($) {
   
var containerId = '#pjax-comment';
/**
* 捕获 a 事件，当点击后要在 300 毫秒以后才会跳转。
*/
$(document).on('click', 'a[target!=_blank]', function () {

    //$("#pjax-container").fadeOut();
    //NProgress.start();
    var url = $(this).attr("href");
    setTimeout(function () {
	

	//$(document).pjax('a[target!=_blank]', '#pjax-container', {fragment: '#pjax-container',timeout: 30000});	
        $.pjax({ url: url, container: containerId, fragment:containerId, timeout: 30000 });
    }, 300);

    return false;
});

})(jQuery);

</script>
		POPOPO
		
			<div id="pjax-comment">
				这是test1<a href="test2.html">>>>test2</a>
			</div>
	</body>
</html>
```

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
	</head>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js" ></script>
	<script type="text/javascript" src="../js/jquery.pjax.js" ></script>
	<body>
		<style type="text/css">
			#pjax-comment{
				color: red;
			}
		</style>
				
<script type="text/javascript">


(function ($) {
   
var containerId = '#pjax-comment';
/**	
* 捕获 a 事件，当点击后要在 300 毫秒以后才会跳转。
*/
$(document).on('click', 'a[target!=_blank]', function () {

    //$("#pjax-container").fadeOut();
    //NProgress.start();
    var url = $(this).attr("href");
    setTimeout(function () {
	

	//$(document).pjax('a[target!=_blank]', '#pjax-container', {fragment: '#pjax-container',timeout: 30000});	
        $.pjax({ url: url, container: containerId, fragment:containerId, timeout: 30000 });
    }, 300);

    return false;
});

})(jQuery);

</script>
		
		DUUDUD
			
		<div id="pjax-comment">
				这是test2<a href="test1.html">返回test1</a>
			</div>
	</body>
</html>

```

#### 			上面的js在[我的github](https://github.com/git-jzzz/git-jzzz.github.io/tree/master/js/src)上有,这里提供一个loading加载动画:

```html
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
    <title>loading加载动画</title>

    <link href="https://www.17sucai.com/static/css/demo.css" rel="stylesheet" media="all">

    <!--[if IE]>

    <style type="text/css">
        li.remove_frame a {
            padding-top: 5px;
            background-position: 0px -3px;
        }
    </style>
    <![endif]-->
    <script src="https://hm.baidu.com/hm.js?382f81c966395258f239157654081890"></script>
    <script type="text/javascript" src="https://www.17sucai.com/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.17sucai.com/static/js/jquery.qrcode.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            function fixHeight() {
                $("#iframe").attr("height", $(window).height()-54+ "px");
            }
            $(window).resize(function () {
                fixHeight();
            }).resize();


            //隐藏
            clicktoLoadingHide();
            setTimeout(function () {
                //  加载
                clicktoLoading();
            },5000)
        });
    </script>
    <script type="text/javascript">
        function Responsive($a) {
            if ($a == true) $("#Device").css("opacity", "100");
            if ($a == false) $("#Device").css("opacity", "0");
            $('#iframe-wrap').removeClass().addClass('full-width');
            $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
            $(this).addClass('active');
            return false;
        };
        function clicktoLoading() {
            $("#iframe-wrap,#iframe").css("display","block");
        }
        function clicktoLoadingHide() {
            $("#iframe-wrap,#iframe").css("display","none");
        }
    </script>
<body id="by">
<div id="iframe-wrap">
    <iframe id="iframe" src="https://www.17sucai.com/preview/602230/2019-07-15/loading/index.html" frameborder="0" width="100%" height="97px"></iframe>
</div>
</body>
</head>
```

​		按照自己的需要放入到pjax的函数中：

```javascript
// pjax请求开始
            $(document).on('pjax:start', function () {
				clicktoLoading();
            });
            // PJAX 渲染结束时
            $(document).on('pjax:end', function() {
                clicktoLoadingHide();
				
				//这里可以进行一些组件的函数回调
                opacity_js();
                sidebar_show();
                valine();
});
```



#### 		当我的hexo使用pjax时出现了一些bug,比如评论不加载、文章无内容、侧边栏消失,估计应该是hexo加载css是模板的原因吧,所以样式丢失,无效。这里记录一下:

```javascript
function opacity_js() {
//文章不显示
    $(".post-block").css({opacity: 1});
    $(".post-header").css({opacity: 1});
    $(".post-body").css({opacity: 1});
    $(".pagination").css({opacity: 1});

}
```

```javascript
//侧边栏
function sidebar_show(){


//侧边栏不显示
	$("#sidebar").css({"margin-top":"358px","margin-left":"initial","opacity":"1"});
	$(".sidebar-inner").css({"display":"block","transform":"initial","opacity":"1"});
	$(".site-overview").css({"opacity":"1"});



	//点击切换侧边栏样式
				$("[data-target='post-toc-wrap']").click(function(){
				$("[data-target='post-toc-wrap']").css({"color":"#fc6423","border-bottom-color":"#fc6423"});
				$("[data-target='site-overview-wrap']").css({"color":"#555","border-bottom-color":"transparent"});
				$(".post-toc-wrap").css({"opacity":"1","transform":"translateY(0px)","display":"block"});
							$(".site-overview-wrap").css({"opacity":"0","transform":"translateY(0px)","display":"none"});
				})

				$("[data-target='site-overview-wrap']").click(function(){
				$("[data-target='site-overview-wrap']").css({"color":"#fc6423","border-bottom-color":"#fc6423"});
				$("[data-target='post-toc-wrap']").css({"color":"#555","border-bottom-color":"transparent"});
				$(".post-toc-wrap").css({"opacity":"0","transform":"translateY(0px)","display":"none"});
				$(".site-overview-wrap").css({"opacity":"1","transform":"translateY(0px)","display":"block"});
				})
				//滚动条事件  固定侧边栏
					$(document).scroll(function() {
						var scroH = $(document).scrollTop();
						if(scroH>=346){
							$(".sidebar-inner").css({"position":"fixed","top":"12px"});
						}else{
						$(".sidebar-inner").css({"position":"initial"});
						}
						});

						//侧边栏悬浮时样式判断
				$("[data-target='post-toc-wrap'],[data-target='site-overview-wrap']").hover(function(){

				if($(this).css("border-bottom-color")!="rgb(252, 100, 35)"){
					$(this).css("color","#fc6423");
				}

			},function(){

					if($(this).css("border-bottom-color")!="rgb(252, 100, 35)"){
					  $(this).css("color","#555");//非 hover时效果
				}

        });

}
```



```javascript
function valine(){

//valine评论
			if($("#comments").length>0){
			console.log("valine 重载");
			var GUEST = ['nick','mail','link'];
    var guest = 'nick,mail,link';
    guest = guest.split(',').filter(item=>{
      return GUEST.indexOf(item)>-1;
    });

   window.valine= new Valine({
        el: '#comments' ,
        verify: false,
        notify: false,
        appId: 'M7OkzmDiLVfdEb2n2QPsyVBR-gzGzoHsz',
        appKey: 'KKlp9GtMfNQQAjFzcIBSseJo',
        placeholder: 'Just go go ~~',
        avatar:'robohash',
        guest_info:guest,
        pageSize:'10' || 10,
		 recordIP: true,
		path: window.location.pathname
    });
			}
}
```



# 看到左下角那个萌萌哒的看板娘了吗?

放到你所需要的位置即可:

```htm
 <!--Live-2D看板娘模型-->
<script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>
<!--Live-2D看板娘模型-->
<script type="text/javascript">
    //Live-2D看板娘模型
    L2Dwidget.init({
        model: {
            //jsonpath控制显示那个小萝莉模型 //hijiki hibiki  z16  //izumi //shizuku //koharu
            jsonPath: "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json", //这里改模型，前面后面都要改
            scale: 1,
        },
        display: {
            position: "left", //设置看板娘的上下左右位置

            width: 120,  //小萝莉的宽度
            height: 200, //小萝莉的高度
            hOffset: 80,  //水平偏移
            vOffset: 10  //垂直偏移
        },
        mobile: {
            show: true,  //在移动端显示
            scale: 0.5   //移动设备上的缩放
        },
        react: {
            opacityDefault: 0.9, //设置透明度
            opacityOnHover: 0.2
        },
    });
</script>
```

# hexo源码保存:

​					hexo默认上传的是编译后的文件,源码是不上传的,如果这时候电脑坏了，那么就会导致之前的配置丢失,就很麻烦,所以我们可以把hexo源码也保存到git:但是再开一个git项目优点多余了,所以我们可以将hexo源码保存到此项目分支中保存,每次更改配置后就可以直接上传.

在hexo根目录执行:

```yml
git init
git remote add origin git@github.com:username/username.github.io.git #添加远程仓库
git checkout -b name #新建分支并切换到该分支
git add . #提交所有文件到暂存区
git commit -m "提交hexo源码" #提交文件
git push origin name #推送分支到github
```

现在github上就有hexo的源码了 可以设置默认分支为此分支:[git修改分支](https://blog.csdn.net/newbunny/article/details/97106033)

如果你的主题是直接clone下来的  是无法直接提交的

### 使用git submodule 实现第三方主题同步:

我们可以把主题的项目Fork到自己的仓库  然后备份你之前的主题文件 

 hexo根目录 git clone 自己账号下的主题url,以next主题为例:

```cmd
git submodule add git@github.com:username/hexo-theme-next.git themes/next
```

如果报错		'themes/next' already exists in the index:

解决方案:

```yml
git rm -r --cached themes/next
```



hexo根目录会多一个.gitmodules文件,这是一个配置文件,保存了项目url和子目录

然后推送到分支:

```yml
git add .
git commit -m "添加子模块"
git push origin name
```

如果主题有更改时，在主题根目录下执行:

```yml
git add .
git commit -m "提交主题更改“
git push origin master #推送主题到github
```



换电脑时:

安装node.js 和 git	

```yml
npm install hexo-cli -g #安装hexo脚手架
git clone git@github.com:username/username.github.io.git  #下载你的hexo项目  设置了默认分支的话 直接下载
git submodule update --init
cd themes/next
git pull origin master #获取最新配置
npm i #安装依赖
hexo s #启动hexo本地服务器

```




---
title: idea设置doc注释模板
tags: idea
categories: 工具
copyright: false
description: <pre>					idea中使用模板快速生成doc注释</pre>
abbrlink: b8329979
date: 2020-07-24 16:27:31
keywords:
---

## 设置类注释模板

~~~txt
/**
 * @version 0.1.0
 *
 * @Description 
 *
 * @author name
 *
 * @since 0.1.0
 *
 * @create ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
~~~

~~~txt
File>>Setting>>Editor>>File and Code Templates>>Includes>>File Header 粘贴即可
~~~

{% asset_img psa.jpg 图片1 %}

## 设置方法注释模板

~~~txt
File>>Setting>>Editor>>Live Templates  点击右上角 +  
添加Group后  点击 +  Live Template
~~~

{% asset_img psb.jpg 图片2 %}

{% asset_img psc.jpg 图片3 %}

添加模板

~~~txt
*
 * @Description: $description$
 * @version 0.1.0
 $params$
 * @return $returns$
 * @author name
 * @date $date$ $time$
 * @since 0.1.0
 **/
~~~

 **Applicable**  设置为 *

 **Expand with** 选择设置哪个键按下生成注释  选择 **Enter**

 **Edit variables**  设置变量

 **Change** 选择生效区域 

{% asset_img psd.jpg 图片4 %}

**params**设置为:

~~~txt
groovyScript("def result=''; def params=\"${_1}\".replaceAll('[\\\\[|\\\\]|\\\\s]', '').split(',').toList(); for(i = 1; i < params.size() +1; i++) {result+='* @param ' + params[i - 1] + ' ' + i + ((i < params.size()) ? '\\n ' : '')}; return result", methodParameters())
~~~

创建类会自动生成注释   方法/**+Enter生成  效果如下:

{% asset_img pse.jpg 图片5 %}
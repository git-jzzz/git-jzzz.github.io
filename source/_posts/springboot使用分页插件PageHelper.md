---
title: springboot使用分页插件PageHelper
tags: 工具
categories: 工具
copyright: true
description: "<pre>\t\t\t\t使用分页插件PageHelper减少开发工作，提高效率</pre>"
abbrlink: 74e44cd
date: 2020-07-04 15:41:52
keywords:
---

## pom文件添加依赖

~~~pom
<dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.11</version>
        </dependency>
~~~

## Mapper.xml代码

~~~xml
 <select id="getAllBooks" resultType="Book">
        select * from book
    </select>
~~~

## Mapper.interface代码

~~~java
 List<Book> getAllBooks();
~~~

## Service层代码

~~~java
@Resource
    BookMapper bookMapper;

    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }
~~~

## Controller层使用

~~~java
 @Autowired
    BookService bookService;

    @GetMapping("/getBookListPage/{pageNo}")
    public Object getBookList(@PathVariable int pageNo){
        PageHelper.startPage(pageNo,2);
        //查询语句紧跟PageHelper.startPage(pageNo,2);之后
        List<Book> books=bookService.getAllBooks();
        PageInfo<Book> bookPageInfo=new PageInfo<Book>(books);
        Map<String,Object> map=new HashMap<>();
        map.put("msg", "success");
        map.put("data", bookPageInfo);
        map.put("code", 200);
        return map;
    }
~~~

## 启动项目，访问Controller

~~~json
{
msg: "success",
code: 200,
data: {
total: 6,
list: [
{
id: 1,
name: "朝花夕拾",
author: "鲁迅"
},
{
id: 3,
name: "ccc",
author: "ccc1"
}
],
pageNum: 1,
pageSize: 2,
size: 2,
startRow: 1,
endRow: 2,
pages: 3,
prePage: 0,
nextPage: 2,
isFirstPage: true,
isLastPage: false,
hasPreviousPage: false,
hasNextPage: true,
navigatePages: 8,
navigatepageNums: [
1,
2,
3
],
navigateFirstPage: 1,
navigateLastPage: 3
}
}
~~~


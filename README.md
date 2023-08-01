# typora-img-tool-java

<p>
  <a href="https://www.oracle.com/java/technologies/javase/17u-relnotes.html"><img src="https://img.shields.io/badge/jdk-%3E=17.0.0-blue.svg" alt="jdk compatility"></a>
</p>

## 介绍

开发环境使用 [jdk@17.x](https://www.oracle.com/java/technologies/downloads/#java17)。

Typora导出的html文件之后，使用此工具将 html文件中 关联本地的图片路径转换为 base64编码格式，方便文件的传递分享，不需要携带图片文件夹


## 预览


![image](https://user-images.githubusercontent.com/50919172/225794079-3d70a845-abf5-49fd-9b1d-0ca31ed0837e.png)


## 项目结构说明

## 接口文档

## 使用

1、在typora编辑器中，将 md 文件导出 HTML格式文件。并将jar包放到同一目录中。
![image](https://user-images.githubusercontent.com/50919172/225793377-8e1379d9-fb1c-4361-bbeb-d7d47cfd4ac4.png)

2、在当前目录 打开cmd
```bash
# 执行方式一：默认读取和写出编码是 `UTF-8`
java -jar typora-img.jar 学习笔记.html

# 执行方式二：指定编码 （读取文件`GBK` 写出文件`UTF-8`）
java -jar typora-img.jar GBK UTF-8 学习笔记.html
```

## 引用


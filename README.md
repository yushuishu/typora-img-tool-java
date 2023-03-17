# typora-img-tool-java

Typora导出的html文件之后，使用此工具将 html文件中 关联本地的图片路径转换为 base64编码格式，方便文件的传递分享，不需要携带图片文件夹

# 使用

1、文档 和 编译好的jar

将 md 文件导出 HTML格式文件

![image](https://user-images.githubusercontent.com/50919172/225793377-8e1379d9-fb1c-4361-bbeb-d7d47cfd4ac4.png)


2、当前目录 打开cmd
使用 typora-img.jar，生成文件名以 base64.html 结尾的文件，使用浏览器打开查看效果
```bash
# 默认读取 和 写出编码 是 UTF-8
java -jar typora-img.jar 学习笔记.html

# 指定编码 （读取文件GBK 写出文件UTF-8）
java -jar typora-img.jar GBK UTF-8 学习笔记.html
```

![image](https://user-images.githubusercontent.com/50919172/225793519-31d6ff65-33d3-44be-8e2e-f933ac54fcb6.png)

效果：

![image](https://user-images.githubusercontent.com/50919172/225794079-3d70a845-abf5-49fd-9b1d-0ca31ed0837e.png)


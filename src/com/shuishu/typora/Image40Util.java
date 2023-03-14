package com.shuishu.typora;


import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Set;


/**
 * @author ：谁书-ss
 * @date ：2022/1/7 15:42
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：typora导出的HTML文件之后，使用此工具将 html文件中 关联本地的图片路径转换为 base64编码格式，方便文件的传递分享，不需要携带图片文件夹
 */
public class Image40Util {
    public static void main(String[] args) {
        // 获取文件路径
        if (args.length == 0) {
            System.out.println("No parameters passed");
            return;
        }
        // 编码
        String inputCharsetName = "UTF-8";
        String outCharsetName = "UTF-8";
        if (args.length > 1) {
            if (args.length == 2) {
                String charset1 = getCharsetName(args[1]);
                if (charset1 != null && charset1.length() > 0) {
                    inputCharsetName = charset1;
                }
            }
            if (args.length == 3) {
                String charset2 = getCharsetName(args[2]);
                if (charset2 != null && charset2.length() > 0) {
                    outCharsetName = charset2;
                }
            }
        }
        System.out.println("读取编码：" + inputCharsetName);
        System.out.println("写出编码：" + outCharsetName);
        System.out.println("");

        String arg = args[0];
        // 获取文件后缀
        String suffix = getSuffix(arg);
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(arg), inputCharsetName);
             BufferedReader bfr = new BufferedReader(isr);
             FileOutputStream fos = new FileOutputStream(arg.concat("-bast64.").concat(suffix), true);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(fos, outCharsetName))
        ) {
            String len = "";
            while ((len = bfr.readLine()) != null) {
                String result = "";
                if (len.indexOf("<img") != -1) {
                    result = execute(len, 0);
                }
                if (result.equals("")) {
                    bfw.write(len);
                } else {
                    bfw.write(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        System.out.println("success");
    }

    public static String getCharsetName(String charsetName) {
        Set<String> charSet = Charset.availableCharsets().keySet();
        if (charsetName != null && charsetName.trim().length() > 0 && charSet.contains(charsetName)) {
            return charsetName;
        }
        return null;
    }

    /**
     * 递归执行查找同一行字符串多个 img 标签
     *
     * @param src - img src 内容
     * @param end -下次查找字符串起始位置
     * @return -
     */
    public static String execute(String src, int end) {
        String result = matchImg(src, end);
        System.out.println("图片：" + result);
        if (result.isEmpty()) {
            return src;
        } else {
            String[] split = result.split(",");
            String s1 = fileToBase64(split[0]);
            if (s1.isEmpty()) {
                return src;
            } else {
                String replace = src.replace(split[0], s1);
                return execute(replace, Integer.valueOf(split[1]) + 20);
            }
        }
    }

    /**
     * 匹配 img src 内容
     *
     * @param str - 原始字符串
     * @return -
     **/
    public static String matchImg(String str, int start) {
        // 起始位置
        int img = str.indexOf("<img", start);
        if (img == -1) {
            return "";
        }
        // src 左侧 双引号
        int l = str.indexOf("\"", img) + 1;
        // src 右侧 双引号
        int r = str.indexOf("\"", l);
        String substring = str.substring(l, r);
        // 跳过已经 base64 编码的文件 和 http 地址
        if (substring.startsWith("data")) {
            return matchImg(str, r);
        }
        // src 地址 返回 src 内容以及最后的位置 使用逗号拼接
        return substring + "," + r;
    }

    /**
     * 文件转 base64
     *
     * @param path - 文件路径
     * @return -
     **/
    public static String fileToBase64(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.err.printf("文件不存在");
            return "";
        }
        byte bytes[] = null;
        try (FileInputStream fileInputStream = new FileInputStream(path);) {
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("图片转 base64 失败");
        }
        // 文件后缀处理
        String suffix = getSuffix(path);
        return "data:image/" + suffix + ";base64," + Base64.getEncoder().encodeToString(bytes);
    }


    /**
     * 获取文件后缀
     *
     * @param str 文件路径
     * @return 图片类型名
     */
    public static String getSuffix(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }
}

package com.shuishu.typora;


import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Base64;


/**
 * @author ：谁书-ss
 * @date ：2022/1/8 14:21
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：视频处理
 */
public class Video10Util {
    public static void main(String[] args) throws IOException {
        String sourceVideo = "C:\\Users\\Administrator\\Desktop\\ISO下载.mp4" ;

        InputStream inputStream =  FileUtils.openInputStream(new File(sourceVideo));
        // encode
        String ss = new String(Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)), Charsets.ISO_8859_1);
        File txtFilePath = new File("C:\\Users\\Administrator\\Desktop\\video.txt");
        FileWriter fileWriter = new FileWriter(txtFilePath);
        fileWriter.write(ss);
        fileWriter.flush();
        fileWriter.close();
    }
}

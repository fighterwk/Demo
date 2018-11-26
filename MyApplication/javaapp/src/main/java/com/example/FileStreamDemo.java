package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/31
 */
public class FileStreamDemo {

    public static void main(String[] args) throws IOException {
        // 声明多行内容
        String multiText = "行1\n" +
                "行2\n" +
                "行3\n" +
                "行4\n" +
                "行5\n" +
                "\n" +
                "\n" +
                "行6";


        BufferedReader bReader = new BufferedReader(new StringReader(multiText));
        String lineStr = null;
        try {
            int index = 0;
            while ((lineStr = bReader.readLine()) != null) {
                if (index == 3) {
                    index = 0;
                }

                writeFile(new File("file", "content_" + index + ".txt"), lineStr);

                index++;
            }
            System.out.println("写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("写入失败:" + e.getMessage());
        }finally {
            bReader.close();
        }


    }


    /**
     * 写入文本内容到文件中
     *
     * @param file    写入的文件路径和文件名
     * @param content 写入的内容
     */
    private static void writeFile(File file, String content) throws IOException {
        // 本地如果不存在，创建一个
        if (!file.exists()) {
            File parentPath = file.getParentFile();
            parentPath.mkdirs();  // 创建目录
            file.createNewFile();  // 创建文件
        }

        System.out.println("准备写入文件:" + file.getAbsolutePath());

        FileOutputStream fos = new FileOutputStream(file, true);
        BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(fos));
        bWriter.write(content);
//        bWriter.write("\n");
        bWriter.newLine();

        bWriter.flush();
        bWriter.close();
    }
}

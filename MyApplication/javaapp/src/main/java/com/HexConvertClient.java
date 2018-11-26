package com;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description描述: 16进制转换
 * @Author作者: Kyle
 * @Date日期: 2018/10/15
 */
public class HexConvertClient {
    public static void main(String[] args) {
        String content = "我爱你中国";
        String md5 = MD5(content);
        System.out.println("md5:" + md5);

//        System.out.println(0xf0);


//        // 一个byte 8字节, 16进制是 最小值 0，最高值是 15
//        // 0-15  二进制表示 0000 - 1111
//        byte bt = -20;
//        byte high = (byte) (((bt & (0b11110000)) >> 4));
////        byte high = (byte) (bt >> 4);
//        byte low = (byte) (bt & 0b00001111);
//
//        StringBuilder builder = new StringBuilder();
//        builder.append(Integer.toHexString(high))
//                .append(Integer.toHexString(low));
//
//        System.out.println(builder.toString());
    }

    /**
     * 获取内容的摘要内容
     *
     * @param content 内容
     * @return
     */
    private static String MD5(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes());
            byte[] digest = md.digest();
            return buffer2Hex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }


    private static final char[] hexDigits =
            new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8'
                    , '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 二进制转换成16进制的文本
     *
     * @param buffer
     * @return
     */
    private static String buffer2Hex(byte[] buffer) {
        StringBuilder sBuilder = new StringBuilder();
        for (byte b : buffer) {
            byte high = (byte) (b & 0b11110000 >> 4);
            byte low = (byte) (b & 0b00001111);
            sBuilder.append(hexDigits[high])
                    .append(hexDigits[low]);
        }
        return sBuilder.toString();
    }
}

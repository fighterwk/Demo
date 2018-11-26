package com.example;


import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 *
 * @author talver
 */
public class Security {
    //    final static String AES_KEY = "Q*1_3@c!4kd^j&g%";
    final static String AES_KEY = "VoSkEFQayXJT47VW";

    public static void main(String[] args) {
//        byte[] data = new byte[16];
//        System.out.println(new String(data));
        String content = "{\"orgId\":\"DIDI\",\"telPhone\":\"13751096562\",\"openId\":\"110100192001016715\"}";

        System.out.println(content.length());
        String encrypt = aesEncrypt(content);
        System.out.println(encrypt);

        System.out.println(aesDecrypt(encrypt));


//        System.out.println(aesDecrypt("IOliRNG+WKEAnj4podDFPNkW2MNGi+a9dyTg93rSU9YAQFv+84tEYDZg/2c7K3Nvjoeg3Vktjd0CoelTLu50lNXt3jDcjIdvyuOVf+/Yr3Q="));
//        System.out.println(aesDecrypt("IOliRNG+WKEAnj4podDFPNkW2MNGi+a9dyTg93rSU9YAQFv+84tEYDZg/2c7K3Nvjoeg3Vktjd0CoelTLu50lNXt3jDcjIdvyuOVf+/Yr3Q="));
    }

    /**
     * AES加密
     *
     * @param str 待加密字符串
     * @return 加密后字符串
     */
    public static String aesEncrypt(String str) {
        try {
//            int pad = str.length() % 16;
//            if (pad == 0){
//                pad = 16;
//            }
//
//            byte[] padByte = new byte[pad];
//            byte[] data = new byte[str.length() + pad];
//            System.arraycopy(str.getBytes(), 0, data, 0, str.length());
////            System.arraycopy(data, str.length(), padByte, 0, padByte.length);
//
//            System.out.println(new String(data));

            String password = AES_KEY;
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            System.out.println("blockSize:" + cipher.getBlockSize());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            return new String(cipher.doFinal(str.getBytes()));
//
//            String strTmp = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
//            return strTmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * AES解密
     *
     * @param str 待解密字符串
     * @return 解密后字符串
     */
    public static String aesDecrypt(String str) {
        try {
            String password = AES_KEY;
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//            cipher.init(Cipher.DECRYPT_MODE, initKeyForAES(AES_KEY));

            String strTmp = new String(cipher.doFinal(Base64.getDecoder().decode(str)));
            return strTmp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }


    private static Key initKeyForAES(String key) throws NoSuchAlgorithmException {
        if (null == key || key.length() == 0) {
            throw new NullPointerException("key not is null");
        }
        SecretKeySpec key2 = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            key2 = new SecretKeySpec(enCodeFormat, "AES");
        } catch (NoSuchAlgorithmException ex) {
            throw new NoSuchAlgorithmException();
        }
        return key2;

    }
}  
package com.qijy.secret;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class SecretTest {
    public static void main(String[] args) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] skey = Arrays.copyOf(messageDigest.digest("123456".getBytes("utf-8")), 16);
        // 转成字节组再转为AES加密再BASE64加密
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(skey, "AES"), new IvParameterSpec(new byte[16]));
        byte[] bytes = cipher.doFinal("{\"submit\":[{\"content\":\"fa'bu发布测试6\",\"destination_address\":\"13701605341\",\"source_address\":\"\"}],\"username\":\"XIEXL2\"}".getBytes("utf-8"));
        String ss = new String (bytes);

        byte[] encode = Base64.getEncoder().encode(bytes);
        String str = new String (encode);
        byte[] bytes1 = str.getBytes();
        byte[] decode = Base64.getDecoder().decode(bytes1);
        String decodeStr = new String (decode,"utf-8");
        System.out.println(decodeStr);
//        System.out.println(str);

    }
}

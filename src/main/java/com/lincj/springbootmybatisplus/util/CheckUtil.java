package com.lincj.springbootmybatisplus.util;


import com.lincj.springbootmybatisplus.security.SHA1;

import java.util.Arrays;

/**
 * 请求校验
 */
public class CheckUtil {

    private static final String token = "linchujia";//自己设置，要与微信界面的设置一致

    public static boolean checkSignature(String signature , String timestamp , String nonce){
        String[] str = new String[]{token,timestamp,nonce};

        //排序
        Arrays.sort(str);

        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for(int i = 0 ; i < str.length; i++){
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹配

        return signature.equals(temp);
    }
}

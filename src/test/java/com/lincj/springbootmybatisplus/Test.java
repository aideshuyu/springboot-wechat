package com.lincj.springbootmybatisplus;

import com.lincj.springbootmybatisplus.util.WeiXinUtil;

public class Test {

    public static void main(String[] args) {
        String access_token = WeiXinUtil.getAccess_Token();
        System.out.println("调用成功 access_token:" + access_token);
    }
}

package com.lincj.springbootmybatisplus;

import com.lincj.springbootmybatisplus.util.MenuUtil;
import com.lincj.springbootmybatisplus.util.WeiXinUtil;

public class TestMenu {

    public static void main(String[] args) {
        String accessToken = WeiXinUtil.getAccess_Token();
        String menu = MenuUtil.initMenu();
        System.out.println(menu);
        int result = MenuUtil.createMenu(accessToken , menu);
        if(result == 0){
            System.out.println("菜单创建成功");
        }else{
            System.out.println("错误码"+result);
        }
    }
}

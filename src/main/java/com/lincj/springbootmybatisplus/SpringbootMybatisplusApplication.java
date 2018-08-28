package com.lincj.springbootmybatisplus;

import com.lincj.springbootmybatisplus.util.MenuUtil;
import com.lincj.springbootmybatisplus.util.WeiXinUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMybatisplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisplusApplication.class, args);
		SpringbootMybatisplusApplication.initMenu();
	}

	public static void initMenu(){
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

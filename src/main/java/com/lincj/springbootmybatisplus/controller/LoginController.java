package com.lincj.springbootmybatisplus.controller;

import com.lincj.springbootmybatisplus.util.CheckUtil;
import com.lincj.springbootmybatisplus.util.ImageMessageUtil;
import com.lincj.springbootmybatisplus.util.MessageUtil;
import com.lincj.springbootmybatisplus.util.TextMessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 与微信对接登录验证
 */
@Controller
public class LoginController {

    @GetMapping(value = "wx")
    public void login(HttpServletRequest request , HttpServletResponse response){
        System.out.println("success");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature,timestamp,nonce)){
                out.write(echostr);
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            out.close();
        }

    }

    @PostMapping(value = "wx")
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        //将微信请求XML转成map格式，获取所需的参数
        Map<String , String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");

        String message = null;
        //处理文本类型，实现输入1，恢复响应的封装内容
        if("text".equals(MsgType)){
            if("图片".equals(Content)){
                ImageMessageUtil util = new ImageMessageUtil();
                message = util.initMessage(FromUserName , ToUserName);
            }else{
                TextMessageUtil textMessage = new TextMessageUtil();
                message = textMessage.initMessage(FromUserName , ToUserName);
            }
        }
        try {
            out = response.getWriter();
            out.write(message);
        }catch (IOException e){
            e.printStackTrace();
        }
        out.close();
    }

}

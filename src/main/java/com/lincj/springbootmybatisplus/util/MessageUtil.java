package com.lincj.springbootmybatisplus.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *消息处理工具，将微信传递过来的XML格式的信息转成Map
 */
public class MessageUtil {

    /** 接收的数据结构
     * <xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>1348831860</CreateTime>
         <MsgType><![CDATA[text]]></MsgType>
         <Content><![CDATA[this is a test]]></Content>
         <MsgId>1234567890123456</MsgId>
         </xml>
    * */

    /**
     * 将微信的请求中参数转成map
     * @param request
     * @return
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request){
        Map<String , String> map = new HashMap<String , String >();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream(); //从request中获取到保存信息的输入流
            Document doc = reader.read(in); //创建一个dom4j的Document对象
            Element root = doc.getRootElement(); //获取根目录
            List<Element> list = root.elements();  //获取根目录下的所有子节点
            for(Element element : list){ //遍历获取每个节点的信息
                map.put(element.getName() , element.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return map;
    }

}

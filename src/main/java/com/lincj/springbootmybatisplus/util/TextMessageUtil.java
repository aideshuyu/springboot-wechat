package com.lincj.springbootmybatisplus.util;

import com.lincj.springbootmybatisplus.po.MessageText;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**

 */
public class TextMessageUtil implements BaseMessageUtil<MessageText>{

    /**
     * 将发送的信息封装成对应的xml格式
     * @param message
     * @return
     */
    public String messageToxml(MessageText message){
        XStream xStream =  new XStream();
        xStream.alias("xml", message.getClass());
        return xStream.toXML(message);
    }


    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     * @return
     */
    public String initMessage(String FromUserName , String ToUserName){
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent("欢迎关注中国电信广东电信研究院");
        text.setCreateTime(new Date().getTime());
        text.setMsgType("text");
        return  messageToxml(text);
    }

}

package com.lincj.springbootmybatisplus.util;

import com.lincj.springbootmybatisplus.po.Image;
import com.lincj.springbootmybatisplus.po.ImageMessage;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * 图片消息回复类
 */
public class ImageMessageUtil implements BaseMessageUtil<ImageMessage>{

    /**
     * 将信息转成xml格式
     * @param imageMessage
     * @return
     */
    @Override
    public String messageToxml(ImageMessage imageMessage) {
        XStream xtream = new XStream();
        xtream.alias("xml", imageMessage.getClass());
        xtream.alias("Image", imageMessage.getImage().getClass());

        String xmlStr = xtream.toXML(imageMessage);
        System.out.println("---------------------------");
        System.out.println(imageMessage.getImage().getMediaId());
        System.out.println(xmlStr);
        return xmlStr;
    }

    /**
     * 封装信息
     * @param FromUserName
     * @param ToUserName
     * @return
     */
    @Override
    public String initMessage(String FromUserName, String ToUserName) {

        ImageMessage message = new ImageMessage();
        message.setFromUserName(ToUserName);
        message.setToUserName(FromUserName);
        message.setCreateTime(new Date().getTime());
        Image image = new Image();

        String mediaId = getmediaId();
        image.setMediaId(mediaId);
        message.setImage(image);
        message.setMsgType("image");
        String xmlStr = messageToxml(message);
        return xmlStr;
    }

    /**
     * 获取mediaId
     * @return
     */
    public String getmediaId(){
        String path = "f:/1.jpg";
        String accessToken = WeiXinUtil.getAccess_Token();
        String mediaId = null;
        try{
            mediaId = UploadUtil.upload(path , accessToken , "image");
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("====================");
        System.out.println(mediaId);
        return mediaId;
    }

}

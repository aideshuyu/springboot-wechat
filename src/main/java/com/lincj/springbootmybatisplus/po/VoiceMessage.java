package com.lincj.springbootmybatisplus.po;

/**
 * 语音消息类
 */
public class VoiceMessage extends BaseMessage {

    public Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}

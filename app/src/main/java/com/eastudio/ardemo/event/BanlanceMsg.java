package com.eastudio.ardemo.event;


public class BanlanceMsg {

    /**
     *
     */
    public int code;

    /**
     *
     */
    public String msgType;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public BanlanceMsg(int code, String msgType) {
        this.code = code;
        this.msgType = msgType;
    }
}

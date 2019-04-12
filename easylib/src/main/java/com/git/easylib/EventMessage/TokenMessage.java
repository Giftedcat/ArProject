package com.git.easylib.EventMessage;

/**
 * Created by Administrator on 2017/12/23/023.
 */

public class TokenMessage {



    public int resultCode;
    public String messageType;


    public TokenMessage() {
    }

    public TokenMessage(int resultCode) {
        this.resultCode = resultCode;
    }

    public TokenMessage(int resultCode, String messageType) {
        this.resultCode = resultCode;
        this.messageType = messageType;
    }

}

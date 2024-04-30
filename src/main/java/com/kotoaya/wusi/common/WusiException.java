package com.kotoaya.wusi.common;

public class WusiException extends RuntimeException{
    public WusiException(String message) {
        super(message,null,false,false);
    }
}

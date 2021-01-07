package com.cobra.es.config;

public enum CallEndEnum {
    OK_ROBOT_HANG_UP(10, "已呼通"),
    CANCEL(20, "手动取消"),
    FAILED_EMPTY_NUMBER(30,"未呼通"),
    ;
    private int code;
    private String msg;

    CallEndEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

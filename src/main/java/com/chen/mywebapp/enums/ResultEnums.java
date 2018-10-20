package com.chen.mywebapp.enums;

public enum ResultEnum {
    SUCCESS(0, "成功"),
    SAVE_USER_ERROR(500, "创建用户失败")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

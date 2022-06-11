package com.blog.consistant;

import lombok.Data;

public enum NetStatus {
    LOCKED("5001", "该账号被锁"),
    EXPIRED("5002", "该账号已过期"),
    ERROR_ACCOUNT("5003", "用户名或密码错误"),
    WITHOUT_LOGIN("5004", "未登录访问"),
    SUCCESS("200200", "请求成功"),
    FILE_UPLOAD_FILED("500005", "文件上传失败");
    private final String code;
    private final String msg;
    private NetStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }
}

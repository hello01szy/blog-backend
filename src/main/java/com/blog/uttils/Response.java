package com.blog.uttils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String code;
    private String msg;
    private Object data;
    public Response (String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

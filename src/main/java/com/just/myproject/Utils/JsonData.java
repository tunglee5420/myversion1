package com.just.myproject.Utils;

import java.io.Serializable;

public class JsonData implements Serializable {
    private  static  final  long serialVersionUID=1L;
    //状态码 1表示成功，0表示失败
    private int code;
    //状态数据
    private  Object data;
    private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonData(int code, Object data, String message) {
        this.code = code;
        this.message=message;
        this.data = data;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

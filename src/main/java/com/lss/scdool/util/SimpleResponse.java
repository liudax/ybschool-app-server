package com.lss.scdool.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Magic
 * @date 23:24 2018/3/31
 * @description
 */
@ApiModel
public class SimpleResponse implements CommonResponse{
    @ApiModelProperty("0-成功，1-失败")
    private int code;
    @ApiModelProperty("结果信息")
    private String msg;
    @ApiModelProperty("返回的数据，失败时为null")
    private Object content;


    public static SimpleResponse SUCCESS = new SimpleResponse();

    public SimpleResponse(){
        this(0,"成功",null);
    }
    public SimpleResponse(int code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }
    public SimpleResponse(Object content) {
        this(0,"成功",content);
    }
    public SimpleResponse(int code,String msg) {
        this(code,msg,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

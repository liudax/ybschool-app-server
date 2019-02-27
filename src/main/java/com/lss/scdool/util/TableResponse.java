package com.lss.scdool.util;

import java.util.List;

/**
 * @author Magic
 * @date 23:28 2018/3/31
 * @description
 */
public class TableResponse<T> implements CommonResponse{
    private int code;
    private String msg;
    private Long count;
    private List<T> data;

    public TableResponse(Long total, List<T> data) {
       this(0,"成功",total,data);
    }

    public TableResponse(int code, String msg, Long count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

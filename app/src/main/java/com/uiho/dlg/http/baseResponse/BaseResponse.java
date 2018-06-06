package com.uiho.dlg.http.baseResponse;

/**
 * 作者：uiho_mac
 * 时间：2018/3/1
 * 描述：基础返回数据
 * 版本：1.0
 * 修订历史：
 */

public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

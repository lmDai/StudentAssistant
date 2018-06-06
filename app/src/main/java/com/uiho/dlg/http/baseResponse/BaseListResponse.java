package com.uiho.dlg.http.baseResponse;

import java.util.List;

/**
 * 作者：uiho_mac
 * 时间：2018/3/19
 * 描述：基础列表
 * 版本：1.0
 * 修订历史：
 */

public class BaseListResponse<T> {

    private int code;
    private String message;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

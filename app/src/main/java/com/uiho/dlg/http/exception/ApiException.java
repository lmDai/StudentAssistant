package com.uiho.dlg.http.exception;

/**
 * 作者：uiho_mac
 * 时间：2018/3/1
 * 描述：异常控制
 * 版本：1.0
 * 修订历史：新建
 */
public class ApiException extends Exception {


    private int code;
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }

    public ApiException(String message) {
        super(message);
        this.displayMessage = message;
    }


    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

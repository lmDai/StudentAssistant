package com.uiho.dlg.http.exception;

/**
 * 作者：uiho_mac
 * 时间：2018/3/1
 * 描述：服务器运行异常
 * 版本：1.0
 * 修订历史：新建
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
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

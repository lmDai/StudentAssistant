package com.uiho.dlg.http.helper;


import com.uiho.dlg.http.api.Api;

import javax.inject.Inject;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：api帮助类
 * 版本：1.0
 * 修订历史：
 */

public class ApiHelper implements HttpHelper {
    private Api api;

    @Inject
    public ApiHelper(Api api) {
        this.api = api;
    }

}

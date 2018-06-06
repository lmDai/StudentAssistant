package com.uiho.dlg.http.api;


import com.uiho.dlg.http.baseResponse.BaseListResponse;
import com.uiho.dlg.ui.controller.model.TestModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：网络请求配置
 * 版本：1.0
 * 修订历史：
 */

public interface Api {
    //测试地址
    String HOST = "http://app.miaohaigou.com/index.php/app/yygoods/";

    /**
     * 测试接口
     */
    @GET("getcategoryinfo.html")
    Observable<BaseListResponse<TestModel>> getTest();
}

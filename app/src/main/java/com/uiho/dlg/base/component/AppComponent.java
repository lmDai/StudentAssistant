package com.uiho.dlg.base.component;

import com.uiho.dlg.app.App;
import com.uiho.dlg.http.helper.ApiHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();//提供App的Context
    ApiHelper apiHelper();  //提供http的帮助类
}

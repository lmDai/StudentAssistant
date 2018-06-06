package com.uiho.dlg.base.component;

import com.uiho.dlg.app.App;
import com.uiho.dlg.http.helper.ApiHelper;
import com.uiho.dlg.http.helper.HttpHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(ApiHelper apiHelper) {
        return apiHelper;
    }
}

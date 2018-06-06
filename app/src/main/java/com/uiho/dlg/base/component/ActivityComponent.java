package com.uiho.dlg.base.component;

import android.app.Activity;

import dagger.Component;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

}

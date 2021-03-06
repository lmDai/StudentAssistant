package com.uiho.dlg.base.component;

import android.app.Activity;

import com.uiho.dlg.ui.fragment.FindFragment;

import dagger.Component;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(FindFragment findFragment);
}

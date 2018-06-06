package com.uiho.dlg.base.presenter;

import com.uiho.dlg.base.view.BaseView;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：presenter 基类
 * 版本：1.0
 * 修订历史：
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}

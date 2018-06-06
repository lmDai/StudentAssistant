package com.uiho.dlg.base.ui;

import android.os.Bundle;

import com.uiho.dlg.app.App;
import com.uiho.dlg.base.component.ActivityComponent;
import com.uiho.dlg.base.component.ActivityModule;
import com.uiho.dlg.base.component.DaggerActivityComponent;
import com.uiho.dlg.base.presenter.BasePresenter;
import com.uiho.dlg.base.view.BaseView;

import javax.inject.Inject;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：mvp activity基类
 * 版本：1.0
 * 修订历史：
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void initInject();

}

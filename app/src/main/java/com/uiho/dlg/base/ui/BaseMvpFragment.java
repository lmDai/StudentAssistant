package com.uiho.dlg.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.uiho.dlg.app.App;
import com.uiho.dlg.base.component.DaggerFragmentComponent;
import com.uiho.dlg.base.component.FragmentComponent;
import com.uiho.dlg.base.component.FragmentModule;
import com.uiho.dlg.base.presenter.BasePresenter;
import com.uiho.dlg.base.view.BaseView;

import javax.inject.Inject;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：BaseMvpFragment
 * 版本：1.0
 * 修订历史：
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) mPresenter.detachView();
        mPresenter = null;
        super.onDestroy();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    protected abstract void initInject();
}

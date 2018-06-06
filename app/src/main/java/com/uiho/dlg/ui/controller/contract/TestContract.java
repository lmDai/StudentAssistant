package com.uiho.dlg.ui.controller.contract;

import com.uiho.dlg.base.presenter.BasePresenter;
import com.uiho.dlg.base.view.BaseView;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */

public interface TestContract {
    interface View extends BaseView {
        void showData(String data);
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}

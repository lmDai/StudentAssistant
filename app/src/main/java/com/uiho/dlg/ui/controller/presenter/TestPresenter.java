package com.uiho.dlg.ui.controller.presenter;

import com.alibaba.fastjson.JSONArray;
import com.uiho.dlg.base.presenter.RxPresenter;
import com.uiho.dlg.http.baseResponse.BaseListResponse;
import com.uiho.dlg.http.exception.ExceptionEngine;
import com.uiho.dlg.http.helper.RetrofitHelper;
import com.uiho.dlg.ui.controller.contract.TestContract;
import com.uiho.dlg.ui.controller.model.TestModel;
import com.uiho.dlg.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */

public class TestPresenter extends RxPresenter<TestContract.View> implements TestContract.Presenter {
    @Inject
    public TestPresenter() {

    }

    @Override
    public void getData() {
        Disposable disposable = RetrofitHelper.getApi().getTest()
                .compose(RxUtil.<BaseListResponse<TestModel>>io_main())
                .compose(RxUtil.<TestModel>handleListResult())
                .subscribe(new Consumer<List<TestModel>>() {//onNext
                    @Override
                    public void accept(List<TestModel> testModels) throws Exception {
                        mView.showData(JSONArray.toJSONString(testModels));
                    }
                }, new Consumer<Throwable>() {//onError
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(ExceptionEngine.handleException(throwable).getDisplayMessage());
                    }
                });
        addSubscribe(disposable);
    }
}

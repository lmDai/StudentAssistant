package com.uiho.dlg.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.uiho.dlg.R;
import com.uiho.dlg.base.ui.BaseMvpFragment;
import com.uiho.dlg.ui.controller.contract.TestContract;
import com.uiho.dlg.ui.controller.presenter.TestPresenter;

import butterknife.BindView;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：
 * 版本：1.0
 * 修订历史：
 */

public class FindFragment extends BaseMvpFragment<TestPresenter> implements TestContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;

    public static FindFragment newInstance(String param1) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        showBack = false;
    }

    @Override
    protected void lazyFetchData() {
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        txtTitle.setText(agrs1);
        mPresenter.getData();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void showData(String data) {

    }
}

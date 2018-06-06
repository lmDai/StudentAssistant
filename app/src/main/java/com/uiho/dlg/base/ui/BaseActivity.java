package com.uiho.dlg.base.ui;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uiho.dlg.R;
import com.uiho.dlg.app.App;
import com.uiho.dlg.util.StatusBarCompatUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：基础activity
 * 版本：1.0
 * 修订历史：
 */

public abstract class BaseActivity extends SupportActivity {
    protected Activity mContext;
    private Unbinder mUnBinder;
    protected boolean showBack = true;
    protected Toolbar toolbar;
    protected TextView textCancel;
    protected TextView textRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        init();
        initStatusBar();
        setContentView(getLayout());
        getIntentData();
        mUnBinder = ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            textRight = (TextView) toolbar.findViewById(R.id.btn_right);
            textCancel = (TextView) toolbar.findViewById(R.id.btn_left);
            if (textCancel != null)
                textCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackListener();
                    }
                });
            setSupportActionBar(toolbar);
            if (showBack) {
                final Drawable upArrow = ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher);
                upArrow.setColorFilter(ContextCompat.getColor(mContext, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        initView();
        initEvent();
    }

    protected void init() {
        App.getInstance().registerActivity(this);
    }

    protected void initStatusBar() {
        //设置状态栏为红色
        StatusBarCompatUtils.setColor(mContext, ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置标题
     */
    public void setTopTitle(String str) {
        TextView title = (TextView) findViewById(R.id.bt_tv_title);
        title.setText(str);
    }

    protected void onBackListener() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null)
            mUnBinder.unbind();
        App.getInstance().unRegisterActivity(this);
    }

    protected abstract int getLayout();

    protected void initView() {
    }

    protected void initEvent() {
    }

    protected void getIntentData() {
    }
}

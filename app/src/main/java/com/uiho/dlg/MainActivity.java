package com.uiho.dlg;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.uiho.dlg.base.ui.BaseMvpActivity;
import com.uiho.dlg.ui.fragment.FindFragment;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.frame_content)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    //最后一次按钮位置
    protected int lastSelectedPosition = 0;
    private FindFragment mFindFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.app_name)).setInactiveIconResource(R.mipmap.ic_launcher_round));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.app_name)).setInactiveIconResource(R.mipmap.ic_launcher_round));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.app_name)).setInactiveIconResource(R.mipmap.ic_launcher_round));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.app_name)).setInactiveIconResource(R.mipmap.ic_launcher_round));
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .setBackgroundResource(R.color.colorWhite);
        bottomNavigationBar
                .setInActiveColor(R.color.colorPrimary);
        bottomNavigationBar
                .setActiveColor(R.color.colorPrimaryDark);
        bottomNavigationBar
                .setFirstSelectedPosition(lastSelectedPosition)

                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * 设置默认显示
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mFindFragment = FindFragment.newInstance("位置");
        transaction.replace(R.id.frame_content, mFindFragment);
        transaction.commit();
    }

    @Override
    public void showError(String msg) {
        Log.i("single", msg);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                mFindFragment = FindFragment.newInstance("位置");
                transaction.replace(R.id.frame_content, mFindFragment);
                break;
            case 1:
                mFindFragment = FindFragment.newInstance("发现");
                transaction.replace(R.id.frame_content, mFindFragment);
                break;
            case 2:
                mFindFragment = FindFragment.newInstance("爱好");
                transaction.replace(R.id.frame_content, mFindFragment);
                break;
            case 3:
                mFindFragment = FindFragment.newInstance("图书");
                transaction.replace(R.id.frame_content, mFindFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}

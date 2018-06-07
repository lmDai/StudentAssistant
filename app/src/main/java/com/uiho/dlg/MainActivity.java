package com.uiho.dlg;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.uiho.dlg.base.ui.BaseMvpActivity;
import com.uiho.dlg.ui.adapter.MainAdapter;
import com.uiho.dlg.ui.controller.model.MainItemModel;
import com.uiho.dlg.util.ShowImageUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.banner)
    MZBannerView banner;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<MainItemModel> mList = new ArrayList<>();

    @Override
    protected int getLayout() {
        showBack = false;
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setTopTitle("首\u3000页");
        List<String> bannerList = new ArrayList<>();
        bannerList.add("http://www.sit.edu.cn/page/main297/images/1.jpg");
        bannerList.add("http://www.sit.edu.cn/page/main297/images/2.jpg");
        bannerList.add("http://www.sit.edu.cn/page/main297/images/4.jpg");
        bannerList.add("http://www.sit.edu.cn/page/main297/images/3.jpg");
        banner.setIndicatorVisible(true);
        banner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        banner.start();//开始轮播
        Resources res = getResources();
        String[] titles = res.getStringArray(R.array.main_item_title);
        TypedArray ar = res.obtainTypedArray(R.array.main_item_img);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        if (titles.length > 0) {
            for (int i = 0; i < titles.length; i++) {
                mList.add(new MainItemModel(resIds[i], titles[i]));
            }
        }
        adapter = new MainAdapter(mList);
        GridLayoutManager layoutManage = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(layoutManage);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.pause();//暂停轮播
    }

    @Override
    protected void initEvent() {
        banner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(mContext, "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            ShowImageUtils.showImageViewGone(context, mImageView, data);
        }
    }

    @Override
    public void showError(String msg) {
        Log.i("single", msg);
    }

    @Override
    protected void initInject() {

    }
}

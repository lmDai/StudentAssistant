package com.uiho.dlg.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uiho.dlg.R;
import com.uiho.dlg.ui.controller.model.MainItemModel;

import java.util.List;

/**
 * 作者：uiho_mac
 * 时间：2018/6/6
 * 描述：首页item
 * 版本：1.0
 * 修订历史：
 */

public class MainAdapter extends BaseQuickAdapter<MainItemModel, BaseViewHolder> {
    public MainAdapter(@Nullable List<MainItemModel> data) {
        super(R.layout.adapter_main_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItemModel item) {

        helper.setText(R.id.txt_title, item.getTitle());
        helper.setImageResource(R.id.img_item, item.getImg());
    }
}

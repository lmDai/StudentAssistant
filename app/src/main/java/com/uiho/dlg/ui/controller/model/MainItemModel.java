package com.uiho.dlg.ui.controller.model;

/**
 * 作者：uiho_mac
 * 时间：2018/6/6
 * 描述：
 * 版本：1.0
 * 修订历史：
 */

public class MainItemModel {
    private int img;
    private String title;

    public MainItemModel(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

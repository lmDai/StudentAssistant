package com.uiho.dlg.app;

import java.io.File;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：常量
 * 版本：1.0
 * 修订历史：
 */

public class Constants {
    private static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";
    public static final String TAG = "single";
}

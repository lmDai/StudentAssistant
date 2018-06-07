package com.uiho.dlg.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 作者：uiho_mac
 * 时间：2018/6/6
 * 描述：页面跳转工具类
 * 版本：1.0
 * 修订历史：
 */

public class UIUtils {
    private static final UIUtils ourInstance = new UIUtils();

    public static UIUtils getInstance() {
        return ourInstance;
    }

    private UIUtils() {
    }


    public void goActivity(Context fromActivity, Class<? extends Activity> toActivity, Bundle bundle) {
        Intent intent = new Intent(fromActivity, toActivity);
        intent.putExtra("bundle", bundle);
        fromActivity.startActivity(intent);
    }

    public void goActivity(Context fromActivity, Class<? extends Activity> toActivity) {
        goActivity(fromActivity, toActivity, null);
    }

    public void goActivityForResult(Activity fromActivity, Class<? extends Activity> toActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(fromActivity, toActivity);
        intent.putExtra("bundle", bundle);
        fromActivity.startActivityForResult(intent, requestCode);
    }

    public void goActivityForResult(Activity fromActivity, Class<? extends Activity> toActivity, int requestCode) {
        goActivityForResult(fromActivity, toActivity, null, requestCode);
    }

    public void goActivityForResult(Context context, Fragment fromActivity, Class<? extends Activity> toActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, toActivity);
        intent.putExtra("bundle", bundle);
        fromActivity.startActivityForResult(intent, requestCode);
    }

    public void goActivityForResult(Fragment fromActivity, Class<? extends Activity> toActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(fromActivity.getActivity(), toActivity);
        intent.putExtra("bundle", bundle);
        fromActivity.startActivityForResult(intent, requestCode);
    }

    public void goActivityForResult(Fragment fromActivity, Class<? extends Activity> toActivity, int requestCode) {
        goActivityForResult(fromActivity, toActivity, null, requestCode);
    }
}

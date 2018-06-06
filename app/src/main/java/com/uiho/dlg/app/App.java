package com.uiho.dlg.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.uiho.dlg.base.component.AppComponent;
import com.uiho.dlg.base.component.AppModule;
import com.uiho.dlg.base.component.DaggerAppComponent;
import com.uiho.dlg.base.component.HttpModule;

import java.util.Stack;

/**
 * 作者：uiho_mac
 * 时间：2018/3/27
 * 描述：
 * 版本：1.0
 * 修订历史：
 */

public class App extends Application {
    private static App instance;
    private Stack<Activity> activityStack;

    public static App getInstance() {
        return instance;
    }

    //64 k引用限制
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 绑定 activity
     *
     * @param activity
     */
    public void registerActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 取消绑定
     *
     * @param activity
     */
    public void unRegisterActivity(Activity activity) {
        if (activityStack != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 清空activity堆栈
     */
    public void removeAll() {
        if (activityStack != null) {
            synchronized (activityStack) {
                Stack<Activity> activityTemp = new Stack<>();
                for (Activity activity : activityStack) {
                    if (null != activity && !activity.isFinishing()) {
                        activityTemp.add(activity);
                        activity.finish();
                    }
                }
                activityStack.removeAll(activityTemp);
            }
        }
    }

    /**
     * 清空堆栈并退出
     */
    public void exitApp() {
        if (activityStack != null) {
            synchronized (activityStack) {
                for (Activity activity : activityStack) {
                    if (null != activity && !activity.isFinishing()) {
                        activity.finish();
                    }
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

}

package com.uiho.dlg.http.helper;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.uiho.dlg.BuildConfig;
import com.uiho.dlg.app.Constants;
import com.uiho.dlg.http.api.Api;
import com.uiho.dlg.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.uiho.dlg.app.Constants.TAG;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：retrofit配置
 * 版本：1.0
 * 修订历史：
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient = null;
    private static Api api;

    public static Api getApi() {
        initOkHttp();//初始化OkHttp
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

    private static void initOkHttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i(TAG, "log: " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BASIC));
            }
            File cacheFile = new File(Constants.PATH_CACHE);//设置网络缓存
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!SystemUtil.isNetworkConnected()) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    int tryCount = 0;
                    Response response = chain.proceed(request);
                    while (!response.isSuccessful() && tryCount < 3) {
                        tryCount++;
                        // retry the request
                        response = chain.proceed(request);
                    }
                    if (SystemUtil.isNetworkConnected()) {
                        int maxAge = 0;
                        // 有网络时, 不缓存, 最大保存时长为0
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            //设置缓存
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }
}

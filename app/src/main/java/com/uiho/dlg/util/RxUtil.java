package com.uiho.dlg.util;

import com.uiho.dlg.http.baseResponse.BaseListResponse;
import com.uiho.dlg.http.baseResponse.BaseResponse;
import com.uiho.dlg.http.exception.ApiException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：uiho_mac
 * 时间：2018/3/28
 * 描述：RxUtil 结果预处理
 * 版本：1.0
 * 修订历史：
 */

public class RxUtil {
    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 请求结果处理
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        if (tBaseResponse.getCode() == 200) {
                            return Observable.just(tBaseResponse.getData());
                        } else {
                            return Observable.error(new ApiException(tBaseResponse.getMsg()));
                        }
                    }
                });
            }
        };
    }

    public static <T> ObservableTransformer<BaseListResponse<T>, List<T>> handleListResult() {
        return new ObservableTransformer<BaseListResponse<T>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<BaseListResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseListResponse<T>, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(BaseListResponse<T> tBaseListResponse) throws Exception {
                        if (tBaseListResponse.getCode() ==0) {
                            return Observable.just(tBaseListResponse.getData());
                        } else {
                            return Observable.error(new ApiException(tBaseListResponse.getMessage()));
                        }
                    }
                });
            }
        };
    }

}

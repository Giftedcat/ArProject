package com.eastudio.ardemo.http.listener;

/**
 * Created by dyf on 2017/3/10.
 */

public interface OnLoadListener<T> {

    /**
     * 成功时回调
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError(String msg);
}

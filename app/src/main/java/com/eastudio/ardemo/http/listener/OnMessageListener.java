package com.eastudio.ardemo.http.listener;

/**
 * Created by Ricardo on 2018/1/26.
 */

public interface OnMessageListener<T> {

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

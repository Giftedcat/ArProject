
package com.git.easylib.http;

import android.app.Activity;

import com.git.easylib.http.callback.HttpListener;
import com.git.easylib.http.callback.HttpResponseListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.Map;

/**
 * Created in Oct 23, 2015 1:00:56 PM.
 *
 * @author Yan Zhenjie.
 */
public class HttpManager {

    private static HttpManager instance;
    Request<String> mRequest = null;

    public static HttpManager getInstance() {
        if (instance == null)
            synchronized (HttpManager.class) {
                if (instance == null)
                    instance = new HttpManager();
            }
        return instance;
    }

    private RequestQueue mRequestQueue;
    private DownloadQueue mDownloadQueue;


    private HttpManager() {
        mRequestQueue = NoHttp.newRequestQueue(5);
        mDownloadQueue = NoHttp.newDownloadQueue(3);
    }


//    //exist some probleam
//    public <T> void post(Activity activity, String url, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading){
//
//        mRequest = NoHttp.createStringRequest(url, RequestMethod.POST);
//
////        request(0, mRequest, callback, canCancel, isLoading);
//        request(activity, 0, request, callback, canCancel, isLoading);
//    }


    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        mRequestQueue.add(what, request, listener);
    }

    public <T> void request(int what, Request<T> request, HttpListener<T> callback) {
        mRequestQueue.add(what, request, new HttpResponseListener<>(request, callback));
    }

    public <T> void request(Activity activity, int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        mRequestQueue.add(what, request, new HttpResponseListener<>(activity, request, callback, canCancel, isLoading));
    }

    public void download(int what, DownloadRequest request, DownloadListener listener) {
        mDownloadQueue.add(what, request, listener);
    }

    /**
     * @param appurl
     * @param params
     * @param callback
     */
    public void add(String appurl, Map<String,String> params, HttpListener<String> callback) {
        Request<String> request = NoHttp.createStringRequest(appurl, RequestMethod.POST);
        if (null != request && null != params && params.size() >0) {
            for (Map.Entry entry : params.entrySet()) {
                request.add(entry.getKey()+"", entry.getValue()+"");// String类型
            }
        }
        mRequestQueue.add(0, request, new HttpResponseListener<>( request, callback));
    }

    public <T> void add(int what, String appurl, Class<T> _class, Map<String,String> params, HttpListener<T> callback) {
        Request<T> request = new JavaBeanRequest<>(appurl, _class);
        if (null != request && null != params && params.size() >0) {
            for (Map.Entry entry : params.entrySet()) {
                request.add(entry.getKey()+"", entry.getValue()+"");// String类型
            }
        }
//        requestQueue.add(what, request, new HttpResponseListener<>(context, request, callback, canCancel, isLoading));
        mRequestQueue.add(what, request, new HttpResponseListener<>( request, callback));
    }



}

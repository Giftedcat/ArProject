package com.eastudio.ardemo.http.action;

import com.eastudio.ardemo.http.Appurl;
import com.eastudio.ardemo.http.action.bean.UserInfoBean;
import com.eastudio.ardemo.http.listener.OnLoadListener;
import com.eastudio.ardemo.http.model.UserInfo;
import com.git.easylib.EventMessage.BaseBean;
import com.git.easylib.http.HttpManager;
import com.git.easylib.http.callback.HttpListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by m on 16-3-30.
 */
public class UserAction {



    /**
     *
     * @param phone
     * @param listener
     */
    public void getCode(String phone, final OnLoadListener<BaseBean> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);

        HttpManager.getInstance().add(0, Appurl.User.REGISTER_URL, BaseBean.class, map, new HttpListener<BaseBean>() {
            @Override
            public void onSucceed(int what, Response<BaseBean> response) {
                if (null !=  response && response.get() != null){
                    if (response.get().getResultCode() == 200) {
                        listener.onSuccess(response.get());
                    }else{
                        listener.onError(response.get().getResultMsg());
                    }
                }else{
                    listener.onError("请稍后尝试");
                }
            }

            @Override
            public void onFailed(int what, Response<BaseBean> response) {
                listener.onError(response == null ? "网络异常" : response.toString());
            }
        });
    }


    /**
     *
     * @param info
     * @param listener
     */
    public void login(UserInfo info, final UserActinCallback<UserInfoBean> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("username", info.getUsername());
        map.put("verifyCode", info.getVerifyCode());

        HttpManager.getInstance().add(0, Appurl.User.REGISTER_URL, UserInfoBean.class, map, new HttpListener<UserInfoBean>() {
            @Override
            public void onSucceed(int what, Response<UserInfoBean> response) {
                if (null !=  response && response.get() != null){
                    UserInfoBean userInfoBean = response.get();
                    if (userInfoBean.getResultCode() == 200){
                        listener.loginSuccess(response.get());
                        return;
                    }
                    listener.loginFailed(response.get().getResultMsg());
                    return;
                }
                listener.loginFailed("请稍后尝试");
            }

            @Override
            public void onFailed(int what, Response<UserInfoBean> response) {
//                MyLogUtil.i(response.toString());
                listener.onFailed(response == null ? "网络异常" : response.toString());
            }
        });
    }

    /**
     *
     * @param info
     * @param listener
     */
    public void tokenLogin(UserInfo.UserBean info, final OnLoadListener<UserInfoBean> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("username", info.getUsername());
        map.put("token", info.getToken());

        HttpManager.getInstance().add(0, Appurl.User.REGISTER_URL, UserInfoBean.class, map, new HttpListener<UserInfoBean>() {
            @Override
            public void onSucceed(int what, Response<UserInfoBean> response) {
                if (null !=  response && response.get() != null){
                    UserInfoBean userInfoBean = response.get();
                    if (userInfoBean.getResultCode() == 200){
                        listener.onSuccess(response.get());
                        return;
                    }
                    listener.onError(response.get().getResultMsg());
                    return;
                }
                listener.onError("请稍后尝试");
            }

            @Override
            public void onFailed(int what, Response<UserInfoBean> response) {
//                MyLogUtil.i(response.toString());
                listener.onError(response == null ? "网络异常" : response.toString());
            }
        });
    }



    public interface UserActinCallback <T>{

        void loginSuccess(T t);

        void loginFailed(String msg);

        void onFailed(String msg);

    }

}

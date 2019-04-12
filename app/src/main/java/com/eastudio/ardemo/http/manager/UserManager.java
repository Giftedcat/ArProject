package com.eastudio.ardemo.http.manager;


import android.text.TextUtils;

import com.eastudio.ardemo.contants.GlobalEnv;
import com.eastudio.ardemo.http.action.UserAction;
import com.eastudio.ardemo.http.action.bean.UserInfoBean;
import com.eastudio.ardemo.http.listener.OnLoadListener;
import com.eastudio.ardemo.http.model.UserInfo;
import com.eastudio.ardemo.util.ConstCode;
import com.git.easylib.EventMessage.BaseBean;
import com.git.easylib.L;
import com.git.easylib.util.StoreUtil;
import com.google.gson.Gson;

/**
 * 项目名称：klnsh
 * 类描述：用户操作的相关接口的提供者
 * 创建人：枫林太晚
 * 修改人：枫林太晚
 * 修改备注：
 * @author Ricardo
 */
public class UserManager {
    private static UserManager instance;
    private UserInfo userinfo;
    private UserAction userAction;


    /**
     * 获取UserManger实例化对象
     *
     * @return 用户管理类
     */
    public static UserManager getInstance() {
        if (null == instance) {
            instance = new UserManager();
            instance.userAction = new UserAction();
        }
        return instance;
    }


    /**
     * 获取用户信息
     *
     * @return UserInfo 对象
     */
    public UserInfo getUserInfo() {
        if (userinfo == null) {
            String infoString = StoreUtil.getString(ConstCode.STORAGE_USERINFO_KEY, null);
            if (!TextUtils.isEmpty(infoString)) {
                userinfo = new Gson().fromJson(infoString, UserInfo.class);
            } else {
                userinfo = new UserInfo();
            }
        }
        return userinfo;
    }


    /**
     *
     * @param info
     * @param callback
     */
    public void userRegister(UserInfo.UserBean info, final UserAction.UserActinCallback<UserInfoBean> callback) {
//        userAction.login(info, new UserAction.UserActinCallback<UserInfoBean>(){
//
//            @Override
//            public void loginSuccess(UserInfoBean userInfoBean) {
//                saveLoginInfo(userInfoBean.getResultContent());
//                L.d("AAA" + userInfoBean.getResultContent().getUser().getId());
////                loginHuanxin(data.getId());
//
////                if (PushManager.getInstance() != null) {
////                    //登陆socket推送服务
//////                    PushManager.getInstance().login();
////                }
//                //!!!!!!!!!!!!!!!!!!!!
//                callback.loginSuccess(userInfoBean);
//            }
//
//            @Override
//            public void loginFailed(String msg) {
//                callback.loginFailed(msg);
//            }
//
//            @Override
//            public void onFailed(String msg) {
//                callback.onFailed(msg);
//            }
//        });
    }


    /**
     * 验证码获取
     * @param phone
     * @param callback
     */
    public void sendVerifyCode(String phone, final OnLoadListener<BaseBean> callback) {
        userAction.getCode(phone, callback);
    }


    /**
     *
     * @param info
     * @param callback
     */
    public void userLogin(UserInfo info, final UserAction.UserActinCallback<UserInfoBean> callback){
        userAction.login(info, new UserAction.UserActinCallback<UserInfoBean>(){

            @Override
            public void loginSuccess(UserInfoBean userInfoBean) {
                GlobalEnv.userInfo = null;
                saveLoginInfo(userInfoBean.getResultContent());
                L.d("AAA" + userInfoBean.getResultContent().getUser().getId());
//                loginHuanxin(data.getId());

//                if (PushManager.getInstance() != null) {
//                    //登陆socket推送服务
////                    PushManager.getInstance().login();
//                }
                //!!!!!!!!!!!!!!!!!!!!
                callback.loginSuccess(userInfoBean);
            }

            @Override
            public void loginFailed(String msg) {
                callback.loginFailed(msg);
            }

            @Override
            public void onFailed(String msg) {
                callback.onFailed(msg);
            }
        });
    }


    /**
     * token login
     *
     * @param info
     * @param callback
     */
    public void tokenLogin(UserInfo.UserBean info, final OnLoadListener<UserInfoBean> callback){
        userAction.tokenLogin(info, new OnLoadListener<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                GlobalEnv.userInfo = null;
                saveLoginInfo(userInfoBean.getResultContent());
                L.d("AAA" + userInfoBean.getResultContent().getUser().getId());
//                loginHuanxin(data.getId());

//                if (PushManager.getInstance() != null) {
//                    //登陆socket推送服务
////                    PushManager.getInstance().login();
//                }
                //!!!!!!!!!!!!!!!!!!!!
                callback.onSuccess(userInfoBean);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }


    public void saveLoginInfo(UserInfo data) {
        userinfo = data;
        StoreUtil.put(ConstCode.STORAGE_USERINFO_KEY, new Gson().toJson(userinfo));
    }

}

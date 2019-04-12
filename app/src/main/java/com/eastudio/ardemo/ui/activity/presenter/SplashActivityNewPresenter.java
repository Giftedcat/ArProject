package com.eastudio.ardemo.ui.activity.presenter;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.eastudio.ardemo.contants.Contants;
import com.eastudio.ardemo.http.action.bean.UserInfoBean;
import com.eastudio.ardemo.http.listener.OnLoadListener;
import com.eastudio.ardemo.http.listener.OnTaskListener;
import com.eastudio.ardemo.http.manager.UserManager;
import com.eastudio.ardemo.http.model.UserInfo;
import com.eastudio.ardemo.ui.activity.SplashActivityNew;
import com.eastudio.ardemo.widget.CustomDialog;
import com.git.easylib.L;
import com.google.gson.Gson;
import com.jude.beam.expansion.BeamBasePresenter;

import com.eastudio.ardemo.R;


/**
 * 文 件 名: SplashActivityNewPresenter
 * 创建日期: 2017/1/19 0019 11:39
 * 修改时间：
 * 修改备注：
 * @author dyf
 */
public class SplashActivityNewPresenter extends BeamBasePresenter<SplashActivityNew> {

    private int app_versionCode;
    private String app_versionName;
    private String app_url;
    private CustomDialog.Builder mAppUpdateBuilder;
    private CustomDialog mAppUpdaterDialog;
    private AlertDialog dialog;
    private String app_description;

    @Override
    protected void onCreateView(@NonNull SplashActivityNew view) {
        super.onCreateView(view);

    }


    /**
     * token login
     */
    public void tokenLogin(final OnTaskListener<String> callback){
        UserInfo mUserInfo = UserManager.getInstance().getUserInfo();
        if (mUserInfo != null && mUserInfo.getUser() != null){
            UserManager.getInstance().tokenLogin(mUserInfo.getUser(), new OnLoadListener<UserInfoBean>() {
                @Override
                public void onSuccess(UserInfoBean userInfoBean) {
                    L.e("UserInfo:" + new Gson().toJson(userInfoBean));
////                GlobalEnv.userInfomodel = UserInfoModel.from(data);
////                GlobalEnv.userInfomodel.setLogin_type(0 + "");
//                    // 登陆成功，去服务器获取绑定的设备
////                    getBindDevicesFromServer(userInfoBean.getResultContent().getUser().getToken());
//                    Device device = new Device();
//                    device.setToken(GlobalEnv.getUserInfo().getUser().getToken());
//                    queryBind(device, callback);
                }

                @Override
                public void onError(String msg) {
                    callback.onFinish("error");
                }
            });
        }else{
            getView().toNextActivity();
        }
    }




    public void checkAppVersion() {
//        DeviceManagerActivity.getInstance().CheckWatchFirmwareVersion("android", new HttpCallback<Object>() {
//            @Override
//            public void onSuccess(Object data) {
//                try {
//                    String response_data = new Gson().toJson(data);
//                    L.e("response_data:" + response_data);
//                    JSONObject mJSONObject = new JSONObject(response_data);
//                    app_versionCode = mJSONObject.getInt("ver_code");
//                    app_versionName = mJSONObject.getString("version");
//                    app_description = mJSONObject.getString("description");
//                    app_url = mJSONObject.getString("path");
////                    if (app_versionCode > Contants.APP_VERSIONCODE) {
////                        NewUIThreadUtil.runOnMainThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                DialogView();
////                            }
////                        });
////                    } else {
////                        getView().runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                getView().checkPermission();
////                            }
////                        });
////
////                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
////                    getView().runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            getView().checkPermission();
////                        }
////                    });
//                }
//            }
//
//            @Override
//            public void onFail(int code, String msg) {
//                L.e("onFail  msg:" + msg);
////                getView().runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        getView().checkPermission();
////                    }
////                });
//            }
//        });
    }


    public void checkVersionResult() {
        getView().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (app_versionCode > Contants.APP_VERSIONCODE) {
                    DialogView();
                } else {
                    getView().notSkip=false;
                    getView().GoNextActivity();
                }
            }
        });

    }

    public void DialogView() {
//        mAppUpdateBuilder = new CustomDialog.Builder(getView()).setMessage(getView().getString(R.string.new_version_update))
//                .setNegativeButton(getView().getResources().getString(R.string.choose_dialog_cancle),
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                mAppUpdaterDialog.dismiss();
//                            }
//                        }).setPositiveButton(getView().getResources().getString(R.string.choose_dialog_ok), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (!TextUtils.isEmpty(app_url)) {
//                            Intent intent = new Intent(getView(), AppUpdateService.class);
//                            intent.putExtra("apk_url", app_url);
//                            getView().startService(intent);
//                        }
//                        mAppUpdaterDialog.dismiss();
//                    }
//                });
//        mAppUpdaterDialog = mAppUpdateBuilder.create();
//        mAppUpdaterDialog.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(getView());
        builder
                .setNegativeButton(R.string.next_time_to_update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getView().notSkip=false;
                        getView().GoNextActivity();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getView().getResources().getString(R.string.choose_dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!TextUtils.isEmpty(app_url)) {
//                            Intent intent = new Intent(getView(), AppUpdateService.class);
//                            intent.putExtra("apk_url", app_url);
//                            getView().startService(intent);
                        }
                        getView().notSkip=false;
                        getView().GoNextActivity();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .setMessage(TextUtils.isEmpty(app_description) ? getView().getResources().getString(R.string.now_update) : app_description);
        dialog = builder.show();
    }
}

package com.eastudio.ardemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.eastudio.ardemo.ui.util.NewUICommonUtil;
import com.yanzhenjie.nohttp.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 *
 * @author Ricardo
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        try {
            Bundle bundle = intent.getExtras();
            Logger.d("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Logger.d("[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Logger.d("[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                processCustomMessage(context, bundle);
                //
                JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                String msgType = json.getString("msgType");
                if (null != msgType && !"".equals(msgType)) {
                    switch (msgType) {
                        case "contact_setting": {
                            //亲情号码下发成功
                            EventBus.getDefault().post("AddressBooks");
                            break;
                        }
                        case "sos_setting": {
                            //SOS号码下发成功
                            EventBus.getDefault().post("SOSNumber");
                            break;
                        }
                        case "balance_query": {
                            //余额查询结果
//							EventBus.getDefault().post(new BanlanceMsg(200, msgType));
                            break;
                        }
                        case "bill_query": {
                            //账单查询结果
                            break;
                        }
                        case "watch_online": {
                            //手表状态查询结果
//							String msgContent = json.getString("msgContent");
//							JSONObject obj = new JSONObject(msgContent);
//							String watch_online = obj.getString("is_online");
//							GlobalEnv.getCurrentDevice().setWatchStatus(Integer.parseInt(watch_online));
//							ObjectMsg msg = new ObjectMsg();
//							msg.setCode(1);
//							EventBus.getDefault().post(msg);
                            break;
                        }
                        case "location_query": {
                            //定位指令推送
                            EventBus.getDefault().post("");
                            break;

                        }
                        default:
                            break;
                    }
                }


            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Logger.d("[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Logger.d("[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

                //
                JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                String msgType = json.getString("msgType");
                if (null != msgType && !"".equals(msgType)) {
                    switch (msgType) {
                        case "logout":
                            //登出
                            NewUICommonUtil.logout(context);
                            break;
                        case "school_bulletin":
                            //学校公告
                            EventBus.getDefault().post("school_bulletin");
                            break;
                        case "class_bulletin":
                            //班级通知
                            EventBus.getDefault().post("class_bulletin");
                            break;
//						case "attendance_msg":
//							//考勤通知
//							EventBus.getDefault().post(new AttendanceMsg(200, msgType));
//							break;
                        default:
                            break;
                    }
                }
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d("[MyReceiver] 用户点击打开了通知");
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);

                JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                String msgType = json.getString("msgType");
                if (null != msgType && !"".equals(msgType)) {
                    switch (msgType) {
                        case "school_bulletin":
                            //学校公告
                            ARouter.getInstance().build("/com/kln/schAnnouncement").navigation(mContext);
                            break;
                        case "class_bulletin":
                            //班级通知
                            ARouter.getInstance().build("/com/kln/classNotify").navigation(mContext);
                            break;
                        default:
                            break;
                    }
                }
//				//打开自定义的Activity
//				Intent i = new Intent(context, TestActivity.class);
//				i.putExtras(bundle);
//				//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//				context.startActivity(i);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Logger.w("[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
                //
                String res = JPushInterface.getRegistrationID(context);
                Logger.d("[MyReceiver] 接收Registration Id : " + res);
            } else {
                Logger.d("[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i("This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.e("Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//		}
    }
}

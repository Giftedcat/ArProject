package com.git.easylib.http;

/**
 * Created by m on 16-3-29.
 */
public class HttpUrlConfig {
    /**
     *  国内 的APP
     */
    public static String PUSH_HOST = "suncloud.chinacloudapp.cn";
    public static int PUSH_HOST_PORT = 8282;
    //正式发布用这个，对外提供sdk时也要切到这个
//    public static String host = "http://suncloud.chinacloudapp.cn/rest.php/api";
    //测试用
    public static String host = "http://suncloud.chinacloudapp.cn:8081/rest.php/api";
    //服务器测试用
//    public static String host = "http://suncloud.chinacloudapp.cn:9081/rest.php/api";
    /**
     * 国外 tccm用
     */
//    public static String PUSH_HOST = "cloud.sunyoucloud.com";//"112.124.63.217";//
//    public static int PUSH_HOST_PORT = 8282;
//    public static String host = "http://cloud.sunyoucloud.com/rest.php/api";

    /**
     * 国外2 除tccm的以外的海外
     */
//    public static String PUSH_HOST = "cloud.sunyoucloud.com";//"112.124.63.217";//
//    public static int PUSH_HOST_PORT = 8282;
//    public static String host = "http://cloud.sunyoucloud.com:9081/rest.php/api";        //http://test.aiaiaini.xyz/rest.php/api";


    public static String hostCH = "http://suncloud.chinacloudapp.cn:8081/rest.php/api";
//    public static String hostCH = "http://192.168.1.88/rest.php/api";
    public static String hostHW = "http://cloud.sunyoucloud.com:9081/rest.php/api";
    public static String PUSH_HOST_CH = "suncloud.chinacloudapp.cn";
    public static String PUSH_HOST_HW = "cloud.sunyoucloud.com";


    public static String URL_GET_ALL_ATTR_DATA = "/attr/get-all-attr-data";
    /**
     * 获取某个属性
     */
    public static String URL_GET_ATTR_DATA = "/attr/get-attr-data";
    /**
     * 设置某个属性
     */
    public static String URL_SET_ATTR_DATA = "/attr/set-attr-data";

    /**
     * 获取数据记录
     */
    public static String URL_GET_DATAS = "/data/get-datas";
    /**
     * 添加数据记录
     */
    public static String URL_ADD_DATAS = "/data/add-data";


    /**
     * 2.4.4.1注册用户
     */
    public static String URL_USER_REGISTER = "/user/register";
    /**
     * 2.4.4.2用户登陆
     */
    public static String URL_USER_LOGIN = "/user/login";

    /**
     * 2.4.4.5. 根据旧密码修改用户账号
     */
    public static String URL_USER_RESET_PASSWORD = "/user/reset-password";
    /**
     * 2.4.4.6. 更新用户推送信息(如果与原来token不一致，通知原来设备下线)
     */
    public static String URL_USER_UPDATE_CLIENT = "/user/update-client";
    /**
     * 2.4.4.7. 用户退出
     */
    public static String URL_USER_LOGOUT = "/user/logout";
    /**
     * 2.4.4.8. 更新用户额外属性 POST: /user/update
     */
    public static String URL_UPDATE_USER_EXTRA_PROPERTY = "/user/update";
    /**
     * 2.4.4.9
     */
    public static String URL_THIRD_USER_LOGIN = "/user/sns-login";
    /**
     * 根据验证码重置密码
     */
    public static String URL_RESET_PASSWORD_BY_VERIFY = "/user/reset-password-by-verify";


    /**
     *2.4.7.1 设置围栏数据(目前只支持圆形)
     */
    public static String URL_POST_ADD_GURAD= "/sys-data/add-guard";

    /**
     *2.4.7.2 获取围栏数据
     */
    public static String URL_POST_GET_GUARD_DATA= "/sys-data/get-guards";


    /**
     *2.4.7.3 删除围栏
     */
    public static String URL_POST_DEL_GUARD= "/sys-data/delete-guard";

    /**
     *2.4.7.4  修改围栏数据 POST: sys-data/update-guard
     */
    public static String URL_POST_UPDATE_GURAD= "/sys-data/update-guard";

    /**
     *2.4.8.1 发送验证码（支持手机和邮箱）
     */

    public static String URL_POST_SEND_VERIFY= "/verify/send-verify";


    /**
     *2.4.8.2 验证验证码是否正确有效（支持手机和邮箱）
     */
    public static String URL_POST_CHECK_VERIFY = "/user/check-verify";


    /**
     * 2.4.9.1 添加群组
     */
    public static String URL_POST_ADD_GROUP = "/group/create";

    /**
     * 2.4.9.2 添加群组客户端
     */
    public static String URL_POST_ADD_GROUP_CLIENT = "/group/add-client";

    /**
     * 2.4.9.3 将设备添加进群组
     */
    public static String URL_POST_ADD_GROUP_DEVICE = "/group/add-device";

    /**
     * 2.4.9.4 移除群组成员(群组所有者或者管理员可以执行)
     */
    public static String URL_POST_REMOVE_GROUP_CLIENT = "/group/remove-client";

    /**
     * 2.4.9.5 删除群组(群组所有者可以执行)
     */
    public static String URL_POST_REMOVE_GROUP = "/group/delete";


    /**
     * 2.4.9.6 修改群组内成员角色(群组所有者可以执行)
     */
    public static String URL_POST_GROUP_CHANGE_ADMIN = "/group/change-admin";

    /**
     * 2.4.9.7 获取群组内客户端
     */
    public static String URL_GET_GROUP_CLIENT = "/group/get-clients";


    /**
     * 2.4.9.8 更新某个用户在群组内的信息
     */
    public static String URL_UPDATE_GROUP_CLIENT_PROPERTY = "/group/update-client-property";

    /**
     * 2.4.9.9 获取群组所有用户信息
     */
    public static String URL_GET_GROUP_USER_CLIENTS = "/group/get-user-clients";


    /**
     * 2.4.9.10 获取所有群组
     */
    public static String URL_GET_GROUPS = "/group/get-groups";


    /**
     * 1.用户绑定设备
     */
    public static String URL_DEVICE_BIND = "/device/bind";

    /**
     * 2.获取绑定设备的所有用户
     * GET: /device/get-users
     */
    public static String URL_GET_DEVICE_BIND_USERS = "/device/get-users";
    /**
     * 3.获取用户的所有绑定设备
     */
    public static String URL_GET_USER_DEVICES = "/device/get-devices";
    /**
     * 转移管理员
     */
    public static String URL_DEVICE_CHANGE_ADMIN = "/device/change-admin";

    /**
     * 5.获取指定设备基本信息
     * 地址
     * <p/>
     * GET: /device/get-device
     */
    public static String URL_DEVICE_GET_DEVICE = "/device/get-device";
    /**
     * 6.根据imei查询设备绑定状态
     * 地址
     * GET: /device/get-bind-status
     * <p/>
     * 是否需要登录
     */
    public static String URL_DEVICE_GET_BIND_STATUS = "/device/get-bind-status";
    /**
     * 7.获取设备最新地址
     * GET: device/get-last-position
     * 是否需要登录  是
     */
    public static String URL_DEVICE_GET_LAST_POSITION = "/device/get-last-position";

    /**
     * 8.获取设备历史位置数据
     */
    public static String URL_DEVICE_GET_POSITION_HISTORY = "/device/get-positions";
    /**
     * 9.解除绑定
     */
    public static String URL_DEVICE_UNBIND = "/device/un-bind";
    /**
     * 10.解除设备的所有绑定人员
     */
    public static String URL_DEVICE_UNBIND_ALL = "/device/un-bind-all";


    /**
     *2.4.5.20 更新设备基本属性(性别，年龄等需要随设备直接返回信息)
     */
    public static String URL_DEVICE_UPDATE_PROPERTY= "/device/update-property";
    /**
     *2.4.5.21.获取设备基本属性(性别，年龄等需要随设备直接返回信息)
     * get: devie/get-property
     */
    public static String URL_DEVICE_GET_PROPERTY= "/device/get-property";
    /**
     *2.4.5.22.更新设备昵称，电话号码及基本属性(性别，年龄等需要随设备直接返回信息)
     * get: devie/update
     */
    public static String URL_DEVICE_UPDATE_MULTIPLE_PROPERTY= "/device/update";
    /**
     * 修改用户绑定昵称
     */
    public static String URL_DEVICE_UPDATE_BIND_NICK = "/device/update-bind-nick";

    /**
     * 添加好友
     */
    public static String URL_POST_ADD_FRIEND = "/friend/add-friend";

    /**
     * 获取好友
     */
    public static String URL_GET_FRIEND = "/friend/get-friends";

    /**
     * 删除好友
     */
    public static String URL_POST_DEL_FRIEND = "/friend/delete-friend";

    public static String URL_POST_UPLOAD_FILE = "/media/upload";

    /**********设备特殊操作***********/
    /**
    * 1.修改绑定昵称
    * */
    public static String URL_POST_UPDATE_BINDER_NICK = "/device/update-bind-nick";

    /**
     * 2.修改设备昵称
     */
    public static String URL_POST_EDIT_DEVICE_NICK = "/device/edit-device-nick";

    /**
     *3.创建设备默认群(管理员调用，会将设备和管理员添加到默认群中)
     */
    public static String URL_POST_CREATE_DEFAULT_DEVICE_GROUP = "/device/create-default-group";

    /**
     *4.获取设备的默认群
     */
    public static String URL_GET_DEFAULT_DEVICE_GROUP = "/device/get-default-group";

    /**
     * 5.获取设备群列表
     */
    public static String URL_GET_DEVICE_GROUPS = "/device/get-device-groups";

    /**
     * 6.管理员将设备退出指定群
     */
    public static String URL_POST_DEVICE_OUT_FROM_GROUP = "/device/out-from-group";

    /**
     * 7.管理员给设备添加好友
     */
    public static String URL_POST_ADD_FRIEND_TO_DEVICE = "/device/add-friend";

    /**
     * 8.管理员删除设备指定好友
     */
    public static String URL_POST_DELETE_FRIEND_FROM_DEVICE = "/device/delete-friend";

    /**
     * 9.获取设备好友列表
     */
    public static String URL_GET_FRIENDS_FROM_DEVICE = "/device/get-friends";

    /**
     * 10.绑定创建组合并操作
     */
    public static String URL_POST_BIND_CREATE_GROUP ="/device/bind-special-group";

    /**
     *解绑删除组合并操作
     */
    public static String URL_POST_UNBIND_REMOVE_GROUP ="/device/un-bind-special-group";

    /**
     * 12.绑定关系，组关系管理员转移
     地址
     POST: /device/change-special-admin
     */
    public static String URL_POST_CHANGE_SPECIAL_ADMIN = "/device/change-special-admin";
    /**
    * 2.4.6.1
    * */
    public static String URL_GET_MESSAGE = "/message/get-message";

    /**
     * 2.4.12 更改升级包
     */
    public static String URL_GET_VERSION = "/package/get-version";


    /**
     * 给设备发消息的接口
     */
    public static String URL_PUSH_TO_DEVICE = "/push-message/push";


    /**
     * 获取设备图片
     */
    public static String URL_GET_IMGS = "/media/get-imgs";

    /**
     * 删除设备图片
     */
    public static String URL_DEL_IMGS = "/media/del-imgs";

    /**
     * 删除消息
     */
    public static String URL_DEL_MESSAGE = "/message/del-message";

    /**
     * 问题反馈
     */
    public static String URL_FEEDBACK = "/feedback/problem";

    /** 获取手表版本 */
    public static String GET_WATCH_VERSION = "/device/get-firmware";
}

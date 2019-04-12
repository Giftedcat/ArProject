package com.eastudio.ardemo.http;

/**
 *
 * @author dyf
 * @date 16-3-29
 */
public class Appurl {

    public static String host = "http://192.168.0.102:8088";
    public static String weather_host = "https://www.sojson.com";


    /**
     *
     */
    public static class User {

        /**
         * 注册接口
         */
        public static String REGISTER_URL = host + "/app/user/register.json";

    }

    /**
     *
     */
    public static class Device {

        /**
         * 绑定信息
         */
        public static String BINDADMIN_URL = host + "/app/device/bindAdmin.json";

    }


    /**
     *
     */
    public static class Other{

        /**
         * 温度、天气获取接口
         */
        public static String WEATHER = weather_host + "/open/api/weather/json.shtml";

        /**
         * 动态获取功能列表
         */
        public static String GETFEATURE = host + "/app/feature/getFeature.json";

    }

}

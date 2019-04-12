package com.git.easylib.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则相关工具类
 * Created by luzhuwen on 2017/3/29.
 */
public class RegularUtils {

    /** 正则：手机号 */
    public static final String REGEX_MOBILE_SIMPLE = "1[34578]\\d{9}";

    /** 正则：电话号码 */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";

    /** 正则：身份证号码18位 */
    public static final String REGEX_IDCARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";

    /** 正则：邮箱 */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /** 正则：URL */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";

    /** 正则：汉字 */
    public static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";

    /** 正则：数字 */
    public static final String REGEX_NUM = "^[0-9]*$";

    /** 正则：英文和数字 */
    public static final String REGEX_ENG_AND_NUM = "^[A-Za-z0-9]+$";

    /** 正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位 */
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";

    /** 正则：密码，6-12位字母或数字 */
    public static final String REGEX_PASSWOLD = "[0-9A-Za-z]{6,12}$";

    /** 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年 */
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";

    /** 正则：IP地址 */
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * If u want more please visit http://toutiao.com/i6231678548520731137/
     */

    // 私有构造
    public RegularUtils() {}

    /**
     * 验证手机号（简单）
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isMobileSimple(String string) {
        return isMatch(REGEX_MOBILE_SIMPLE, string);
    }

    /**
     * 验证电话号码
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isTel(String string) {
        return isMatch(REGEX_TEL, string);
    }

    /**
     * 验证身份证号码18位
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isIDCard18(String string) {
        return isMatch(REGEX_IDCARD18, string);
    }

    /**
     * 验证邮箱
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }

    /**
     * 验证URL
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isURL(String string) {
        return isMatch(REGEX_URL, string);
    }

    /**
     * 验证汉字
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isChz(String string) {
        return isMatch(REGEX_CHZ, string);
    }

    /**
     * 验证数字
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isNumber(String string) {
        return isMatch(REGEX_NUM, string);
    }

    /**
     * 验证用户名
     * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isUsername(String string) {
        return isMatch(REGEX_USERNAME, string);
    }

    /**
     * 判断是否为Emoji表情
     * @param string
     * @return
     */
    public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

    /**
     * 验证密码是否匹配要求
     * 取值范围为a-z,A-Z,0-9，6-12位
     * @param string 待验证密码字符串
     * @return true:匹配; false:不匹配
     */
    public static boolean isPassword(String string) {
        return isMatch(REGEX_PASSWOLD, string);
    }

    /**
     * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isDate(String string) {
        return isMatch(REGEX_DATE, string);
    }

    /**
     * 验证IP地址
     * @param string 待验证文本
     * @return true:匹配; false:不匹配
     */
    public static boolean isIP(String string) {
        return isMatch(REGEX_IP, string);
    }

    /**
     * string是否匹配regex
     * @param regex  正则表达式字符串
     * @param string 要匹配的字符串
     * @return true:匹配; false:不匹配
     */
    public static boolean isMatch(String regex, String string) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regex, string);
    }

}
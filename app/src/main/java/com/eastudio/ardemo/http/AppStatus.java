package com.eastudio.ardemo.http;

import java.util.HashMap;
import java.util.Map;

public class AppStatus {
	/**
	 * @描述 HTTP请求状态
	 */
	public static final class Request_Status {
		public static final int RESULT_OK = 200; // 成功数据成功
		public static final int RESULT_300 = 300; //服务器操作异常
		public static final int RESULT_400 = 400;	//错误
		public static final int RESULT_0000 = 10010; //token过期
		public static final int RESULT_0901 = 901;	  //网络存在问题
		public static final int RESULT_88888888 = 88888888;	//数据解析异常
		public static final int RESULT_99999999 = 99999999;	//返回的code错误
		
		public static final Map<Integer,String> reqStateMap = new HashMap<Integer,String>();
		
		static{
			reqStateMap.put(RESULT_OK, "接口调用成功");
			reqStateMap.put(RESULT_300, "系统异常");
			reqStateMap.put(RESULT_400, "账号密码错误");
			reqStateMap.put(RESULT_0000, "用户编号不存在");
			reqStateMap.put(RESULT_0901, "网络存在问题");
			reqStateMap.put(RESULT_88888888, "数据解析异常");
			reqStateMap.put(RESULT_99999999, "返回code错误");
		}
	}
}

package com.git.easylib.EventMessage;



/**
 * json基础Bean
 * @author Administrator
 *
 */
public class BaseBean {

	private int resultCode;
	private String resultMsg;
	
	public int getResultCode() {
		return resultCode == 0 ? 10010 : resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}

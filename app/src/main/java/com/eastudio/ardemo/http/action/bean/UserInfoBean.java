package com.eastudio.ardemo.http.action.bean;

import com.eastudio.ardemo.http.model.UserInfo;
import com.git.easylib.EventMessage.BaseBean;

/**
 *
 * @author dyf
 * @date 2017/10/19/019
 */

public class UserInfoBean extends BaseBean {

    private UserInfo resultContent;

    public UserInfo getResultContent() {
        return resultContent;
    }

    public void setResultContent(UserInfo resultContent) {
        this.resultContent = resultContent;
    }



}

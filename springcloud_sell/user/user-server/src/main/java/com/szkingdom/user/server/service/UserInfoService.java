package com.szkingdom.user.server.service;

import com.szkingdom.user.server.dataobject.UserInfo;

/**
 * @Description
 * @Author zhaizhengwei
 */
public interface UserInfoService {

    UserInfo findByOpenid(String openid);
}

package com.szkingdom.user.server.service.impl;

import com.szkingdom.user.server.dataobject.UserInfo;
import com.szkingdom.user.server.repository.UserInfoRepository;
import com.szkingdom.user.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}

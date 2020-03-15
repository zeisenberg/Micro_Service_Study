package com.szkingdom.user.server.repository;

import com.szkingdom.user.server.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author zhaizhengwei
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}

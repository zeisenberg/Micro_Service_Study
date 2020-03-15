package com.szkingdom.user.server.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}

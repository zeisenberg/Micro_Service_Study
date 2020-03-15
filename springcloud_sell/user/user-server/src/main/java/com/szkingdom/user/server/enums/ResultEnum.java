package com.szkingdom.user.server.enums;

import lombok.Getter;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Getter
public enum ResultEnum {
    LOGIN_FAIL(1, "登陆失败"),
    ROLE_ERROR(2, "角色权限有误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

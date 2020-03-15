package com.szkingdom.user.server.controller;

import com.szkingdom.user.server.VO.ResultVO;
import com.szkingdom.user.server.constant.CookieConstant;
import com.szkingdom.user.server.constant.RedisConstant;
import com.szkingdom.user.server.dataobject.UserInfo;
import com.szkingdom.user.server.enums.ResultEnum;
import com.szkingdom.user.server.enums.RoleEnum;
import com.szkingdom.user.server.service.UserInfoService;
import com.szkingdom.user.server.utils.CookieUtil;
import com.szkingdom.user.server.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zhaizhengwei
 */
@RestController
@RequestMapping("/login")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse httpServletResponse) {
        // 1.openid和数据库数据是否匹配
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if(ObjectUtils.isEmpty(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2.判断角色
        if(RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3.cookie里设置openid=abc
        CookieUtil.setCookie(httpServletResponse, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);

        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse httpServletResponse) {

        //判断是否已经登陆
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if(!ObjectUtils.isEmpty(cookie) &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        // 1.openid和数据库数据是否匹配
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if(ObjectUtils.isEmpty(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2.判断角色
        if(RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3.redis中设置token, key=token_%s(UUID生成的随机数)， value=xyz（openid的值）
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                        openid,
                        CookieConstant.EXPIRE,
                        TimeUnit.SECONDS);

        // 4.cookie里设置token，value=token（UUID生成的随机数）
        CookieUtil.setCookie(httpServletResponse, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        return ResultVOUtil.success();
    }
}

package com.szkingdom.order.server.utils;


import com.szkingdom.order.server.VO.ResultVO;

/**
 * @Description
 * @Author zhaizhengwei
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}

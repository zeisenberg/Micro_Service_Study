package com.szkingdom.product.server.utils;

import com.szkingdom.product.server.VO.ResultVO;

/**
 * @Description 商品 controller
 * @Author zhaizhengwei
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}

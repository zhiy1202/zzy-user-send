package com.wx.common;

import lombok.Data;

/**
 * @author zzy
 * @desc
 */
@Data
public class UserCarGoodsImg {
    private Integer carId;
    private Integer goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsImg;
    private String address;
    private String telephone;

}

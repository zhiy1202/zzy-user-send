package com.wx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzy
 * @since 2022-08-29
 */
@Getter
@Setter
@TableName("t_car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "car_id", type = IdType.AUTO)
    private Integer carId;

    private Integer userId;

    private Integer goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    private  String address;
    private String telephone;


}

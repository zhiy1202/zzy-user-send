package com.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("goods_imgs")
public class GoodsImgs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "img_id", type = IdType.AUTO)
    private Integer imgId;

    private Integer goodsId;

    private String goodsImg;


}

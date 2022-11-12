package com.wx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
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
@TableName("t_common")
public class Common implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "common_id", type = IdType.AUTO)
    private Integer commonId;

    private Integer userId;

    private String userName;

    private Integer goodsId;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}

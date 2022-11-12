package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.Common;
import com.wx.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-08-29
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonMapper commonMapper;
    //添加评论
    @PostMapping("/addComm")
    public ZZYResult<Boolean> addComm(Common common){
        int insert = commonMapper.insert(common);
        return ZZYResult.getInstance(200,"执行添加评论",insert == 1 ? true:false);
    }

    // 通过goodId 查询所有评论
    @GetMapping("/getAllCommByGoodsId/{goodsId}")
    public ZZYResult<List<Common>> getAllCommByGoodsId(@PathVariable("goodsId") Integer goodsId){
        QueryWrapper<Common> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",goodsId).orderByDesc("create_time");
        return ZZYResult.getInstance(200,"执行查找所有产品评论",commonMapper.selectList(wrapper));
    }

}

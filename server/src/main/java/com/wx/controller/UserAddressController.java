package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.UserAddress;
import com.wx.mapper.UserAddressMapper;
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
@RequestMapping("/address")
public class UserAddressController {
    @Autowired
    private UserAddressMapper userAddressMapper;
    //添加地址
    @PostMapping("/addAddress")
    public ZZYResult<Boolean> addAddress(UserAddress userAddress){
        int insert = userAddressMapper.insert(userAddress);
        return ZZYResult.getInstance(200,"执行添加用户地址",insert == 1? true:false);
    }

    //通过userId 获取地址
    @GetMapping("/getAddressByUserId/{userId}")
    public ZZYResult<List<UserAddress>> getAddressByUserId(@PathVariable("userId") Integer userId){
        QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return ZZYResult.getInstance(200,"执行获取用户地址",userAddressMapper.selectList(wrapper));
    }

    //通过addressId删除地址
    @DeleteMapping("/delAddressById/{addressId}")
    public ZZYResult<Boolean> delAddressById(@PathVariable("addressId") Integer addressId){
        int i = userAddressMapper.deleteById(addressId);
        return ZZYResult.getInstance(200,"执行删除地址操作",i == 1? true:false);
    }

    //修改地址
}

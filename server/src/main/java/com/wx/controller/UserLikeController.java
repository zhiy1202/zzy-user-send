package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.UserHistory;
import com.wx.entity.UserLike;
import com.wx.mapper.UserLikeMapper;
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
@RequestMapping("/userLike")
public class UserLikeController {
    @Autowired
    private UserLikeMapper userLikeMapper;
    //添加用户喜欢
    @PostMapping("/addUserLike")
    public ZZYResult<Boolean> addUserLike(UserLike userLike){
        int insert = userLikeMapper.insert(userLike);
        return ZZYResult.getInstance(200,"执行添加用户喜欢记录",insert == 1 ? true:false);

    }
    //删除用户喜欢
    @DeleteMapping("/delUserLike/{likeId}")
    public ZZYResult<Boolean> delUserLike(@PathVariable("likeId") Integer likeId){
        int insert = userLikeMapper.deleteById(likeId);
        return ZZYResult.getInstance(200,"执行删除用户喜欢记录",insert == 1 ? true:false);
    }
    //通过userId 获取所有喜欢列表
    @GetMapping("/getAllLike/{userId}")
    public ZZYResult<List<UserLike>> getAllLike(@PathVariable("userId") Integer userId){
        QueryWrapper<UserLike> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).orderByDesc("create_time");
        return ZZYResult.getInstance(200,"执行获取用户所有喜欢记录",userLikeMapper.selectList(wrapper));
    }
}

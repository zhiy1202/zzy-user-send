package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.UserHistory;
import com.wx.mapper.UserHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
@RequestMapping("/userHistory")
public class UserHistoryController {
    @Autowired
    private UserHistoryMapper userHistoryMapper;
    public UserHistory hisIsExits(UserHistory userHistory) {
        QueryWrapper<UserHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userHistory.getUserId()).eq("goods_id",userHistory.getGoodsId());
        UserHistory userHistory1 = userHistoryMapper.selectOne(wrapper);
        return userHistory1;
    }
    //添加用户历史
    @PostMapping("/addUserHistory")
    public ZZYResult<Boolean> addUserHistory(UserHistory userHistory, HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        userHistory.setUserId(userId);
        UserHistory b = hisIsExits(userHistory);
        if (b==null){
            int insert = userHistoryMapper.insert(userHistory);
            return ZZYResult.getInstance(200,"执行添加用户历史记录",insert == 1 ? true:false);
        }else {
            userHistory.setCreateTime(LocalDateTime.now());
            userHistory.setHistoryId(b.getHistoryId());
            int i = userHistoryMapper.updateById(userHistory);
            return ZZYResult.getInstance(200,"执行更改用户历史记录时间",i == 1 ? true:false);
        }


    }
    //删除用户历史
    @DeleteMapping("/delUserHistory/{historyId}")
    public ZZYResult<Boolean> delUserHistory(@PathVariable("historyId") Integer historyId){
        int insert = userHistoryMapper.deleteById(historyId);
        return ZZYResult.getInstance(200,"执行删除用户历史记录",insert == 1 ? true:false);
    }
    //通过userId 获取所有历史
    @GetMapping("/getAllHistory")
    public ZZYResult<List<UserHistory>> getAllHistory(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        QueryWrapper<UserHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).orderByDesc("create_time");
        return ZZYResult.getInstance(200,"执行获取用户所有历史记录",userHistoryMapper.selectList(wrapper));
    }
}

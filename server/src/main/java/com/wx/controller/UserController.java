package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.User;
import com.wx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    public Boolean userIsExits(User user){
        User selectOne = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (selectOne == null){
            return true;
        }{
            return false;
        }
    }
    @GetMapping("/isExits")
    public Boolean userIsCheck(String username){
        User selectOne = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (selectOne == null){
            return true;
        }{
            return false;
        }
    }

    //admin登录
    @PostMapping("/admin/login")
    public ZZYResult<Boolean> adminLogin(String username,String password){
        if ("admin".equals(username) && "admin".equals(password)){
            return ZZYResult.getInstance(200,"欢迎管理员",true);
        }else {
            return ZZYResult.getInstance(200,"无权限",false);

        }
    }
    //添加用户
    @PostMapping("/register")
    public ZZYResult<Boolean> addUser(User user){
        Boolean aBoolean = userIsExits(user);
        if (aBoolean){
            userMapper.insert(user);
            return ZZYResult.getInstance(200,"用户注册成功",true);
        }else {
            return ZZYResult.getInstance(500,"用户存在",false);
        }
    }
    //通过id删除用户
    @DeleteMapping("/delUserById/{userId}")
    public ZZYResult<Boolean> delUserById(@PathVariable("userId") Integer userId){
        int i = userMapper.deleteById(userId);
        return ZZYResult.getInstance(200,"执行删除用户",i == 1 ? true : false );
    }

    //通过用户id修改用户
    @PutMapping("/updateUser")
    public ZZYResult<Boolean> updateUser(User user){
//        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        int i = userMapper.updateById(user);
        return ZZYResult.getInstance(200,"修改用户信息",i == 1 ? true : false);
    }

    //通过id查询用户
    @GetMapping("/getUserById/{userId}")
    public ZZYResult<User> getUserById(@PathVariable("userId") Integer userId){
        User user = userMapper.selectById(userId);
        return ZZYResult.getInstance(200,"执行查询用户",user);
    }
    //查看用户信息
    @GetMapping("/getUserInfo")
    public ZZYResult<User> getUserInfo(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        User user = userMapper.selectById(userId);
        return ZZYResult.getInstance(200,"执行查询用户",user);
    }

    //登录
    @PostMapping("/login")
    public ZZYResult<Boolean> login(String username, String password, HttpServletRequest request){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(wrapper);
        request.getSession(true).setAttribute("userId",user.getUserId());
        return ZZYResult.getInstance(200,"执行登录",user == null ? false:true);
    }

    //获取所有用户
    @GetMapping("/allUser")
    public ZZYResult<List<User>> allUser(){
        return ZZYResult.getInstance(200,"执行查询所有用户",userMapper.selectList(null));
    }
}

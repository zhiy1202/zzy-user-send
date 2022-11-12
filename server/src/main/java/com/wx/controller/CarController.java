package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.UserCarGoodsImg;
import com.wx.common.ZZYResult;
import com.wx.entity.Bill;
import com.wx.entity.Car;
import com.wx.mapper.BillMapper;
import com.wx.mapper.CarMapper;
import com.wx.mapper.GoodsMapper;
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
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private BillMapper billMapper;

    @Autowired
    private GoodsMapper goodsMapper;
    //添加购物车
    @PostMapping("/addCar")
    public ZZYResult<Boolean> addCar(Car car, HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        car.setUserId(userId);
        int insert = carMapper.insert(car);
        return ZZYResult.getInstance(200,"执行添加购物车",insert == 1 ? true:false);
    }

    //通过carId删除购物车
    @DeleteMapping("/delCarById/{carId}")
    public ZZYResult<Boolean> delCarById(@PathVariable("carId") Integer carId){
        int i = carMapper.deleteById(carId);
        return ZZYResult.getInstance(200,"执行删除购物车",i == 1? true:false);
    }

    //通过用户id查看所有购物车
    @GetMapping("/getAllCarByUserId")
    public ZZYResult<List<UserCarGoodsImg>> getALlCarByUserId(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        return ZZYResult.getInstance(200,"执行查看用户所有购物车",carMapper.getAllUserCar(userId));
    }

    //(结算 添加账单)
    @DeleteMapping("/statement/{carId}")
    public ZZYResult<Boolean> statement(@PathVariable("carId") Integer carId){
        Car car = carMapper.selectById(carId);
        Bill bill = new Bill();
        bill.setUserId(car.getUserId());
        bill.setGoodsId(car.getGoodsId());
        bill.setGoodsName(car.getGoodsName());
        bill.setGoodsPrice(car.getGoodsPrice());
        bill.setAddress(car.getAddress());
        bill.setTelephone(car.getTelephone());
        int insert = billMapper.insert(bill);
        if (insert == 1){
            //逻辑删除goods
            goodsMapper.deleteById(car.getGoodsId());
            int i = carMapper.deleteById(carId);
            if (i == 1){
                return ZZYResult.getInstance(200,"执行结账",true);
            }
        }
        return ZZYResult.getInstance(500,"执行结账失败",false);
    }



}

package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.Bill;
import com.wx.entity.Car;
import com.wx.mapper.BillMapper;
import com.wx.mapper.CarMapper;
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
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private CarMapper carMapper;
    //添加账单
    @PostMapping("/addBill")
    public ZZYResult<Boolean> addBill(Bill bill, HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        bill.setUserId(userId);
        int insert = billMapper.insert(bill);
        if (insert == 1){
            QueryWrapper<Car> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).eq("goods_id",bill.getGoodsId());
            insert = carMapper.delete(wrapper);
        }
        return ZZYResult.getInstance(200,"执行添加账单",insert != -1 ? true:false);
    }

    //通过billId删除账单
    @DeleteMapping("/delBillById/{billId}")
    public ZZYResult<Boolean> delBillById(@PathVariable("billId") Integer billId){
        int id = billMapper.deleteById(billId);
        return ZZYResult.getInstance(200,"执行删除账单",id == 1?true:false);
    }

    //查看所有账单
    @GetMapping("/getAllBill")
    public ZZYResult<List<Bill>> getAllBill(){
        return ZZYResult.getInstance(200,"所有账单",billMapper.selectList(null));
    }

    //通过用户id查看所有账单
    @GetMapping("/getAllBillByUserId")
    public ZZYResult<List<Bill>> getAllBillByUserId(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return ZZYResult.getInstance(200,"执行所有用户账单",billMapper.selectList(wrapper));
    }
}

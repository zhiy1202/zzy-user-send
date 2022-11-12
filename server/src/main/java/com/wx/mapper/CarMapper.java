package com.wx.mapper;

import com.wx.common.UserCarGoodsImg;
import com.wx.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-08-29
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {
    @Select("select c.car_id,c.goods_id,c.goods_name,c.goods_price, c.address,c.telephone,(select gi.goods_img from goods_imgs gi where gi.goods_id = c.goods_id limit 0,1) goods_img  from t_car c where c.user_id = #{userId}")
    List<UserCarGoodsImg> getAllUserCar(Integer userId);
}

package com.wx.mapper;

import com.wx.common.ZZYParam;
import com.wx.entity.Goods;
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
public interface GoodsMapper extends BaseMapper<Goods> {
    //查询所有产品一张图片和他的详细信息
    @Select("select g.goods_id, g.goods_name,g.goods_price,g.goods_intro, (select goods_img from goods_imgs where goods_id = g.goods_id limit 0,1 ) goods_img from t_goods  g where g.is_del = 0")
    List<ZZYParam> getAllGoods();

    @Select("select g.goods_id, g.goods_name,g.goods_price,g.goods_intro, (select goods_img from goods_imgs where goods_id = g.goods_id limit 0,1 ) goods_img from t_goods g where g.goods_actice = 1 and g.is_del = 0")
    List<ZZYParam> getAllGoodsByActive();

    @Select("select g.goods_id, g.goods_name,g.goods_price,g.goods_intro, (select goods_img from goods_imgs where goods_id = g.goods_id limit 0,1 ) goods_img from t_goods g where g.goods_price >  2000 and g.goods_price < 5000 and g.is_del = 0")
    List<ZZYParam> getAllGoodsByGood();

    @Select("select g.goods_id, g.goods_name,g.goods_price,g.goods_intro, (select goods_img from goods_imgs where goods_id = g.goods_id limit 0,1 ) goods_img from t_goods g where g.goods_name like '%${goodsName}%' and g.is_del = 0")
    List<ZZYParam> getAllGoodsByGoodsName(String goodsName);

    @Select("select * from t_goods where user_id = #{userId} and is_del = 1")
    List<Goods> getAllSellGoods(Integer userId);

}

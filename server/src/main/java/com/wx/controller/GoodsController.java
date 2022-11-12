package com.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYParam;
import com.wx.common.ZZYResult;
import com.wx.entity.Goods;
import com.wx.entity.GoodsImgs;
import com.wx.mapper.GoodsImgsMapper;
import com.wx.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-08-29
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImgsMapper goodsImgsMapper;
    //添加产品
    @PostMapping("/addGoods")
    public ZZYResult<Integer> addGoods(Goods goods,HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        goods.setUserId(userId);
        goods.setIsDel(false);
        int insert = goodsMapper.insert(goods);
        Integer goodsId = goods.getGoodsId();
        System.out.println(goodsId);
        return ZZYResult.getInstance(200,"执行添加产品",goodsId);
    }
    //修改产品
    @PutMapping("/updateGoodsInfo")
    public ZZYResult<Boolean> updateGoodsInfo(Goods goods){
        int i = goodsMapper.updateById(goods);
        return ZZYResult.getInstance(200,"修改成功",i != -1 ? true : false);
    }
    //通过goodsId删除产品
    @DeleteMapping("/delGoodsById/{goodsId}")
    public ZZYResult<Boolean> delGoodsById(@PathVariable("goodsId") Integer goodsId){
        int i = goodsMapper.deleteById(goodsId);
        return ZZYResult.getInstance(200,"执行删除产品",i ==1 ? true:false);
    }

    //通过用户id 查看用户所有产品
    @GetMapping("/getGoodsByUserId")
    public ZZYResult<List<Goods>> getGoodsByUserId(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return ZZYResult.getInstance(200,"执行查看用户所有产品",goodsMapper.selectList(wrapper));
    }
    //用户已卖出产品
    @GetMapping("/sell")
    public ZZYResult<List<Goods>> sell(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        return ZZYResult.getInstance(200,"执行查看用户所有产品",goodsMapper.getAllSellGoods(userId));
    }

    //查看所有产品
    @GetMapping("/getAllGoodsForAdmin")
    public ZZYResult<List<Goods>> getAllGoods(){
        return ZZYResult.getInstance(200,"执行查看说有产品",goodsMapper.selectList(null));
    }

    //分类查看 goods_type
    @GetMapping("/getAllGoodsByType/{type}")
    public ZZYResult<List<Goods>> getAllGoodsByType(@PathVariable("type") String type){
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_type",type).orderByDesc("create_time");
        return ZZYResult.getInstance(200,"执行查询分类查看",goodsMapper.selectList(wrapper));
    }

    //查看所有goods
    @GetMapping("/getAllGoods")
    public ZZYResult<List<ZZYParam>> getAllGoodsForItem(){
        return ZZYResult.getInstance(200,"执行所有goods元素",goodsMapper.getAllGoods());
    }
    //查看所有活动商品
    @GetMapping("/getALlGoodsByActive")
    public ZZYResult<List<ZZYParam>> getALlGoodsByActive(){
        return ZZYResult.getInstance(200,"查找所有活动商品",goodsMapper.getAllGoodsByActive());
    }
    //查看所有推荐商品
    @GetMapping("/getALlGoodsByGood")
    public ZZYResult<List<ZZYParam>> getALlGoodsByGood(){
        return ZZYResult.getInstance(200,"查找所有活动商品",goodsMapper.getAllGoodsByGood());
    }
    //通过goodsId获取goods
    @GetMapping("/getGoodsById/{goodsId}")
    public ZZYResult<Goods> getGoodsById(@PathVariable("goodsId") Integer goodsId){
        return ZZYResult.getInstance(200,"获取goods",goodsMapper.selectById(goodsId));
    }
    //通过goodsId 获取所有图片
    @GetMapping("/getGoodsImgByGoodsId/{goodsId}")
    public ZZYResult<List<GoodsImgs>> getGoodsImgByGoodsId(@PathVariable("goodsId") Integer goodsId){
        QueryWrapper<GoodsImgs> wrapper = new QueryWrapper();
        wrapper.eq("goods_id",goodsId);
        return ZZYResult.getInstance(200,"获取goods",goodsImgsMapper.selectList(wrapper));
    }
    //通过goodsName 获取所有goods
    @GetMapping("/getGoodsImgByGoodsName/{goodsName}")
    public ZZYResult<List<ZZYParam>> getGoodsImgByGoodsName(@PathVariable("goodsName") String goodsName){
        return ZZYResult.getInstance(200,"获取goods",goodsMapper.getAllGoodsByGoodsName(goodsName));
    }


}

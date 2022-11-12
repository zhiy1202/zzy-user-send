package com.wx.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.common.ZZYResult;
import com.wx.entity.GoodsImgs;
import com.wx.mapper.GoodsImgsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
@RequestMapping("/goodsImgs")
public class GoodsImgsController {
    @Value("${zzy.remotePath}")
    private String remotePath;
    @Value("${zzy.port}")
    private int port;
    @Autowired
    private GoodsImgsMapper goodsImgsMapper;
    //上传文件
    @PostMapping("/upload")
    public ZZYResult<Boolean> upload(List<MultipartFile> files, Integer goodsId){
        for (MultipartFile file : files) {
            String path = getRemotePath(file);
            System.out.println("地址为:"+path);
            GoodsImgs img = new GoodsImgs();
            img.setGoodsId(goodsId);
            img.setGoodsImg(path);
            goodsImgsMapper.insert(img);
        }
        return ZZYResult.getInstance(200,"文件执行上传",true);
    }

    //查看一张图片
    @GetMapping("/getGoodsImgByOne")
    public ZZYResult<String> getGoodsImgByOne(Integer goodsId){
        QueryWrapper<GoodsImgs> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",goodsId);
        GoodsImgs imgs = goodsImgsMapper.selectList(wrapper).get(0);
        return ZZYResult.getInstance(200,"执行查询产品介绍图",imgs.getGoodsImg());
    }

    //查看goods所有图片
    @GetMapping("/getGoodsImgs")
    public ZZYResult<List<GoodsImgs>> getGoodsImgs(Integer goodsId){
        QueryWrapper<GoodsImgs> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",goodsId);
        List<GoodsImgs> imgs = goodsImgsMapper.selectList(wrapper);
        return ZZYResult.getInstance(200,"查询所有goods图片",imgs);
    }

    //文件写入 返回网络地址
    public String getRemotePath(MultipartFile file){
        String path = "http://"+remotePath +":"+port+"/static/";
        System.out.println(path);
        //后缀
        String originalFilename = file.getOriginalFilename();
        String [] arr = originalFilename.split("\\.");
        String fix = arr[arr.length-1];

        //文件名
        String name = IdUtil.fastSimpleUUID();
        String fileName = name + "." +fix;

        //文件夹生成
        ApplicationHome home = new ApplicationHome();
        File dir = home.getDir();
        String resources = dir.getAbsolutePath()+"\\target\\static\\";
//        System.out.println("文件储存地址为:"+resources);

        File file1 = new File(resources);
        if (!file1.exists()){
            file1.mkdirs();
        }
        File file2 = new File(resources + fileName);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            System.out.println("文件写入失败");
            throw new RuntimeException(e);
        }

        return path + fileName;
    }
}

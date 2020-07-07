package com.together.modules.goods.controller;


import com.together.annotation.Pmap;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.service.IGoodsService;
import com.together.util.FileUtil;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    IGoodsService iGoodsService;

    @Autowired
    ListOperations<String,GoodsEntity> listOperations;

    @GetMapping("/getGoodsPage")
    public R getGoodsPage(@Pmap P p) throws Exception {
        System.out.println(p);
        p.batchToInt("page","limit");
        return iGoodsService.queryGoodsByShopId(p);
    }

    @RequestMapping("/delGoods")
    public R delGoods(@Pmap P p){
        if (iGoodsService.removeById(p.getInt("goods_id"))) {
            return R.success("删除成功");
        }
        else{
            return R.error("删除失败");
        }
    }

    @RequestMapping("/queryGoodsById")
    public R queryGoodsById(@Pmap P p){
        GoodsEntity goodsId = iGoodsService.getById(p.getInt("goodsId"));
        return R.success("操作成功",goodsId);
    }

    //图片上传
    @RequestMapping("/addGoods")
    public R addGoods(@Pmap P p) throws Exception {
        MapUtil.mapKeySetUpper2Line(p);
        return iGoodsService.addGoods(p);
    }

    //图片上传
    @RequestMapping("/uploadGoods")
    public R uploadGoodsList(@RequestParam MultipartFile file) throws IOException {
        FileUtil fileUtil = new FileUtil();
        String basePath = this.getClass().getResource("/static").getPath();
        String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = basePath + "/upload/shop/" + new Date().getTime() + prefix;
        File desFile = new File(filePath);
        File outfile = fileUtil.write(desFile,file.getInputStream(),file.getSize(),1024*40);
        return R.success().set("fileName",outfile.getName()).set("filePath",filePath);
    }

    /**
     * shadow
     * @param p
     * @return
     * @throws Exception
     */
    @GetMapping("/queryAllGoods")
    public R queryAllGoods(@Pmap P p) throws Exception {
        p.batchToInt("page","limit");
        return  iGoodsService.queryGoodsByShopId(p);
    }

    /**
     * 查询热门商品推荐接口
     * @param p
     * @return
     */
    @GetMapping("/queryhotGoods")
    public R queryhotGoods(@Pmap P p) {
        List<GoodsEntity> goodshot = listOperations.range("goodshot", 0, -1);
        return R.success().data(goodshot);
    }

    /**
     * 查询拼团商品推荐
     * @param p
     * @return
     */
    @GetMapping("/queryCommonGoods")
    public R queryCommonGoods(@Pmap P p){
        List<GoodsEntity> goodshot = listOperations.range("goodsgroup", 0, -1);
        return R.success().data(goodshot);
    }


}

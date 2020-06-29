package com.together.modules.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.service.IGoodsService;
import com.together.util.Map2JavaBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/getGoodsPage")
    public R getGoodsPage(@RequestParam Map<String,Object> map) throws Exception {
        GoodsEntity goodsEntity = new GoodsEntity();
        int pageNum = 1;
        int limit = 10;
        if (map.get("page")!=null) {
            pageNum = (int) map.get("page");
        }
        if (map.get("limit")!=null) {
            limit = (int) map.get("limit");
        }
        if(map.get("goods")!=null){
            goodsEntity = (GoodsEntity) map.get("goods");
        }
        Page page = new Page(pageNum,limit);
        Map2JavaBeanUtil.transMap2Bean(map,goodsEntity);
        return R.ok(iGoodsService.page(page, Wrappers.query(goodsEntity)));
    }

    @GetMapping("/getGoodsList")
    public R getGoodsList(){
        return R.ok(iGoodsService.list());
    }

    /**
     * 根据商品id查询信息
     * @param goodsId
     * @return
     */
    @GetMapping("/{goodsId}")
    public R getProductId(@PathVariable("goodsId") String goodsId){
        return R.ok(iGoodsService.getById(goodsId));
    }

    /**
     * 新增
     * @param goodsEntity
     * @return
     */
    @PostMapping
    public R save(@RequestBody GoodsEntity goodsEntity){
        return R.ok(iGoodsService.save(goodsEntity));
    }

    /**
     * 修改
     * @param goodsEntity
     * @return
     */
    @PutMapping
    public R updateById(@RequestBody GoodsEntity goodsEntity){
        return R.ok(iGoodsService.updateById(goodsEntity));
    }

    /**
     * 删除
     * @return
     */
    @DeleteMapping
    public R removeById(@PathVariable String goodsId){
        return R.ok(iGoodsService.removeById(goodsId));
    }
}

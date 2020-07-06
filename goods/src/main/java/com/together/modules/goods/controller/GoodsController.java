package com.together.modules.goods.controller;


import com.together.annotation.Pmap;
import com.together.enun.TipMsgEnum;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.service.IGoodsService;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

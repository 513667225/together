package com.together.modules.shop.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.config.KdniaoTrackQueryAPI;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.service.IShopService;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

//    @Autowired
//    ListOperations<String, Object> listOperations;

    @Autowired
    ValueOperations<String, Object> valueOperations;

    /**
     * 根据user_id查询店铺信息
     * @param p
     * @return
     */
    @GetMapping("/getShopPage")
    public R getShopPage(@Pmap P p) throws Exception{
        IPage shopPage = shopService.getShopPage(p);
        return R.success("xxx",shopPage.getRecords()).set("count",shopPage.getTotal());
    }

    /**
     * 查询所有店铺 和  根据店铺的类型查询
     * @param p
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllShop")
    public R getAllShop(@Pmap P p)throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page<ShopEntity> objectPage = new Page<>(page,limit);
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        if(""==p.getString("shop_name")){
            p.remove("shop_name");
        }
        if(""==p.getString("shop_category")){
            p.remove("shop_category");
        }
        Page<ShopEntity> pageObject = shopService.page(objectPage,new QueryWrapper<ShopEntity>().allEq(p));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }

    @GetMapping("/getShipByShipSn")
    public R getShipByShipSn(@Pmap P p) throws Exception {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        return R.success("操作成功", api.getOrderTracesByJson(p.getString("ship_channel"), p.getString("ship_sn")));
    }

    @PostMapping("/newOrder")
    public R newOrder(@Pmap P p) throws Exception {
        ShopEntity shopEntity = new ShopEntity();
        p.thisToEntity(shopEntity);
        return R.success("success",shopService.save(shopEntity));
    }


    /**
     * 精选店铺推荐
     * @param p
     * @return
     * @throws Exception
     */
    @GetMapping("/queryLimitHotShop")
    public R queryLimitHotShop(@Pmap P p) throws Exception {
        Object popularShops = valueOperations.get("popularShops");
        if(popularShops instanceof List){
            List<ShopEntity> shopEntityList= (List<ShopEntity>) popularShops;
            return R.success().data(shopEntityList);
        }
//        List<Object> goodshot = listOperations.range("popularShops", 0, -1);
        return R.success().data(null);
    }



}

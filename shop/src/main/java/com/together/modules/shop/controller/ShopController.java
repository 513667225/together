package com.together.modules.shop.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.config.KdniaoTrackQueryAPI;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.service.IShopService;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.modules.shopUser.service.IShopUserService;
import com.together.util.FileUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
        p.removeByKey(p);
        Page<ShopEntity> pageObject = shopService.page(objectPage,new QueryWrapper<ShopEntity>().allEq(p));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }

    @GetMapping("/getShipByShipSn")
    public R getShipByShipSn(@Pmap P p) throws Exception {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        return R.success("操作成功", api.getOrderTracesByJson(p.getString("ship_channel"), p.getString("ship_sn")));
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


    /**
     * 查询所有现在发行了优惠卷的店铺
     */
//    select * from shop where shop_id in (select distinct shop_id from group_roll where inventory>0)  and shop_type=1
    @GetMapping("/queryLimitRollShop")
    public R queryLimitRollShop(@Pmap P p) throws Exception {

        List<ShopEntity> shopEntities=shopService.queryLimitRollShop(p);
        return R.success().data(shopEntities);
    }

    @RequestMapping("/queryRegion")
    public R queryRegion(@Pmap P p){
        p.batchToInt("pid");
        return shopService.queryRegion(p);
    }

    @GetMapping("/queryShopByShopName")
    public R queryShopByShopName(@Pmap P p){
        ShopEntity shopEntity = shopService.getOne(new QueryWrapper<ShopEntity>().eq("shop_name", p.getString("shop_name")));
        if (shopEntity==null) {
            return R.success("");
        }
        else {
            return R.success("店铺已存在！请重新输入！");
        }
    }

    //增加店铺，商家，查询市代方法
    @RequestMapping("/addShop")
    public R addShop(@Pmap P p) throws Exception {
        int addShops = shopService.addShop(p);
        if (0!=addShops){
          return R.success("增加成功！");
        }
        return R.success("增加失败！");

    }




    //图片上传 店铺
    @RequestMapping("/uploadShopPic")
    public R uploadShopPic(@RequestParam MultipartFile file) throws IOException {
        FileUtil fileUtil = new FileUtil();
        String basePath = this.getClass().getResource("/static").getPath();
        String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = basePath + "/upload/store/" + new Date().getTime() + prefix;
        File desFile = new File(filePath);
        File outfile = fileUtil.write(desFile,file.getInputStream(),file.getSize(),1024*40);
        return R.success().set("fileName",outfile.getName()).set("filePath",outfile.getName());
    }


}

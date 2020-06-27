package com.together.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.product.entity.ProductEntity;
import com.together.modules.product.service.IProductService;
import com.together.modules.util.Map2JavaBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * 分页查询
     * @param page
     * @param productEntity
     * @return
     */
    @GetMapping("/getProductPage")
    public R getProductPage(Page page, ProductEntity productEntity){
//        Page page1 = new Page();
        return R.ok(productService.page(page, Wrappers.query(productEntity)));
    }


    @RequestMapping("/getProductPage1")
    public  R getProductPage1(@RequestBody Map<String,Object> map) throws Exception {

        Page page = new Page((int)map.get("page"),(int)map.get("limit"));
//        Wrappers.
        ProductEntity productEntity = new ProductEntity();
        Map2JavaBeanUtil.transMap2Bean(map,productEntity);
        return R.ok(productService.page(page, Wrappers.query(productEntity)));
    }

    /**
     * 根据商品id查询信息
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public R getProductId(@PathVariable("productId") String productId){
        return R.ok(productService.getById(productId));
    }

    /**
     * 新增
     * @param productEntity
     * @return
     */
    @PostMapping
    public R save(@RequestBody ProductEntity productEntity){
        return R.ok(productService.save(productEntity));
    }

    /**
     * 修改
     * @param productEntity
     * @return
     */
    @PutMapping
    public R updateById(@RequestBody ProductEntity productEntity){
        return R.ok(productService.updateById(productEntity));
    }

    /**
     * 删除
     * @return
     */
    @DeleteMapping
    public R removeById(@PathVariable String productId){
        return R.ok(productService.removeById(productId));
    }
}

package com.together.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.product.entity.ProductEntity;
import com.together.modules.product.service.IProductService;
import com.together.util.Map2JavaBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
// url: "/product/getProductPage"
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/getProductPage")
    public R getProductPage(Map<String,Object> map) throws Exception {
        int pageNum = 1;
        int limit = 10;
        if (map.get("page")!=null) {
            pageNum = (int) map.get("page");
        }
        if (map.get("limit")!=null) {
            limit = (int) map.get("limit");
        }
        Page page = new Page(pageNum,limit);
        ProductEntity productEntity = new ProductEntity();
        Map2JavaBeanUtil.transMap2Bean(map,productEntity);
        return R.ok(productService.page(page, Wrappers.query(productEntity)));
    }

    @GetMapping("/getProductList")
    public R getProductList(){
        return R.ok(productService.list());
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

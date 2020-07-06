package com.together.modules.category.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.together.annotation.Pmap;
import com.together.modules.category.entity.CategoryEntity;
import com.together.modules.category.service.ICategoryService;
import com.together.modules.category.service.impl.CategoryServiceImpl;
import com.together.util.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询所有商品品目
     */
    @GetMapping("/getCategoryList")
    public R getCategoryList(){
        return R.ok(categoryService.list());
    }

    /**
     * 查询所有商品父类目
     */
    @GetMapping("/getCategoryPidList")
    public R getCategoryPidList(){
        List<CategoryEntity> categoryEntities = categoryService.list(new QueryWrapper<CategoryEntity>().eq("pid", "0"));
        return R.ok(categoryEntities);
    }

    /**
     * 根据父类目查询子类目
     */
    @GetMapping("/getCategoryListByPid")
    public R getCategoryListByPid(@Pmap P p){
        List<CategoryEntity> categoryEntities = categoryService.list(new QueryWrapper<CategoryEntity>().eq("pid", p.getInt("categoryId")));
        return R.ok(categoryEntities);
    }


}

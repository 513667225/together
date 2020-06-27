package com.together.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.product.entity.ProductEntity;
import com.together.modules.product.mapper.ProductMapper;
import com.together.modules.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements IProductService {

    /*@Override
    public List getProductPage(Page page, ProductEntity productEntity) {
        QueryWrapper<ProductEntity> productEntityWrapper = new QueryWrapper<>();
        productEntityWrapper.
        List<ProductEntity> productEntities = baseMapper.selectList(productEntityWrapper);
        return null;
    }*/
}

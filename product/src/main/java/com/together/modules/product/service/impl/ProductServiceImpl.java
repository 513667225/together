package com.together.modules.product.service.impl;

import com.together.modules.product.entity.ProductEntity;
import com.together.modules.product.mapper.ProductMapper;
import com.together.modules.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

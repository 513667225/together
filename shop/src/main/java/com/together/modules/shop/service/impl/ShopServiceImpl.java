package com.together.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.mapper.ShopMapper;
import com.together.modules.shop.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, ShopEntity> implements IShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public IPage getShopPage(P p) {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page page1  = new Page(page, limit);
        Integer userId = p.getInt("userId");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return baseMapper.selectPage(page1,queryWrapper);
    }

    @Override
    public int saveShopById(P p){
        ShopEntity shopEntity = (ShopEntity) p.get("shop");
        return baseMapper.insert(shopEntity);
    }

    @Override
    public int updShopById(P p){
        ShopEntity shopEntity = (ShopEntity) p.get("shop");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",shopEntity.getShopId());
        return baseMapper.update(shopEntity,queryWrapper);
    }
}

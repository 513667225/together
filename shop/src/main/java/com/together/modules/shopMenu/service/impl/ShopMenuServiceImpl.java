package com.together.modules.shopMenu.service.impl;

import com.together.modules.shopMenu.entity.MenuTree;
import com.together.modules.shopMenu.entity.ShopMenuEntity;
import com.together.modules.shopMenu.mapper.ShopMenuMapper;
import com.together.modules.shopMenu.service.IShopMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.shopMenu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-27
 */
@Service
public class ShopMenuServiceImpl extends ServiceImpl<ShopMenuMapper, ShopMenuEntity> implements IShopMenuService {

    @Autowired
    ShopMenuMapper menuMapper;

//    @Autowired
//    OrderServiceClient orderServiceClient;

    @Override
    public R getMenu() {
        return R.success("success",getRootMenu());
    }

    @Override
    public R getOrder(Map<String, Object> map) {
        return null;
    }

//    @Override
//    public R getOrder(Map<String,Object> map) {
//        return orderServiceClient.getOrder(map.get("page"),map.get("limit"));
//    }

    public List<MenuTree> getRootMenu(){
        Map<String,Object> map = new HashMap<>();
        map.put("pid","0");
        List<ShopMenuEntity> menus = menuMapper.selectByMap(map);
        List<MenuTree> list = new ArrayList<>();
        for (ShopMenuEntity menu : menus) {
            MenuTree menuTree1 = new MenuTree();
            menuTree1.setId(menu.getId()+"");
            menuTree1.setHref(menu.getUrl());
            menuTree1.setName(menu.getName());
            list.add(menuTree1);
            getChildMenu(menuTree1);
        }
        return list;
    }

    public  void getChildMenu(MenuTree menuTree){
        String id = menuTree.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("pid",id);
        List<ShopMenuEntity> menus = menuMapper.selectByMap(map);
        for (ShopMenuEntity menu : menus) {
            menuTree.initChild();
            MenuTree menuTree1 = new MenuTree();
            menuTree1.setId(menu.getId()+"");
            menuTree1.setHref(menu.getUrl());
            menuTree1.setName(menu.getName());
            menuTree.getChildren().add(menuTree1);
            getChildMenu(menuTree1);
        }
    }
}

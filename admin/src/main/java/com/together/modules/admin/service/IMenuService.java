package com.together.modules.admin.service;

import com.together.modules.admin.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.modules.admin.util.R;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
public interface IMenuService extends IService<Menu> {

    public R getMenu();

    public R getOrder(Map<String,Object> map);

}

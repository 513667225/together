package com.together.modules.region.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.modules.region.entity.RegionEntity;
import com.together.modules.region.mapper.RegionMapper;
import com.together.modules.region.service.IRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行政区域表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, RegionEntity> implements IRegionService {

    @Override
    public RegionEntity getRegionByCode(String code) throws Exception {
        code = code.substring(0, 4);
        code = code+"00";
        int i = Integer.parseInt(code);
        return baseMapper.selectOne(new QueryWrapper<RegionEntity>().eq("code",i));
    }
}

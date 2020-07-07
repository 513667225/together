package com.together.modules.spell.service;


import com.together.entity.Spell;
import com.together.enun.GoodsLevel;
import com.together.enun.TogetherNumber;
import com.together.util.R;

public interface SpellService {


    R startTogether(Integer userId, Integer goodsId, TogetherNumber togetherNumber, GoodsLevel goodsLevel);


    R startTogetherMq(Spell spell);


}

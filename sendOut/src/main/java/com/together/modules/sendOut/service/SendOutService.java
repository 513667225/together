package com.together.modules.sendOut.service;

import com.together.entity.Spell;
import com.together.util.P;
import com.together.util.R;

import java.util.List;

/**
 * @author Agu
 */
public interface SendOutService {


     R createOrder(List<Spell> list);


}

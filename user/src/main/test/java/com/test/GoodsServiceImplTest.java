package com.test;

import com.together.AppUser;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import com.together.modules.user.service.impl.UserServiceImpl;
import com.together.util.P;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppUser.class})// 指定启动类
public class GoodsServiceImplTest {
    @Autowired
    IUserService iGoodsService;

    @Test
    public void queryAllGoods() throws Exception {
//        List<UserEntity> userEntities = iGoodsService.selectUserByids(new String[]{
//                "10236","10241","10242"
//        });
//        System.out.println(userEntities);
        P p = new P();
        p.put("referrer_id",10243);
        p.put("x",0);
        p.put("n","普通新");
        iGoodsService.test(p);
    }

    @Test
    public void update() throws Exception {
//        List<UserEntity> userEntities = iGoodsService.selectUserByids(new String[]{
//                "10236","10241","10242"
//        });
//        System.out.println(userEntities);
        P p = new P();
        p.put("user_id",10243);
        p.put("balance",0.02);
        p.put("integral",3);
//        p.put("n","普通新");
        iGoodsService.updateMoney(p);
    }


}

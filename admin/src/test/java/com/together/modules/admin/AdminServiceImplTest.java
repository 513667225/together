package com.together.modules.admin;

import com.together.AppAdmin;
import com.together.modules.admin.service.IAdminService;
import com.together.util.P;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppAdmin.class})// 指定启动类
public class AdminServiceImplTest {
    @Autowired
    IAdminService iAdminService;
    @Test
    public void update() throws Exception {
        P p = new P();
        p.put("balance",2.56);
        p.put("admin_id",1);
        iAdminService.update(p);
    }
}

package com.baizhi;

import com.baizhi.dao.AdminMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class YingxApplicationTests {
    @Resource
    UserService userService;
    @Resource
    AdminMapper adminMapper;
    @Test
    void contextLoads() {
       /* List<Admin> admins = adminMapper.selectAll();
        admins.forEach(admin -> System.out.println(admin));*/
        Admin hx = adminMapper.queryByName("hx");
        System.out.println(hx);


    }
    @Test
    void userTest() {
        List<User> users = userService.queryAll(0,1);
        users.forEach(user -> System.out.println(user));
       /* Integer integer = userService.queryByCount();
        System.out.println("数据库中的用户数量："+integer);*/
    }
}

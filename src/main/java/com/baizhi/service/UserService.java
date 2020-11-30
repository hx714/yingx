package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    //后台：分页查询所有
    List<User> queryAll(Integer page,Integer size);
    //后台：用户的数量
    Integer queryByCount();

    //后台：修改状态
    void updateStatus(User user);
    List<User> selectAll();
}

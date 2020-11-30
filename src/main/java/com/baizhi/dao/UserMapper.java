package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    //后台：分页查询
    List<User> queryAll(@Param("begin") Integer begin,@Param("end") Integer end);
    //后台:查询数量
    Integer queryByCount();
    //后台：修改状态
    void updateStatus(User user);

    //后台：导出用户查询
    List<User> selectAll();
}
package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.List;

public interface LogService {
    //查所有
    List<Log> queryAll(Integer page, Integer rows);
    Integer queryByCount();

}

package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {

    List<Category> queryAll();

    //查询一级类别：分页
    List<Category> queryByOne(Integer page,Integer size);

    //根据父类别id查询二级类别（pId:父类别id）
    List<Category> queryByTwo(String pId,Integer page, Integer size);

    //根据级别查询总条数(levels:级别)
    Integer count(Integer levels);

    //添加
    public void insert(Category category);

    //更新
    void update(Category category);

    //查询一级类别
    List<Category> selectOne();

    //删除
    HashMap<String, Object> delete(Category category);

    //前台：类别首页
    List<Category> queryAllCategory();
}

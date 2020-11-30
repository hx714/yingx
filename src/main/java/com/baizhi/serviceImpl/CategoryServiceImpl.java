package com.baizhi.serviceImpl;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.service.CategoryService;
import com.sun.org.apache.xml.internal.resolver.readers.CatalogReader;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Resource
    private CategoryMapper categoryMapper;

    //查询所有类别
    @Override
    public List<Category> queryAll() {
        List<Category> categories = categoryMapper.queryAll();
        log.debug("CategoryServiceImpl------查询所有类别：{}", categories);
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryByOne(Integer page, Integer size) {
        CategoryExample categoryExample = new CategoryExample();
        //增加查询条件(“根据一级类别查询”)
        categoryExample.createCriteria().andLevelsEqualTo(1);
        Integer pageNow = (page - 1) * size;
        RowBounds rowBounds = new RowBounds(pageNow, size);
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(categoryExample, rowBounds);
        log.debug("CategoryServiceImpl------分页查询一级类别：{}", categories);
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryByTwo(String pId, Integer page, Integer size) {
        CategoryExample example = new CategoryExample();
        //增加查询条件(根据一级类别和父类别id查询)
        example.createCriteria().andLevelsEqualTo(2).andParentIdEqualTo(pId);
        Integer pageNow = (page - 1) * size;
        RowBounds rowBounds = new RowBounds(pageNow, size);
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        log.debug("CategoryServiceImpl------分页查询二级类别：{}", categories);
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count(Integer levels) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andLevelsEqualTo(levels);
        int count = categoryMapper.selectCountByExample(example);
        return count;
    }

    @AddLog("添加类别")
    @Override
    public void insert(Category category) {
        //判断是不是一级类别
        if (category.getParentId() == null) {
            category.setId(UUID.randomUUID().toString());
            category.setParentId("0");
            category.setLevels(1);
        } else {
            category.setId(UUID.randomUUID().toString());
            category.setLevels(2);
        }
        categoryMapper.insert(category);
        log.debug("CategoryServiceImpl------添加类别：{}", category);

    }

    @AddLog("修改类别信息")
    @Override
    public void update(Category category) {
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        log.debug("CategoryServiceImpl------修改类别：{}", i);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> selectOne() {
        CategoryExample categoryExample = new CategoryExample();
        //增加查询条件(“根据一级类别查询”)
        categoryExample.createCriteria().andLevelsEqualTo(1);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        log.debug("CategoryServiceImpl------查询一级类别：{}", categories);
        return categories;
    }

    //删除
    @AddLog("删除类别")
    @Override
    public HashMap<String, Object> delete(Category category) {
        //根据id查询
        Category cate = categoryMapper.selectByPrimaryKey(category.getId());
        HashMap<String, Object> map = new HashMap<>();

        //判断是不是一级类别
        if (cate.getLevels() == 1) {
            CategoryExample categoryExample = new CategoryExample();
            //增加查询条件(“根据一级类别查询”)
            categoryExample.createCriteria().andParentIdEqualTo(cate.getId());
            List<Category> categories = categoryMapper.selectByExample(categoryExample);
            //如果categories.size() 大于0，就说明一级类别下有二级类别(不能删)
            if (categories.size() != 0) {
                map.put("message", "该类别下有子类别,不能删除");
                //打印日志信息
                log.debug("有二级类别,无法删除！条数是：{}", categories.size());
            } else {
                //删除一级
                categoryMapper.deleteByPrimaryKey(cate.getId());
                map.put("message", "删除成功");
            }
        } else {
            //如果不是一级类别
            //判断该类下是否有其他视频
            if (true) {
                //没有视频
                categoryMapper.deleteByPrimaryKey(category);
                map.put("message", "删除成功");
            } else {
                //有视频 不能删除
                map.put("message", "该类别下有视频,不能删除");
            }
        }
        //value
        for(Object value : map.values()){
            System.out.println(value);
        }
        return map;
    }

    @Override
    public List<Category> queryAllCategory() {
        List<Category> categories = categoryMapper.queryAllCategory();
        log.debug("前台分类：{}",categories);
        return categories;
    }

}

package com.baizhi;


import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Category;
import com.baizhi.po.VideoPO;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class CategoryTests {

    @Resource
    private CategoryService categoryService;
    @Resource
    VideoMapper videoMapper;

    @Test
    public void testQueryAll(){
        List<Category> categories = categoryService.queryByTwo("3", 1, 3);
        categories.forEach(category -> System.out.println(category));

       /*List<Category> categories = categoryService.queryByOne(2, 3);
       System.out.println(categories);*/
        /*List<Category> categories = categoryService.queryAll();
        categories.forEach(category -> System.out.println(category));*/
    }
    @Test
    public void testInsert(){
        //Category cate = new Category(null, "大数据应用", 2, "1", null);
        //categoryService.insert(cate);
        List<VideoPO> videoPOS = videoMapper.queryByLikeVideoName("铁");
        for (VideoPO videoPO : videoPOS) {
            System.out.println(videoPO);
        }
    }

}

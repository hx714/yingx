package com.baizhi.controller;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.geom.AreaOp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cate")
@Scope("prototype")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Resource
    private CategoryService categoryService;


    //后台：一级类别信息
    @RequestMapping("one")
    @ResponseBody
    public HashMap<String, Object> queryByOne(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        //当前页数
        map.put("page",page);
        List<Category> categories = categoryService.queryByOne(page, rows);
        //总条数
        Integer count = categoryService.count(1);
        map.put("redords",count);
        //总页数
        Integer totals = count%rows ==0 ?count/rows : count/rows+1;
        map.put("total",totals);

        //一级类别数据
        map.put("rows",categories);
        return map;
    }

    //后台：二级类别信息
    @ResponseBody
    @RequestMapping("two")
    public HashMap<String, Object> queryByTwo(String pId,Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        //当前页数
        map.put("page",page);
        //总条数
        Integer count = categoryService.count(2);
        map.put("records",count);
        //总页数
        int totals = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",totals);
        //二级类别数据
        List<Category> categories = categoryService.queryByTwo(pId, page, rows);
        map.put("rows",categories);

        return map;
    }

    //后台：查询一级类别
    @ResponseBody
    @RequestMapping("selectOne")
    public void selectOne(HttpServletResponse response) throws IOException {
        List<Category> categories = categoryService.selectOne();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<select>");
        categories.forEach(category -> {
            stringBuffer.append("<option value="+category.getId()+">"+category.getName()+"</option>");
        });
        stringBuffer.append("</select>");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().print(stringBuffer);
    }


    //后台：添加
    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String, Object> insert(Category category,String oper){
        HashMap<String, Object> map = null;
        if(oper.equals("add")){
            categoryService.insert(category);
        }
        if(oper.equals("edit")){
            categoryService.update(category);
        }
        if(oper.equals("del")){
               map = categoryService.delete(category);
            //value
            for(Object value : map.values()){
                System.out.println("控制器-map："+value);
            }
            log.debug("删除的错误信息：{}",map);
        }
        return map;
    }
}

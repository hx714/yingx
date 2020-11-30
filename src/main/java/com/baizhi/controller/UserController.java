package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;


    //后台：分页查询    //hashmap:默认初始长度：16
    @RequestMapping("all")
    @ResponseBody
    public HashMap<String, Object> queryAll(Integer page,Integer rows){
        //page:当前页数    row:每页展示的条数
        List<User> users = userService.queryAll(page, rows);
        //count：用户总条数
        Integer count = userService.queryByCount();
        //totals: 总页数
        Integer totals = count % rows == 0 ? count / rows : count / rows + 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);//当前的页数
        map.put("total",totals);//总页数
        map.put("records",count);//总条数
        map.put("rows",users);//所有用户数据
        return map;
    }

    //后台：修改状态
    @RequestMapping("update")
    @ResponseBody
    public HashMap<String, Object> update(User user){
        HashMap<String, Object> map = new HashMap<>();
        try {
            userService.updateStatus(user);
            map.put("message","修改成功");
            map.put("status","200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","修改失败");
            map.put("status","404");
        }
        return map;
    }


    //后台:导出用户信息
    @RequestMapping("getUserEasyPoi")
    @ResponseBody
    public HashMap<String, Object> getUserEasyPoi(){
        HashMap<String, Object> map = new HashMap<>();
        List<User> users = userService.selectAll();
        //导出设置的参数  参数:大标题,工作表名
        ExportParams exportParams = new ExportParams("前台用户信息", "用户信息");

        //导出工具   参数:导出的参数,对应的实体类,导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class, users);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D:\\easyPois.xls")));
            map.put("success",true);
            map.put("message","导出成功！");

        } catch (IOException e) {
            map.put("success",false);
            map.put("message","导出失败！");

        }
        return map;
    }
}

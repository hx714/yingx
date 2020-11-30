package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping("all")
    @ResponseBody
    public HashMap<String,Object> queryAll(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        List<Log> logs = logService.queryAll(page, rows);
        Integer count = logService.queryByCount();
        map.put("page",page);//当前页数
        map.put("records",page);//总条数
        Integer totals = count%page==0?count/page:count/page+1;
        map.put("total",totals);//总页数
        map.put("rows",logs);//所有用户数据
        return map;
    }

}

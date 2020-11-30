package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {

    @Resource
    private VideoService videoService;

    //后台：查所有
    @RequestMapping("all")
    @ResponseBody
    public HashMap<String, Object> queryAll(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        List<Video> videos = videoService.queryAll(page, rows);
        Integer count = videoService.queryByCount();
        map.put("page",page);//当前页数
        map.put("records",count);//总条数
        Integer totals = count%rows==0?count/rows:count/rows+1;
        map.put("total",totals);//总页数
        map.put("rows",videos);
        return map;
    }

    //后台：上传视频
    @RequestMapping("upload")
    @ResponseBody
    public void edit(MultipartFile videoPath, String id){
        videoService.upload(videoPath,id);
    }

    //后台：增删改
    @RequestMapping("edit")
    @ResponseBody
    public Object edit(Video video,String oper){
        if(oper.equals("edit")){

        }
        if(oper.equals("add")){
            String id = videoService.insert(video);
            return id;
        }
        if(oper.equals("del")){
            videoService.delete(video);
        }
        return "";
    }
}

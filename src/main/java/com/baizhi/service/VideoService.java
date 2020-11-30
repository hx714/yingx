package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {
    //添加
    String insert(Video video);

    //文件上传
    void upload(MultipartFile picImg, String id);
    //查询
    List<Video> queryAll(Integer page,Integer rows);

    //查询数量
    Integer queryByCount();

    //删除
    void delete(Video video);

    //首页
    public List<VideoPO> queryByReleaseTime() ;
    //分类视频
    List<VideoPO> queryCateVideoList(String cateId);
    //前台：搜索视频
    List<VideoPO> queryByLikeVideoName(String content);
}

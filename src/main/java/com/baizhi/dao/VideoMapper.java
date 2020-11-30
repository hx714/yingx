package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;
import java.util.List;

import com.baizhi.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface VideoMapper extends Mapper<Video> {

    //前台:首页
    List<VideoPO> queryByReleaseTime();

    //前台：分类视频
    List<VideoPO> queryCateVideoList(@Param("cateId") String cateId);
    //前台：搜索视频
    List<VideoPO> queryByLikeVideoName(@Param("content") String content);
}
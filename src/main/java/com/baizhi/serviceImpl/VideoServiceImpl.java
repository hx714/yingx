package com.baizhi.serviceImpl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;
import com.baizhi.po.VideoPO;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {

    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);
    @Resource
    private VideoMapper videoMapper;

    @Resource
    private HttpServletRequest request;

    //添加
    @Override
    @AddLog("添加视频")
    public String insert(Video video) {
        //ID
        video.setId(UUID.randomUUID().toString());
        //上传时间
        video.setUploadTime(new Date());
        videoMapper.insertSelective(video);
        //前台用户信息

        return video.getId() ;
    }


    //文件上传
    @Override
    public void upload(MultipartFile picImg, String id){
        //获取文件名
        String filename = picImg.getOriginalFilename();
        log.debug("文件的名字：{}",filename);
        String newFileName = new Date().getTime() + "-" + filename;
        log.debug("文件的新名字:{}",newFileName);
        String videoName = "video/"+newFileName;
        AliyunOssUtils.upload(picImg,"huxyingxue",videoName);


        /*//截取文件名
        String[] split = filename.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";
        //上传封面图到阿里云
        AliyunOssUtils.intercept("huxyingxue",videoName,coverName);*/

        //修改视频信息
        Video video = new Video();
        video.setId(id);
        video.setVideoPath("http://huxyingxue.oss-cn-beijing.aliyuncs.com/"+videoName);
        //video.setCoverPath("http://huxyingxue.oss-cn-beijing.aliyuncs.com/"+coverName);
        //编辑封面图
        video.setCoverPath("http://huxyingxue.oss-cn-beijing.aliyuncs.com/"+videoName+"?x-oss-process=video/snapshot,t_5000,f_jpg,w_0,h_0,m_fast,ar_auto");
        videoMapper.updateByPrimaryKeySelective(video);
    }

    //查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Video> queryAll(Integer page,Integer rows) {
        VideoExample example = new VideoExample();
        Integer pageNow = (page-1)*rows;
        RowBounds rowBounds = new RowBounds(pageNow, rows);
        List<Video> videos = videoMapper.selectByExampleAndRowBounds(example, rowBounds);
        log.debug("VideoServiceImpl-查所有：{}",videos);
        return videos;
    }

    //查数量
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer queryByCount() {
        Video video = new Video();
        int count = videoMapper.selectCount(video);
        return count;
    }

    //删除
    @Override
    @AddLog("删除视频")
    public void delete(Video video) {
        //根据id查询信息
        Video v = videoMapper.selectByPrimaryKey(video);
        String videoPath = v.getVideoPath().replace("http://huxyingxue.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = v.getCoverPath().replace("http://huxyingxue.oss-cn-beijing.aliyuncs.com/", "");

        //删除视频
        AliyunOssUtils.deleteFile("huxyingxue",videoPath);
        //删除封面
        AliyunOssUtils.deleteFile("huxyingxue",coverPath);
        //删除数据
        videoMapper.deleteByPrimaryKey(video);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<VideoPO> queryByReleaseTime() {

        List<VideoPO> videoPOS = videoMapper.queryByReleaseTime();
        for (VideoPO videoPO : videoPOS) {
            //获取视频id
            String id = videoPO.getId();

            //根据视频id redis 查询点赞数
            //查询到后并设置
            videoPO.setLikeCount(8);
        }
        return videoPOS;
    }

    @Override
    public List<VideoPO> queryCateVideoList(String cateId) {
        List<VideoPO> videoPOS = videoMapper.queryCateVideoList(cateId);
        for (VideoPO videopo : videoPOS) {
            //获取视频id
            String id = videopo.getId();

            //根据视频id redis 查询点赞数
            //查询到后并设置
            videopo.setLikeCount(8);
        }
        return videoPOS;
    }

    @Override
    public List<VideoPO> queryByLikeVideoName(String content) {
        List<VideoPO> videoPOS = videoMapper.queryByLikeVideoName(content);
        for (VideoPO videopo : videoPOS) {
            //获取视频id
            String id = videopo.getId();

            //根据视频id redis 查询点赞数
            //查询到后并设置
            videopo.setLikeCount(8);
        }
        return videoPOS;
    }


}

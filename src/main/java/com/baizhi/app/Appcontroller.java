package com.baizhi.app;

import com.baizhi.common.CommonResult;
import com.baizhi.entity.Category;
import com.baizhi.po.VideoPO;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtils;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class Appcontroller {

    private static final Logger log = LoggerFactory.getLogger(Appcontroller.class);
    @Resource
    private VideoService videoService;
    @Resource
    private CategoryService categoryService;

    //发送手机验证码
    @RequestMapping("getPhoneCode")
    public Object getPhone(String phone){
        //生成随机验证码
        String code = ImageCodeUtil.getSecurityCode();
        log.debug("手机验证码：{}",code);

        String message = null;

        try {
            message = AliyunUtil.sendPhoneMsg(phone, code);
            return new CommonResult().success(message,phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }

    //前台：首页
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    //前台：首页搜索查询
    @RequestMapping("queryByLikeVideoName")
    public CommonResult queryByLikeVideoName(String content){
        try {
            List<VideoPO> videoPOS = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    //前台：分类
    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory(){
        try {
            List<Category> categories = categoryService.queryAllCategory();
            return new CommonResult().success(categories);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    //前台：分类视频
    @RequestMapping("queryCateVideoList")
    public CommonResult queryCateVideoList(String cateId){
        log.debug("client传来的类别id:{}",cateId);
        try {
            List<VideoPO> videoPOS = videoService.queryCateVideoList(cateId);
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }




}

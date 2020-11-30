package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
@Scope("prototype")
public class AdminControler {

    private static final Logger log = LoggerFactory.getLogger(AdminControler.class);
    @Resource
    HttpSession session;
    @Autowired
    private AdminService adminService;
    //登录
    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(Admin admin,String clientCode) throws IOException {
        log.debug("收集的clientCode:{}",clientCode);
        log.debug("收集的admin:{}",admin);
        Map<String,Object> result = new HashMap<>();
        String coder = (String) session.getAttribute("code");
        String message = null;
        message = adminService.queryByName(admin, clientCode, coder);
        if (message.equals("登陆成功")){
            log.debug("登录信息：{}",message);
            result.put("success",true);
            result.put("msg","登陆成功！");
            Admin a = adminService.ByName(admin);
            session.setAttribute("admin",a);
        }else{
            log.debug("登录信息：{}",message);
            result.put("success",false);
            result.put("msg",message);
        }
        /*//转为json
        String s = JSONObject.toJSONString(result);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(s);*/
        return result;

    }
    //验证码

    @RequestMapping("cover")
    public void getImageCode(HttpServletResponse response, HttpServletRequest request){
        try {
            //生成验证码
            String code = ImageCodeUtil.getSecurityCode();
            //为验证码创建图片
            BufferedImage image = ImageCodeUtil.createImage(code);
            ServletOutputStream outputStream = response.getOutputStream();
            //把验证码画到页面
            ImageIO.write(image,"gif",outputStream);
            request.getSession().setAttribute("code",code);
            log.debug("验证码：{}",code);
        }catch (Exception e){
            e.printStackTrace();
            log.debug("错误日志：{}",e);
        }
    }

    //安全退出
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";

    }
}



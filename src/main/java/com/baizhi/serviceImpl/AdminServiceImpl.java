package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Resource
    private AdminMapper adminMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String queryByName(Admin admin,String coder,String clientCode) {
        String message = null;
        Admin admins = new Admin();
        admins.setUsername(admin.getUsername());
        Admin a = adminMapper.selectOne(admins);
        if(clientCode.equals(coder)){
            if(a==null){
                message="该用户不存在！";
            }else {
                if (admin.getPassword().equals(a.getPassword())){
                    log.debug("AdminServiceImpl----queryByName:{}",a);
                    message="登陆成功";
                }else{
                    message="密码错误！";
                }
            }
        }else{
            message="验证码错误！";
        }


        return message;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin ByName(Admin admin) {
        Admin a = adminMapper.queryByName(admin.getUsername());
        return a;
    }
}

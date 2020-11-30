package com.baizhi.aspect;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class AspectLog {
    private static final Logger log1 = LoggerFactory.getLogger(AspectLog.class);
    @Resource
    HttpServletRequest request;
    @Resource
    private LogMapper logMapper;

    @Around("@annotation(com.baizhi.annotation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        //获取用户数据
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解对应的属性值
        String value = addLog.value();

        String message = null;
        Object result = null;
        //放行方法

        try {
            result = proceedingJoinPoint.proceed();
            message = "success";
        } catch (Throwable throwable) {
            message = "error";
        }
        Log log = new Log(UUID.randomUUID().toString(), admin.getUsername(),new Date(),methodName+" ("+value+")",message);
        logMapper.insert(log);
        log1.debug("添加的日志信息：{}",log);
        return result;
    }
}

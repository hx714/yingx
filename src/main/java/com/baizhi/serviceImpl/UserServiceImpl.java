package com.baizhi.serviceImpl;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service("userSerivce")
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryAll(Integer page,Integer size) {
        Integer begin = (page-1)*size;
        //Integer end = page*size;
        UserExample userExample = new UserExample();
        RowBounds rowBounds = new RowBounds(begin,size);
        List<User> users = userMapper.selectByExampleAndRowBounds(userExample, rowBounds);
        users.forEach(user ->log.debug("service-----分页查询所有：{}",user));
        return users;
    }

    @Override
    public Integer queryByCount() {
        Integer count = userMapper.queryByCount();
        return count;
    }

    @AddLog("修改用户状态")
    @Override
    public void updateStatus(User user) {
        if(user.getStatus().equals("正常")) user.setStatus("冻结");
        else user.setStatus("正常");
        userMapper.updateStatus(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}

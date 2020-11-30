package com.baizhi.serviceImpl;

import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {
    private static final Logger log1 = LoggerFactory.getLogger(LogServiceImpl.class);
    @Resource
    private LogMapper logMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Log> queryAll(Integer page, Integer rows) {
        Log log = new Log();
        //当前页数
        Integer pageNow = (page-1)*rows;
        RowBounds rowBounds = new RowBounds(pageNow, rows);
        List<Log> logs = logMapper.selectByRowBounds(log, rowBounds);
        log1.debug("LogServiceImpl-查所有：{}",logs);
        return logs;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer queryByCount() {
        Log log = new Log();
        return logMapper.selectCount(log);
    }

}

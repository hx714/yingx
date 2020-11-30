package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    String queryByName(Admin admin,String coder,String clientCode);
    Admin ByName(Admin admin);
}

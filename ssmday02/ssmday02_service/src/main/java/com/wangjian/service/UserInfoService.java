package com.wangjian.service;

import com.wangjian.Role;
import com.wangjian.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> findAll();
    void save(UserInfo userInfo);
    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String id);

    void addRoleToUser(String id, String[] roleIds);
}

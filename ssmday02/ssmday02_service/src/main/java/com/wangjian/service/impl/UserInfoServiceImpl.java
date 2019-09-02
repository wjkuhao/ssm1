package com.wangjian.service.impl;

import com.wangjian.Role;
import com.wangjian.UserInfo;
import com.wangjian.dao.UserInfoDao;
import com.wangjian.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return userInfoDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addRoleToUser(String id, String[] roleIds) {
        //遍历数组取出每个id，分别对应id进行添加
        for (String roleId : roleIds) {
            userInfoDao.addRoleToUser(id,roleId);
        }

    }

}

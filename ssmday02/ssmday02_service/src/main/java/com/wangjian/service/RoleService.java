package com.wangjian.service;

import com.wangjian.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    void save(Role role);
 }

package com.wangjian.service;

import com.wangjian.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    void save(Permission permission);
}

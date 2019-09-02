package com.wangjian.dao;

import com.wangjian.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId =#{roleId})")
    Permission findById(String id);
    @Select("select * from permission")
    List<Permission> findAll();
    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
 }

package com.wangjian.dao;

import com.wangjian.Role;
import com.wangjian.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserInfoDao {
    @Select("select * from users")
    List<UserInfo> findAll();
    @Insert("insert into users(email,userName,passWord,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "userName"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wangjian.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id);
    @Select("select * from role where id not in (select roleId from users_role where userID =#{id} )")
    List<Role> findUserByIdAndAllRole (String id);
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String id, @Param("roleId") String roleId);
}

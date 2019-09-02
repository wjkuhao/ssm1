package com.wangjian.dao;

import com.wangjian.Member;
import com.wangjian.Orders;
import com.wangjian.Product;
import org.apache.ibatis.annotations.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType =Product.class,one = @One(select = "com.wangjian.dao.ProductDao.findById")),
    })
    List<Orders> findAll(int page,int size);
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType =Product.class,one = @One(select = "com.wangjian.dao.ProductDao.findById")),
            @Result(property = "member" ,column = "memberId", javaType = Member.class,one =@One(select = "com.wangjian.dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id",javaType = java.util.List.class,many = @Many(select = "com.wangjian.dao.TravllerDao.findById"))
    })
    Orders findById(String id);
}

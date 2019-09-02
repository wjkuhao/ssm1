package com.wangjian.service;

import com.wangjian.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAll(int page,int size);
    public Orders findById(String id);
}

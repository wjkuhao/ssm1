package com.wangjian.service.impl;

import com.github.pagehelper.PageHelper;
import com.wangjian.Orders;
import com.wangjian.dao.OrderDao;
import com.wangjian.service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return orderDao.findAll(page,size);
    }

    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }
}

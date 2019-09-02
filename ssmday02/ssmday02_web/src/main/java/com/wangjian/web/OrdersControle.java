package com.wangjian.web;

import com.github.pagehelper.PageInfo;
import com.wangjian.Orders;
import com.wangjian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersControle {
    @Autowired
    private OrdersService ordersService;
    //没有实现分页的写法
    /*public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll();
        mv.addObject("ordersList",all);
        mv.setViewName("orders-list");
        return mv;
    }*/
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required =true )int page,@RequestParam(name = "size",required = true,defaultValue = "3") int pageSize){
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        ModelAndView mv = new ModelAndView();
        Orders orders= ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}

package com.wangjian.dao;

import com.wangjian.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravllerDao {
    @Select("select * from traveller where  id in (select travellerId from order_traveller where orderId = #{id})")
    public List<Traveller> findById(String id);
}

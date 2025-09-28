package com.bgur.mapper;

import com.bgur.pojo.Cart;

public interface CartMapper {


    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

}
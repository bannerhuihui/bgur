package com.bgur.mapper;

import com.bgur.pojo.ProductVisibleScope;

public interface ProductVisibleScopeMapper {


    int insertSelective(ProductVisibleScope record);

    ProductVisibleScope selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductVisibleScope record);

}
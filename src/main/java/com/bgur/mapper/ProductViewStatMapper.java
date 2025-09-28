package com.bgur.mapper;

import com.bgur.pojo.ProductViewStat;

public interface ProductViewStatMapper {


    int insertSelective(ProductViewStat record);

    ProductViewStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductViewStat record);

}
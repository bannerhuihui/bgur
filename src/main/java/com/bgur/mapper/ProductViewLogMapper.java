package com.bgur.mapper;

import com.bgur.pojo.ProductViewLog;

public interface ProductViewLogMapper {


    int insertSelective(ProductViewLog record);

    ProductViewLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductViewLog record);

}
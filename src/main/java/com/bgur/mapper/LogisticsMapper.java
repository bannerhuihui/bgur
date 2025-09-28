package com.bgur.mapper;

import com.bgur.pojo.Logistics;

public interface LogisticsMapper {


    int insertSelective(Logistics record);

    Logistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logistics record);

}
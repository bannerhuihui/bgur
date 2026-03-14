package com.bgur.mapper;

import com.bgur.pojo.Excel;

public interface ExcelMapper {


    int insertSelective(Excel record);

    Excel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Excel record);

}
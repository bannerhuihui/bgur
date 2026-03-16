package com.bgur.mapper;

import com.bgur.pojo.Excel;

import java.util.List;
import java.util.Map;

public interface ExcelMapper {


    int insertSelective(Excel record);

    Excel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Excel record);

    List<Excel> selectList(Map<String, Object> params);

    int countList(Map<String, Object> params);

}

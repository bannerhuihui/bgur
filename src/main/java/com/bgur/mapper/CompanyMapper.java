package com.bgur.mapper;

import com.bgur.pojo.Company;

public interface CompanyMapper {


    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

}
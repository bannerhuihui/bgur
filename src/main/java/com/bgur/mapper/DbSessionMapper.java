package com.bgur.mapper;

import com.bgur.pojo.DbSession;

public interface DbSessionMapper {


    int insertSelective(DbSession record);

    DbSession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbSession record);

    DbSession selectByRefreshToken(String refreshToken);
}
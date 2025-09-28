package com.bgur.mapper;

import com.bgur.pojo.OperationAuditLog;

public interface OperationAuditLogMapper {


    int insertSelective(OperationAuditLog record);

    OperationAuditLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationAuditLog record);

}
package com.bgur.service.excel.strategy;

import com.bgur.mongodb.bean.ExcelBean;
import com.bgur.common.CommonResult;

import java.util.List;

/**
 * CSV解析策略接口，根据不同的类型将上传的文件解析为不同的实体列表。
 */
public interface CsvParseStrategy {
    /**
     * 返回该策略支持的类型标识（与 ExcelBean.type 对应）。
     */
    String getType();

    /**
     * 执行解析，将上传的 CSV 文件解析为对应的实体列表。
     */
    List<?> parse(ExcelBean excelBean) throws Exception;

    /**
     * 执行业务处理，包含解析及后续持久化/校验等，返回处理结果（成功或失败）。
     */
    CommonResult execute(ExcelBean excelBean);
}
package com.bgur.service.impl;

import com.bgur.common.CommonPageRequest;
import com.bgur.common.CommonResult;
import com.bgur.mongodb.bean.ExcelBean;
import com.bgur.service.ExcelService;
import com.bgur.service.excel.factory.CsvParseStrategyFactory;
import com.bgur.service.excel.strategy.CsvParseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

/**
 * @projectName: bgur
 * @package: com.bgur.service.impl
 * @className: ExcelServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 20:17
 * @version: 1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private CsvParseStrategyFactory csvParseStrategyFactory;

    @Override
    public CommonResult getExcelList(com.bgur.common.CommonPageRequest commonPageRequest) {
        return null;
    }

    @Override
    public CommonResult uploadExcel(ExcelBean excelBean) {
        MultipartFile file = excelBean.getExcelFile();
        String type = excelBean.getType();
        if (file == null || file.isEmpty()) {
            return CommonResult.failure();
        }
        CsvParseStrategy strategy = csvParseStrategyFactory.get(type);
        if (strategy == null) {
            return CommonResult.failure();
        }
        try {
            // 执行业务处理：流式解析 + 批量写入 + 统计
            return strategy.execute(excelBean);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failure();
        }
    }
}

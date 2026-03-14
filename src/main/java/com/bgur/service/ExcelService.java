package com.bgur.service;

import com.bgur.common.CommonPageRequest;
import com.bgur.common.CommonResult;
import com.bgur.mongodb.bean.ExcelBean;

public interface ExcelService {
    CommonResult getExcelList(CommonPageRequest commonPageRequest);

    CommonResult uploadExcel(ExcelBean excelBean);

}

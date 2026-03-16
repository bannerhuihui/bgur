package com.bgur.service.impl;

import com.bgur.common.CommonPageRequest;
import com.bgur.common.CommonResult;
import com.bgur.mapper.ExcelMapper;
import com.bgur.mongodb.bean.ExcelBean;
import com.bgur.pojo.Excel;
import com.bgur.service.ExcelService;
import com.bgur.service.excel.factory.CsvParseStrategyFactory;
import com.bgur.service.excel.strategy.CsvParseStrategy;
import com.bgur.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommonResult getExcelList(CommonPageRequest commonPageRequest) {
        int page = commonPageRequest == null || commonPageRequest.getCurrentPage() == null ? 1 : commonPageRequest.getCurrentPage();
        int pageSize = commonPageRequest == null || commonPageRequest.getMaxRow() == null ? 10 : commonPageRequest.getMaxRow();
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;

        Integer offset = commonPageRequest == null ? null : commonPageRequest.getIndex();
        if (offset == null) {
            offset = (page - 1) * pageSize;
        }

        Map<String, Object> args = commonPageRequest == null ? null : commonPageRequest.getArgs();
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        params.put("beginTime", commonPageRequest == null ? null : commonPageRequest.getBeginTime());
        params.put("endTime", commonPageRequest == null ? null : commonPageRequest.getEndTime());
        if (args != null) {
            params.put("userId", asInteger(args.get("userId")));
            params.put("companyId", asInteger(args.get("companyId")));
            params.put("type", asString(args.get("type")));
            params.put("isAnalyzing", asInteger(args.get("isAnalyzing")));
        }

        int total = excelMapper.countList(params);
        List<Excel> list = total == 0 ? java.util.Collections.emptyList() : excelMapper.selectList(params);
        int totalPage = total == 0 ? 0 : ((total + pageSize - 1) / pageSize);
        PageResult<Excel> result = new PageResult<>(list, total, page, pageSize, totalPage);
        return CommonResult.success(result);
    }

    @Autowired
    private ExcelMapper excelMapper;

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

    private Integer asInteger(Object v) {
        if (v == null) return null;
        if (v instanceof Integer) return (Integer) v;
        if (v instanceof Long) return ((Long) v).intValue();
        if (v instanceof Number) return ((Number) v).intValue();
        String s = String.valueOf(v).trim();
        if (s.isEmpty()) return null;
        try {
            return Integer.parseInt(s);
        } catch (Exception ignored) {
            return null;
        }
    }

    private String asString(Object v) {
        if (v == null) return null;
        String s = String.valueOf(v).trim();
        return s.isEmpty() ? null : s;
    }
}

package com.bgur.service.excel.strategy.impl;

import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.bgur.common.CommonEun;
import com.bgur.common.CommonResult;
import com.bgur.mapper.ExcelMapper;
import com.bgur.mongodb.EtfHolder;
import com.bgur.mongodb.bean.ExcelBean;
import com.bgur.pojo.Excel;
import com.bgur.service.excel.strategy.CsvParseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 解析 ETF_Holder 类型的CSV为 EtfHolder 实体列表，并执行业务持久化。
 */
@Component
public class EtfHolderCsvStrategy implements CsvParseStrategy {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ExcelMapper excelMapper;

    private static final int BATCH_SIZE = 5000;

    @Override
    public String getType() {
        return "1";
    }

    @Override
    public List<EtfHolder> parse(ExcelBean excelBean) throws Exception {
        CsvReader reader = CsvUtil.getReader();
        try (java.io.Reader r = new java.io.InputStreamReader(excelBean.getExcelFile().getInputStream(), Charset.forName("GBK"))) {
            return reader.read(r, EtfHolder.class);
        }
    }

    @Override
    public CommonResult execute(ExcelBean excelBean) {
        AtomicLong total = new AtomicLong(0);
        AtomicLong success = new AtomicLong(0);
        AtomicLong fail = new AtomicLong(0);
        List<EtfHolder> buffer = new ArrayList<>(BATCH_SIZE);
        CsvReadConfig config = new CsvReadConfig();
        config.setSkipEmptyRows(true);
        config.setContainsHeader(true);
        config.setErrorOnDifferentFieldCount(false);
        Excel trace = createTrace(excelBean);
        try (java.io.Reader r = new java.io.InputStreamReader(excelBean.getExcelFile().getInputStream(), Charset.forName("GBK"))) {
            CsvReader reader = CsvUtil.getReader(config);
            reader.read(r, (CsvRow row) -> {
                total.incrementAndGet();
                try {
                    EtfHolder bean = row.toBean(EtfHolder.class);
                    buffer.add(bean);
                    if (buffer.size() >= BATCH_SIZE) {
                        int currentBatchSize = buffer.size();
                        flushBatch(buffer);
                        success.addAndGet(currentBatchSize);
                        buffer.clear();
                    }
                } catch (Exception e) {
                    fail.incrementAndGet();
                }
            });
            // flush remaining
            if (!buffer.isEmpty()) {
                int remaining = buffer.size();
                flushBatch(buffer);
                success.addAndGet(remaining);
                buffer.clear();
            }
            String msg = String.format("导入完成: total=%d, success=%d, fail=%d", total.get(), success.get(), fail.get());
            finishTrace(trace, success.get(), fail.get(), msg);
            return fail.get() == 0 ? CommonResult.success(msg) : CommonResult.failure(CommonEun.ARGUMENT_NULL, msg);
        } catch (Exception e) {
            String msg = String.format("导入失败: total=%d, success=%d, fail=%d, error=%s",
                    total.get(), success.get(), fail.get(), e.getMessage());
            finishTrace(trace, success.get(), fail.get(), msg);
            return CommonResult.failure(CommonEun.ARGUMENT_NULL, e.getMessage());
        }
    }

    private Excel createTrace(ExcelBean excelBean) {
        try {
            Excel trace = new Excel();
            trace.setUserId(excelBean.getUserId());
            trace.setCompanyId(excelBean.getCompanyId());
            trace.setExcelName(excelBean.getExcelFile() == null ? null : excelBean.getExcelFile().getOriginalFilename());
            trace.setSize(excelBean.getExcelFile() == null ? null : (int) Math.min(Integer.MAX_VALUE, excelBean.getExcelFile().getSize()));
            trace.setIsAnalyzing(0);
            trace.setType(excelBean.getType());
            Date now = new Date();
            trace.setCreateTime(now);
            trace.setUpdateTime(now);
            trace.setSuccess(0);
            trace.setError(0);
            excelMapper.insertSelective(trace);
            return trace.getId() == null ? null : trace;
        } catch (Exception ignored) {
            return null;
        }
    }

    private void finishTrace(Excel trace, long success, long error, String data) {
        if (trace == null || trace.getId() == null) {
            return;
        }
        try {
            Excel update = new Excel();
            update.setId(trace.getId());
            update.setIsAnalyzing(1);
            update.setUpdateTime(new Date());
            update.setSuccess((int) Math.min(Integer.MAX_VALUE, success));
            update.setError((int) Math.min(Integer.MAX_VALUE, error));
            update.setData(data);
            excelMapper.updateByPrimaryKeySelective(update);
        } catch (Exception ignored) {
        }
    }

    private void flushBatch(List<EtfHolder> batch) {
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, EtfHolder.class);
        ops.insert(batch);
        ops.execute();
    }
}

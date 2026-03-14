package com.bgur.service.excel.strategy.impl;

import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.bgur.common.CommonEun;
import com.bgur.common.CommonResult;
import com.bgur.mongodb.StockTrading;
import com.bgur.mongodb.bean.ExcelBean;
import com.bgur.service.excel.strategy.CsvParseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @projectName: bgur
 * @package: com.bgur.service.excel.strategy.impl
 * @className: StockTradingCsvStrategy
 * @author: huihui
 * @description: 股票交易CSV解析策略
 * @date: 2025/10/21 23:30
 * @version: 1.0
 */
@Component
public class StockTradingCsvStrategy implements CsvParseStrategy {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final int BATCH_SIZE = 5000;

    @Override
    public String getType() {
        return "StockTrading";
    }

    @Override
    public List<?> parse(ExcelBean excelBean) throws Exception {
        CsvReader reader = CsvUtil.getReader();
        InputStreamReader inputStreamReader = new InputStreamReader(
                excelBean.getExcelFile().getInputStream(),
                Charset.forName("GBK")
        );
        return reader.read(inputStreamReader, StockTrading.class);
    }

    @Override
    public CommonResult execute(ExcelBean excelBean) {
        AtomicLong total = new AtomicLong(0);
        AtomicLong success = new AtomicLong(0);
        AtomicLong fail = new AtomicLong(0);
        List<StockTrading> buffer = new ArrayList<>(BATCH_SIZE);
        CsvReadConfig config = new CsvReadConfig();
        config.setSkipEmptyRows(true);
        config.setContainsHeader(true);
        config.setErrorOnDifferentFieldCount(false);
        try (InputStreamReader inputStreamReader = new InputStreamReader(
                excelBean.getExcelFile().getInputStream(), Charset.forName("GBK"))) {
            CsvReader reader = CsvUtil.getReader(config);
            reader.read(inputStreamReader, (CsvRow row) -> {
                total.incrementAndGet();
                try {
                    StockTrading bean = row.toBean(StockTrading.class);
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
            if (!buffer.isEmpty()) {
                int remaining = buffer.size();
                flushBatch(buffer);
                success.addAndGet(remaining);
                buffer.clear();
            }
            String msg = String.format("导入完成: total=%d, success=%d, fail=%d",
                    total.get(), success.get(), fail.get());
            return fail.get() == 0 ? CommonResult.success(msg) : CommonResult.failure(CommonEun.ARGUMENT_NULL, msg);
        } catch (Exception e) {
            return CommonResult.failure(CommonEun.ARGUMENT_NULL, e.getMessage());
        }
    }

    private void flushBatch(List<StockTrading> batch) {
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, StockTrading.class);
        ops.insert(batch);
        ops.execute();
    }
}
package com.bgur.service.excel.factory;

import com.bgur.service.excel.strategy.CsvParseStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单工厂 + 注册表，按 type 返回对应的解析策略。
 */
@Component
public class CsvParseStrategyFactory {
    private final Map<String, CsvParseStrategy> STRATEGIES = new HashMap<>();

    public CsvParseStrategyFactory(List<CsvParseStrategy> strategyBeans) {
        if (strategyBeans != null) {
            for (CsvParseStrategy s : strategyBeans) {
                register(s);
            }
        }
    }

    public void register(CsvParseStrategy strategy) {
        STRATEGIES.put(strategy.getType(), strategy);
    }

    public CsvParseStrategy get(String type) {
        if (type == null) return null;
        // 先尝试按字符串类型名直接获取（例如：EtfHolder）
        CsvParseStrategy strategy = STRATEGIES.get(type);
        if (strategy != null) return strategy;
        // 兼容数字型 type（1-5）与类型名的映射
        switch (type) {
            case "1":
                return STRATEGIES.get("EtfHolder");
            case "2":
                return STRATEGIES.get("EtfHolding");
            case "3":
                return STRATEGIES.get("IndexHolding");
            case "4":
                return STRATEGIES.get("IndexTrading");
            case "5":
                return STRATEGIES.get("StockTrading");
            default:
                return null;
        }
    }
}
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
        String type = strategy.getType();
        STRATEGIES.put(type, strategy);
        switch (type) {
            case "1":
                STRATEGIES.put("EtfHolder", strategy);
                break;
            case "2":
                STRATEGIES.put("EtfHolding", strategy);
                break;
            case "3":
                STRATEGIES.put("IndexHolding", strategy);
                break;
            case "4":
                STRATEGIES.put("IndexTrading", strategy);
                break;
            case "5":
                STRATEGIES.put("StockTrading", strategy);
                break;
            default:
                break;
        }
    }

    public CsvParseStrategy get(String type) {
        if (type == null) return null;
        String normalized = type.trim();
        CsvParseStrategy strategy = STRATEGIES.get(normalized);
        if (strategy != null) return strategy;
        switch (normalized) {
            case "EtfHolder":
                return STRATEGIES.get("1");
            case "EtfHolding":
                return STRATEGIES.get("2");
            case "IndexHolding":
                return STRATEGIES.get("3");
            case "IndexTrading":
                return STRATEGIES.get("4");
            case "StockTrading":
                return STRATEGIES.get("5");
            default:
                return null;
        }
    }
}

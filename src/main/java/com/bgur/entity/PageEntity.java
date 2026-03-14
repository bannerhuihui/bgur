package com.bgur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: bgur
 * @package: com.bgur.entity
 * @className: CommonPageEntity
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 19:55
 * @version: 1.0
 */
@Data
public class PageEntity<T> implements Serializable {

    private static final long serialVersionUID = -4325780927124699187L;
    private List<T> dataList;// 对象集合
    private int totalPage = 0;// 总页数
    private int currentPage;// 当前页
    private int maxRow = 10;// 每页显示的最大记录数
    private int total = 0; // 数据总条数

    public PageEntity(List<T> dataList, int currentPage, int maxRow, int totalCount) {
        if (maxRow == 0) {
            maxRow = 10;
        }
        this.dataList = dataList;
        this.totalPage = totalCount % maxRow == 0 ? totalCount / maxRow : totalCount / maxRow + 1;
        this.currentPage = currentPage;
        this.maxRow = maxRow;
        this.total = totalCount;
    }

    private PageEntity(){}
}

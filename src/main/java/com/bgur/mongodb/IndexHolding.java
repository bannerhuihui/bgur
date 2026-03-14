package com.bgur.mongodb;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.mongodb
 * @className: IndexHolding
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 23:11
 * @version: 1.0
 */
@Data
@Document(collection = "IndexHolding")
public class IndexHolding implements Serializable {

    private static final long serialVersionUID = -1765458301128965652L;

    @Alias("IndexCode")
    private Long IndexCode;

    @Alias("Idxinfo01")
    private String Idxinfo01;

    @Alias("Date")
    private Date date;

    @Alias("Stk_Code")
    private Long Stk_Code;

    @Alias("Stk_Name")
    private String Stk_Name;

    @Alias("Stk_Weight")
    private Double Stk_Weight;

}

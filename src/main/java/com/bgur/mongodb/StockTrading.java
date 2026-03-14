package com.bgur.mongodb;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.mongodb
 * @className: StockTrading
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 23:12
 * @version: 1.0
 */
@Data
@Document(collection = "StockTrading")
public class StockTrading implements Serializable {

    private static final long serialVersionUID = -8693265975184706042L;
    
    @Alias("Stk_Code")
    private String Stk_Code;

    @Alias("Stk_Name")
    private String Stk_Name;

    @Alias("Date")
    private Date date;

    @Alias("Stk_Close")
    private Double Stk_Close;

    @Alias("Stk_Price")
    private Double Stk_Price;

    @Alias("Total_Shares")
    private Long Total_Shares;

    @Alias("Outstanding_AShares")
    private Long Outstanding_AShares;

    @Alias("State")
    private String State;

}

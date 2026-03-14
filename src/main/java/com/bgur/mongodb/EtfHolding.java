package com.bgur.mongodb;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.mongodb
 * @className: EtfHolding
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 23:11
 * @version: 1.0
 */
@Data
@Document(collection = "EtfHolding")
public class EtfHolding implements Serializable {

    private static final long serialVersionUID = 8892930050520588714L;

    @Alias("ETF_Code")
    private Long ETF_Code;

    @Alias("ETF_Name")
    private String ETF_Name;

    @Alias("ReportType")
    private Integer ReportType;

    @Alias("Date")
    private Date date;

    @Alias("Stk_Code")
    private Long Stk_Code;

    @Alias("Stk_Name")
    private String Stk_Name;

    @Alias("Hold_Num")
    private Long Hold_Num;

    @Alias("Hold_MV")
    private Double Hold_MV;

}

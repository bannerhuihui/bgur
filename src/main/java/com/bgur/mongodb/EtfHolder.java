package com.bgur.mongodb;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.entity
 * @className: EtfHolder
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 20:48
 * @version: 1.0
 */
@Data
@Document(collection = "EtfHolder")
public class EtfHolder implements Serializable {

    private static final long serialVersionUID = -8842768746876109595L;

    @Alias("ETF_Code")
    private Long ETF_Code;

    @Alias("ETF_Name")
    private String ETF_Name;

    @Alias("ReportType")
    private Integer ReportType;

    @Alias("Date")
    private Date date;

    @Alias("Holder_Name")
    private String Holder_Name;

    @Alias("Holder_Num")
    private Long Holder_Num;

    @Alias("Holder_Weight")
    private Double Holder_Weight;

}

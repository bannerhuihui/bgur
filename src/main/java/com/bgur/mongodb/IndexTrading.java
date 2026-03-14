package com.bgur.mongodb;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.mongodb
 * @className: IndexTrading
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 23:11
 * @version: 1.0
 */
@Data
@Document(collection = "IndexTrading")
public class IndexTrading implements Serializable {

    private static final long serialVersionUID = -1854368085142152123L;

    @Alias("IndexCode")
    private Long IndexCode;

    @Alias("Idxinfo01")
    private String Idxinfo01;

    @Alias("Date")
    private Date date;

    @Alias("IndexClose")
    private Double IndexClose;

    @Alias("IndexChange")
    private Double IndexChange;

    @Alias("IndexVolume")
    private Double IndexVolume;

    @Alias("IndexAmount")
    private Double IndexAmount;

}

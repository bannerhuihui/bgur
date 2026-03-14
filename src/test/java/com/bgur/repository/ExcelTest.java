package com.bgur.repository;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import com.bgur.mongodb.EtfHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @projectName: bgur
 * @package: com.bgur.repository
 * @className: ExcelTest
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 20:34
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

    @Test
    public void testExcel() throws IOException {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        final List<EtfHolder> result = reader.read(
                ResourceUtil.getReader("csv/ETF_Holder.csv", Charset.forName("GBK")),
                EtfHolder.class);
        //遍历行
        // 手动格式化日期显示
        EtfHolder entity = result.get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(entity.getDate());
        System.out.println("格式化后的日期: " + formattedDate);
        System.out.println("实体对象: " + entity);
    }
}

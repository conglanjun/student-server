package com.clj.student.excel_sheets;

import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(classes = ExcelTest.class)
public class ExcelTest {

    private List<CarMaintenanceObjectEntity> carMaintenanceObjectList;
    
    @BeforeEach
    public void init() {
        carMaintenanceObjectList = new ArrayList<>();

        // 基础项（isBase = 1）
        CarMaintenanceObjectEntity base = new CarMaintenanceObjectEntity();
        base.setId(1L);
        base.setContractObjectCode("CRHW-24");
        base.setCompanyCode("in3");
        base.setCompanyName("厦门城容环卫有限公司");
        base.setItemKindType("其他");
        base.setMaintenanceCarType("1.5 吨运桶车福龙马牌长安");
        base.setCode("011");
        base.setName("检修灯光(含拆装、线路检修)");
        base.setPrice(new BigDecimal("47.50"));
        base.setGuaranteePeriod(30);
        base.setIsBase(1);
        base.setBaseCode("CRHW-24"); // 基础数据自己的 baseCode 是自己的 contractObjectCode
        base.setEnabled(true);
        carMaintenanceObjectList.add(base);

        // 关联车型 1
        CarMaintenanceObjectEntity related1 = new CarMaintenanceObjectEntity();
        related1.setId(2L);
        related1.setContractObjectCode("CRHW-24-01");
        related1.setCompanyCode("in3");
        related1.setCompanyName("厦门城容环卫有限公司");
        related1.setItemKindType("其他");
        related1.setMaintenanceCarType("3 吨护栏清洗车福龙马牌600P");
        related1.setCode("011");
        related1.setName("检修灯光(含拆装、线路检修)");
        related1.setPrice(new BigDecimal("58.80"));
        related1.setGuaranteePeriod(30);
        related1.setIsBase(0);
        related1.setBaseCode("CRHW-24");
        related1.setEnabled(true);
        carMaintenanceObjectList.add(related1);

        // 关联车型 2
        CarMaintenanceObjectEntity related2 = new CarMaintenanceObjectEntity();
        related2.setId(3L);
        related2.setContractObjectCode("CRHW-24-02");
        related2.setCompanyCode("in3");
        related2.setCompanyName("厦门城容环卫有限公司");
        related2.setItemKindType("其他");
        related2.setMaintenanceCarType("小型清扫车创德游侠 PLUS");
        related2.setCode("011");
        related2.setName("检修灯光(含拆装、线路检修)");
        related2.setPrice(new BigDecimal("61.30"));
        related2.setGuaranteePeriod(30);
        related2.setIsBase(0);
        related2.setBaseCode("CRHW-24");
        related2.setEnabled(true);
        carMaintenanceObjectList.add(related2);
    }

    @Test
    public void exportExcel() {
        // 公共样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        WriteCellStyle headWriteCellStyleData = new WriteCellStyle();
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, headWriteCellStyleData);

        String fileName = "output_sheets.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).registerWriteHandler(styleStrategy).build();
        
        // Sheet1 - 维保项目
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "维保项目").head(header1()).build();
        excelWriter.write(data1(), sheet1);

        // Sheet2 - 项目内车型（动态列）
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "项目内车型").head(header2()).build();
        excelWriter.write(data2(), sheet2);

        // Sheet3 - 成绩档次汇总（原逻辑）
        WriteSheet sheet3 = EasyExcel.writerSheet(2, "模板").head(header3()).build();
        excelWriter.write(data3(), sheet3);

        excelWriter.finish();
    }

    public List<List<String>> header1() {
        return Arrays.asList(
            Collections.singletonList("付款主体"),
            Collections.singletonList("项目编码"),
            Collections.singletonList("项目类别"),
            Collections.singletonList("项目名称"),
            Collections.singletonList("是否启用"),
            Collections.singletonList("质保期（天）"),
            Collections.singletonList("描述")
        );
    }
    public List<List<String>> data1() {
        List<List<String>> list = new ArrayList<>();
        for (CarMaintenanceObjectEntity e : carMaintenanceObjectList) {
            list.add(Arrays.asList(
                e.getCompanyName(),
                e.getCode(),
                e.getItemKindType(),
                e.getName(),
                e.getEnabled() != null && e.getEnabled() ? "是" : "否",
                e.getGuaranteePeriod() == null ? "" : e.getGuaranteePeriod().toString(),
                e.getDescription()
            ));
        }
        return list;
    }

    public List<List<String>> header2() {
        List<String> carTypes = getAllCarTypes(); // 获取所有车型（比如从数据里取唯一车型名）
        List<List<String>> headers = new ArrayList<>();
        headers.add(Collections.singletonList("合同标的编码"));
        headers.add(Collections.singletonList("项目编码"));
        headers.add(Collections.singletonList("项目名称"));
        for (String carType : carTypes) {
            headers.add(Collections.singletonList(carType + "工时费"));
        }
        return headers;
    }

    public List<List<String>> data2() {
        List<List<String>> data = new ArrayList<>();
    
        // 获取所有车型名
        List<String> carTypes = getAllCarTypes();
    
        // 1. 找出基础数据（isBase == 1）
        List<CarMaintenanceObjectEntity> baseItems = carMaintenanceObjectList.stream()
            .filter(e -> e.getIsBase() != null && e.getIsBase() == 1)
            .collect(Collectors.toList());
    
        // 2. 遍历每个基础项目
        for (CarMaintenanceObjectEntity base : baseItems) {
            List<String> row = new ArrayList<>();
            row.add(base.getContractObjectCode()); // 合同标的编码
            row.add(base.getCode());               // 项目编码
            row.add(base.getName());               // 项目名称
    
            // 3. 找到所有与该基础项目相关联的车型信息
            List<CarMaintenanceObjectEntity> relatedCars = carMaintenanceObjectList.stream()
                .filter(e -> Objects.equals(e.getIsBase(), 0))
                .filter(e -> Objects.equals(e.getBaseCode(), base.getContractObjectCode()))
                .collect(Collectors.toList());
    
            // 4. 为每种车型构造价格列（没有则留空）
            for (String carType : carTypes) {
                Optional<CarMaintenanceObjectEntity> match = relatedCars.stream()
                    .filter(r -> carType.equals(r.getMaintenanceCarType()))
                    .findFirst();
                row.add(match.map(e -> e.getPrice() != null ? e.getPrice().toString() : "").orElse(""));
            }
    
            data.add(row);
        }
    
        return data;
    }
    


    public List<String> getAllCarTypes() {
        return carMaintenanceObjectList.stream()
            .map(CarMaintenanceObjectEntity::getMaintenanceCarType)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
    }

    public List<List<String>> data3() {
        // 空数据
        return Collections.emptyList();
    }

    public List<List<String>> header3() {
        return Arrays.asList(
            Collections.singletonList("合同标的编号"),
            Collections.singletonList("维保车型"),
            Collections.singletonList("是否合同内")
        );
    }
}

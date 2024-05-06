package com.clj.student.excelManage;

import java.util.List;
import java.util.Map;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.BooleanEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DataEntity {
    @ExcelIgnore
    private String title;
    @ExcelIgnore
    private Month month;
    @ExcelIgnore
    private String companyName;
    @ExcelIgnore
    private String parentProject;
    @ExcelIgnore
    private String project;
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String score;
    private float appropriation;
    private String comment;
}

@Data
class ScoreLevelExcelData {
    Map<Integer, List<DataEntity>> dataMap;
    List<DataEntity> heads;
}

@Data
class ExcelData {
    @ColumnWidth(12)
    @ExcelProperty(value = "单位", index = 0)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String companyName;
    @ColumnWidth(12)
    @ExcelProperty(value = "单位", index = 1)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String parentProject;
    @ExcelProperty(value = "考评", index = 2)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String project;
    @ColumnWidth(10)
    @ExcelProperty(value = "1月", index = 3)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String january;
    @ColumnWidth(10)
    @ExcelProperty(value = "2月", index = 4)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String february;
    @ColumnWidth(10)
    @ExcelProperty(value = "3月", index = 5)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String march;
    @ColumnWidth(10)
    @ExcelProperty(value = "4月", index = 6)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String april;
    @ColumnWidth(10)
    @ExcelProperty(value = "5月", index = 7)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String may;
    @ColumnWidth(10)
    @ExcelProperty(value = "6月", index = 8)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String june;
    @ColumnWidth(10)
    @ExcelProperty(value = "7月", index = 9)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String july;
    @ColumnWidth(10)
    @ExcelProperty(value = "8月", index = 10)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String august;
    @ColumnWidth(10)
    @ExcelProperty(value = "9月", index = 11)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String september;
    @ColumnWidth(10)
    @ExcelProperty(value = "10月", index = 12)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String october;
    @ColumnWidth(10)
    @ExcelProperty(value = "11月", index = 13)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String november;
    @ExcelProperty(value = "12月", index = 14)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String december;
    @ExcelProperty(value = "备注", index = 15)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String comment;
}

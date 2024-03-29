package com.clj.student.excel;

import java.util.List;
import java.util.Map;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

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
    @ExcelProperty("成绩")
    private String score;
    @ExcelProperty("档次")
    private String level;
}

@Data
class ScoreLevelExcelData {
    Map<Integer, List<DataEntity>> dataMap;
    List<DataEntity> heads;
}

package com.clj.student.excel_sheets;
import java.util.Date;
import java.math.BigDecimal;
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
public class CarMaintenanceObjectEntity {

    private Long id;
    
    @ExcelIgnore
    private String contractObjectCode; // 合同标的编码

    @ExcelIgnore
    private String companyCode; // 付款主体code

    @ExcelIgnore
    private String companyName; // 付款主体

    @ExcelIgnore
    private String maintenanceKindType; // 维保类型

    @ExcelIgnore
    private String maintenanceCarType; // 维保车型

    @ExcelIgnore
    private String itemKindType; // 项目类别

    @ExcelIgnore
    private String code; // 编码

    @ExcelIgnore
    private String name; // 名称

    @ExcelIgnore
    private BigDecimal price; // 工时费

    @ExcelIgnore
    private Integer guaranteePeriod; // 质保期

    @ExcelIgnore
    private String engineKind; // 发动机类型

    @ExcelIgnore
    private BigDecimal costHour; // 维保时长 - 1位小数

    @ExcelIgnore
    private Boolean enabled; // 是否可用

    @ExcelIgnore
    private Boolean isImportant; // 是否重要项目

    @ExcelIgnore
    private Integer mileage; // 保养周期公里

    @ExcelIgnore
    private Integer days; // 保养周期天数

    @ExcelIgnore
    private Integer resourceType; // 资源类型（1清单内、2清单外审批通过等）

    @ExcelIgnore
    private String description; // 描述

    @ExcelIgnore
    private Long createUserId; // 创建用户id

    @ExcelIgnore
    private Date createTime; // 创建时间

    @ExcelIgnore
    private Long modifyUserId; // 修改用户id

    @ExcelIgnore
    private Date modifyTime; // 修改时间

    @ExcelIgnore
    private Boolean isDelete; // 是否删除（1是，0否）

    @ExcelIgnore
    private String orderNo; // 维保单号

    @ExcelIgnore
    private Integer isBase; // 是否基础信息

    @ExcelIgnore
    private String createUserCn; // 创建人名字

    @ExcelIgnore
    private String modifyUserCn; // 修改人名字

    @ExcelIgnore
    private String baseCode; // 基础信息编码
}

@Data
class ScoreLevelExcelData {
    Map<Integer, List<CarMaintenanceObjectEntity>> dataMap;
    List<CarMaintenanceObjectEntity> heads;
}

package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumptionData {
    private Long id;
    private Date createTime;
    private String displayCreateTime;
    private String type;
    private Integer inventory;
    private Integer consumptionNum;
}

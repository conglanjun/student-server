 package com.clj.student.model.dto;

import java.util.Date;

import com.clj.student.model.po.Consumption;
import com.clj.student.model.po.User;

import lombok.Data;

@Data
public class ConsumptionRecordData {
    private Long id;
    private Date createTime;
    private String displayCreateTime;
    private Long consumptionId;
    private Consumption consumption;
    private Integer consumptionNumber;
    private Long creatorId;

    private User creator;
 }
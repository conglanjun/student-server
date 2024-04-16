package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BuildingData {
    private Long id;
    private Date createTime;
    private String name;
    private String description;
    private String displayDescription;
    private boolean nameDuplicated;
}

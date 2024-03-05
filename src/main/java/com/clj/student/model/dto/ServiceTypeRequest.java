package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceTypeRequest {
    private Long id;
    private String name;
    private Date createTime;

}

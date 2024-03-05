package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentRequest {
    private Long id;
    private String name;
    private String phone;
    private Date createTime;
    private String password;
}

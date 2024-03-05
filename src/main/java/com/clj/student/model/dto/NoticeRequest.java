package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeRequest {
    private Long id;
    private Date createTime;
    private String text;
}

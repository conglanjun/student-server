package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeData {
    private Long id;
    private Date createTime;
    private String displayCreateTime;
    private String text;
    private String displayText;
    private String imageAddress;
}

package com.clj.student.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoomData {
    private Long id;
    private Date createTime;
    private String displayCreateTime;
    private String name;
    private String description;
    private String displayDescription;
}

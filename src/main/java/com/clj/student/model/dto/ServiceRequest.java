package com.clj.student.model.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ServiceRequest {
    private Long id;
    private String title;
    private String detail;
    private Date createTime;
    private String status;
    private Long creatorId;
    private Long maintainerId;
    private Integer rate;
    private String comment;
    private Long typeId;
    private Long roomId;

}

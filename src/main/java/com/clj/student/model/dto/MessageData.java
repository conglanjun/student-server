package com.clj.student.model.dto;

import com.clj.student.model.po.User;
import lombok.Data;

import java.util.Date;

@Data
public class MessageData {
    private Long id;
    private String title;
    private String message;
    private String displayMessage;
    private Date createTime;
    private String displayCreateTime;
    private User user;
    private Long userId;
    private String imageAddress;
}

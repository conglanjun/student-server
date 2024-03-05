package com.clj.student.model.dto;

import com.clj.student.model.po.Room;
import com.clj.student.model.po.ServiceType;
import com.clj.student.model.po.User;
import lombok.Data;

import java.util.Date;

@Data
public class ServiceData {
    private Long id;
    private String title;
    private String displayTitle;
    private String detail;
    private Date createTime;
    private String status;
    private String displayStatus;
//    private Long creatorId;
    private User creator;
    private User maintainer;
    private Integer rate;
    private String comment;
    private ServiceType serviceType;
    private String displayCreateTime;
    private Room room;
    private String displayRoomInfo;
}

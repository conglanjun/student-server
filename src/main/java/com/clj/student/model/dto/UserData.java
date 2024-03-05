package com.clj.student.model.dto;

import com.clj.student.model.po.Building;
import com.clj.student.model.po.Role;
import com.clj.student.model.po.Room;
import lombok.Data;

import java.util.Date;

@Data
public class UserData {
    private Long id;
    private String name;
    private String phone;
    private Date createTime;
    private String password;
    private String identity;
    private Role role;
    private Long roleId;
    private Building building;
    private Long buildingId;
    private Room room;
    private Long roomId;
    private String displayRoomInfo;
}

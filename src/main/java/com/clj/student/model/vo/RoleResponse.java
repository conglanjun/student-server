package com.clj.student.model.vo;

import com.clj.student.model.po.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoleResponse {
    private int code;
    private String message;
    private List<Role> roleList;
}

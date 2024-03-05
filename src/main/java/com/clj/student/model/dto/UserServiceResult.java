package com.clj.student.model.dto;

import com.clj.student.model.po.User;
import com.clj.student.service.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserServiceResult {
    private UserData user;
    private UserStatus status;
}

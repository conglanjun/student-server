package com.clj.student.model.vo;

import com.clj.student.model.dto.UserData;
import com.clj.student.model.dto.UserServiceResult;
import com.clj.student.model.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
    private int code;
    private String message;
    private Long id;
    private List<User> userList;
    private List<UserData> userDataList;
    private User user;
    private UserData userData;
    private UserServiceResult userServiceResult;

    public UserResponse(int code, String message, Long id) {
        this.code = code;
        this.message = message;
        this.id = id;
    }

    public UserResponse(int code, String message, List<User> userList) {
        this.code = code;
        this.message = message;
        this.userList = userList;
    }

    public UserResponse(int code, List<UserData> userDataList, String message) {
        this.code = code;
        this.message = message;
        this.userDataList = userDataList;
    }

    public UserResponse(int code, String message, User user) {
        this.code = code;
        this.message = message;
        this.user = user;
    }

    public UserResponse(int code, String message, UserData userData) {
        this.code = code;
        this.message = message;
        this.userData = userData;
    }

    public UserResponse(int code, String message, UserServiceResult userServiceResult) {
        this.code = code;
        this.message = message;
        this.userServiceResult = userServiceResult;
    }
}

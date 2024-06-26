package com.clj.student.controller;

import com.clj.student.dao.UserRepository;
import com.clj.student.model.dto.UserData;
import com.clj.student.model.dto.UserServiceResult;
import com.clj.student.model.po.Role;
import com.clj.student.model.po.User;
import com.clj.student.model.vo.RoleResponse;
import com.clj.student.model.vo.UserResponse;
import com.clj.student.service.UserService;
import com.clj.student.utils.Secret;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("queryAll")
    public UserResponse queryAll() {
        // query db
        return new UserResponse(200, "get user list successfully!", userRepository.findAll());
    }

    // save student
    @PostMapping("save/user")
    public UserResponse saveUser(@RequestBody UserData user) {
        if (Strings.isEmpty(user.getPhone())) {
            return new UserResponse(400, "电话号空，请输入电话号！", 0L);
        }
        List<User> userList = userService.userByPhone(user);
        if (!userList.isEmpty()) {
            return new UserResponse(400, "电话号已存在！", 0L);
        }
        UserServiceResult save = userService.save(user);
        return new UserResponse(200, "save user successfully!", save.getUser());
    }

    @DeleteMapping("user/delete/{id}")
    public UserResponse userDelete(@PathVariable Long id) {
        userService.delete(id);
        return new UserResponse(200, "delete user successfully!");
    }
    

    @PostMapping("login")
    public UserResponse login(@RequestBody UserData user) {
        log.info("login user:" + user);
        UserServiceResult loginResult = userService.login(user);
        return new UserResponse(200, "login handle successfully!", loginResult);
    }

    @GetMapping("userList")
    public UserResponse userList(@RequestParam(required = false) Long roleId, @RequestParam(required = false) String name) {
        List<UserData> userList = userService.userList(roleId, name);
        return new UserResponse(200, userList, "user list successfully!");
    }

    @GetMapping("roleList")
    public RoleResponse roleList() {
        List<Role> roleList = userService.roleList();
        return new RoleResponse(200, "query role list!", roleList);
    }

    @PostMapping("user/update")
    public UserResponse userUpdate(@RequestBody UserData userData) {
        UserData ud = userService.userUpdate(userData);
        log.info("update user ud:" + ud);
        return new UserResponse(200, "update user successfully!", ud);
    }

    @PostMapping("user/changeSecret")
    public UserResponse changeSecret(@RequestBody UserData userData) {
        List<User> userList = userService.userByPhone(userData);
        if (userList.size() == 0) {
            return new UserResponse(400, "用户不存在！", 0L);
        }
        User user = userList.get(0);
        userData.setId(user.getId());
        userData.setPassword(Secret.md5(userData.getPassword()));
        UserData ud = userService.userUpdate(userData);
        log.info("update user ud:" + ud);
        return new UserResponse(200, "update user successfully!", ud);
    }

}
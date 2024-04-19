package com.clj.student.service;

import com.clj.student.dao.BuildingRepository;
import com.clj.student.dao.RoleRepository;
import com.clj.student.dao.RoomRepository;
import com.clj.student.dao.UserRepository;
import com.clj.student.model.dto.UserData;
import com.clj.student.model.dto.UserServiceResult;
import com.clj.student.model.po.Role;
import com.clj.student.model.po.Room;
import com.clj.student.model.po.User;
import com.clj.student.utils.ModelConvert;
import com.clj.student.utils.Secret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RoomRepository roomRepository;

    public UserServiceResult login(UserData user){
        List<User> userListByPhone = userByPhone(user);
        if (userListByPhone.size() == 0) {
            return new UserServiceResult(null, UserStatus.NOTLOGIN);
        }
        User u = userListByPhone.get(0);
        if (u.getRole() == null) {
            u.setRole(new Role(-1L, "none", "未分配权限"));
        }
        UserData ret = ModelConvert.UserConvertUserData(u);
        if (ret.getRoom() == null) {
            ret.setRoom(new Room(-1L, Calendar.getInstance().getTime(), "无", "未分配"));
            ret.setDisplayRoomInfo("无");
        } else {
            ret.setDisplayRoomInfo(ret.getRoom().getBuilding().getName() + "楼 " + ret.getRoom().getName());
        }
        if (!Secret.md5(user.getPassword()).equals(ret.getPassword())) {
            return  new UserServiceResult(null, UserStatus.INCORRECTPASSWORD);
        }
        return new UserServiceResult(ret, UserStatus.LOGIN);
    }

    public UserServiceResult save(UserData user) {
        user.setCreateTime(Calendar.getInstance().getTime());
        User userEntity = ModelConvert.UserDataConvertUser(user);
        String secret = Secret.md5(userEntity.getPassword());
        userEntity.setPassword(secret);
        if (user.getRoomId() != null && user.getRoomId() > 0) {
            Optional<Room> roomById = roomRepository.findById(user.getRoomId());
            roomById.ifPresent(userEntity::setRoom);
        }
        if (user.getRoleId() != null && user.getRoleId() > 0) {
            Optional<Role> roleById = roleRepository.findById(user.getRoleId());
            roleById.ifPresent(userEntity::setRole);
        }
        User save = userRepository.save(userEntity);
        UserData ret = ModelConvert.UserConvertUserData(save);
        if (ret.getRoom() == null) {
            ret.setRoom(new Room(-1L, Calendar.getInstance().getTime(), "无", "未分配"));
            ret.setDisplayRoomInfo("无");
        } else {
            ret.setDisplayRoomInfo(ret.getRoom().getBuilding().getName() + "楼 " + ret.getRoom().getName());
        }
        if (!Secret.md5(user.getPassword()).equals(ret.getPassword())) {
            return  new UserServiceResult(null, UserStatus.INCORRECTPASSWORD);
        }
        log.info("saved student:" + ret);
        return new UserServiceResult(ret, UserStatus.SAVED);
    }

    public List<User> userByPhone(UserData userData) {
        return userRepository.findAllByPhone(userData.getPhone());
    }

    public List<Role> roleList() {
        return roleRepository.findAll();
    }

    public List<UserData> userList(Long roleId) {
        List<UserData> ret = new ArrayList<>();
        List<User> userList;
        if (roleId == null || roleId == 0) {
            userList = userRepository.findAll();
        } else {
            userList = userRepository.findAllByRoleId(roleId);
        }
        for (User user : userList) {
            UserData ud = ModelConvert.UserConvertUserData(user);
            if (user.getRole() == null) {
                ud.setRole(new Role(-1L, "none", "未分配权限"));
            }
            if (user.getRoom() == null) {
                ud.setRoom(new Room(-1L, Calendar.getInstance().getTime(), "无", "未分配"));
                ud.setDisplayRoomInfo("无");
            } else {
                ud.setDisplayRoomInfo(user.getRoom().getBuilding().getName() + "楼 " + user.getRoom().getName());
            }
            if (user.getRole() == null) {
                ud.setDisplayRoomInfo("无");
            } else {
                ud.setDisplayRoleInfo(user.getRole().getDisplayName());
            }
            ret.add(ud);
        }
        return ret;
    }

    @Transactional
    public UserData userUpdate(UserData userData) {
        if (userData.getId() == null) {
            return null;
        }
        Optional<User> userById = userRepository.findById(userData.getId());
        if (userById.isEmpty()) {
            return null;
        }
        User u = userById.get();
        if (userData.getRoleId() != null) {
            Optional<Role> roleById = roleRepository.findById(userData.getRoleId());
            roleById.ifPresent(u::setRole);
        }
        if (userData.getRoomId() != null) {
            Optional<Room> roomById = roomRepository.findById(userData.getRoomId());
            roomById.ifPresent(u::setRoom);
        } else {
            u.setRoom(null);
            u.setRoomId(null);
        }
        if (userData.getName() != null) {
            u.setName(userData.getName());
        }
        if (userData.getPhone() != null) {
            u.setPhone(userData.getPhone());
        }
        if (userData.getCreateTime() != null) {
            u.setCreateTime(userData.getCreateTime());
        }
        if (userData.getPassword() != null) {
            u.setPassword(userData.getPassword());
        }
        if (userData.getIdentity() != null) {
            u.setIdentity(userData.getIdentity());
        }
        User save = userRepository.save(u);
        UserData ret = ModelConvert.UserConvertUserData(save);
        if (ret.getRoom() == null) {
            ret.setRoom(new Room(-1L, Calendar.getInstance().getTime(), "无", "未分配"));
            ret.setDisplayRoomInfo("无");
        } else {
            ret.setDisplayRoomInfo(ret.getRoom().getBuilding().getName() + "楼 " + ret.getRoom().getName());
        }
        return ret;
    }
}


package com.clj.student.dao;

import com.clj.student.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByPhone(String phone);

    @Modifying
    @Query("update User u set u.roleId = :roleId where u.id = :id")
    int updateUserRole(@Param(value = "id") long id, @Param(value = "roleId") int roleId);

    List<User> findAllByRoleId(Long roleId);

    List<User> findByNameContaining(String name);
}

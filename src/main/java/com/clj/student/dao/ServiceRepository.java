package com.clj.student.dao;

import com.clj.student.model.po.Service;

import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByCreatorIdOrderByCreateTimeDesc(Long creatorId);
    List<Service> findAllByMaintainerIdOrderByCreateTimeDesc(Long maintainerId);

    List<Service> findAllByOrderByCreateTimeDesc();

    @Query(nativeQuery = true, value = "select * from service s where s.room_id in (:roomIds) order by create_time desc")
    List<Service> findByRoomIds(@Param(value = "roomIds") List<Long> roomIds);
}

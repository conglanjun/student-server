package com.clj.student.dao;

import com.clj.student.model.po.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByBuildingId(Long buildingId);

    List<Room> findAllByNameAndBuildingId(String name, Long buildingId);
}

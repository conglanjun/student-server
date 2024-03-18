package com.clj.student.dao;

import com.clj.student.model.po.Building;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAllByOrderByCreateTimeDesc();
    List<Building> findByNameOrderByCreateTimeDesc(String name);
}

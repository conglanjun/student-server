package com.clj.student.dao;

import com.clj.student.model.po.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByCreatorId(Long creatorId);
    List<Service> findAllByMaintainerId(Long maintainerId);
}

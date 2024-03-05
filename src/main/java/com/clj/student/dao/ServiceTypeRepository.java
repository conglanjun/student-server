package com.clj.student.dao;

import com.clj.student.model.po.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}

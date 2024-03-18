package com.clj.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clj.student.model.po.ConsumptionRecord;

public interface ConsumptionRecordRepository extends JpaRepository<ConsumptionRecord, Long> {
    
}

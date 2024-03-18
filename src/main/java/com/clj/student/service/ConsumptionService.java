package com.clj.student.service;

import com.clj.student.dao.ConsumptionRecordRepository;
import com.clj.student.dao.ConsumptionRepository;
import com.clj.student.dao.UserRepository;
import com.clj.student.model.dto.ConsumptionData;
import com.clj.student.model.dto.ConsumptionRecordData;
import com.clj.student.model.po.Consumption;
import com.clj.student.model.po.ConsumptionRecord;
import com.clj.student.model.po.User;
import com.clj.student.utils.ModelConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private ConsumptionRecordRepository consumptionRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ConsumptionData> consumptionDataList() {
        List<ConsumptionData> ret = new ArrayList<>();
        List<Consumption> consumptionList = consumptionRepository.findAll();
        for (int i = 0; i < consumptionList.size(); i++) {
            Consumption consumption = consumptionList.get(i);
            ConsumptionData consumptionData = ModelConvert.ConsumptionConvertConsumptionData(consumption);
            ret.add(consumptionData);
        }
        return ret;
    }

    public ConsumptionData save(ConsumptionData cd) {
        Consumption c = ModelConvert.ConsumptionDataConvertConsumption(cd, new Consumption());
        c.setCreateTime(Calendar.getInstance().getTime());
        Consumption consumption = consumptionRepository.save(c);
        return ModelConvert.ConsumptionConvertConsumptionData(consumption);
    }

    public void delete(Long id) {
        Consumption c = new Consumption();
        c.setId(id);
        consumptionRepository.delete(c);
    }

    public ConsumptionData update(ConsumptionData cd) {
        Optional<Consumption> consumptionById = consumptionRepository.findById(cd.getId());
        if (consumptionById.isEmpty()) {
            return null;
        }
        cd.setInventory(cd.getInventory() - (cd.getConsumptionNum() == null? 0: cd.getConsumptionNum()));
        Consumption c = consumptionById.get();
        Consumption consumption = ModelConvert.ConsumptionDataConvertConsumption(cd, c);
        Consumption saved = consumptionRepository.save(consumption);
        return ModelConvert.ConsumptionConvertConsumptionData(saved);
    }

    public ConsumptionRecordData saveConsumptionRecord(ConsumptionRecordData crd) {
        ConsumptionRecord cr = ModelConvert.ConsumptionRecordDataConvertConsumptionRecord(crd, new ConsumptionRecord());
        cr.setCreateTime(Calendar.getInstance().getTime());
        Consumption c = new Consumption();
        c.setId(cr.getConsumptionId());
        cr.setConsumption(c);
        User u = new User();
        u.setId(crd.getCreatorId());
        cr.setCreator(u);
        ConsumptionRecord consumptionRecord = consumptionRecordRepository.save(cr);
        return ModelConvert.ConsumptionRecordConvertConsumptionRecordData(consumptionRecord);
    }

    public List<ConsumptionRecordData> consumptionRecordDataList() {
        List<ConsumptionRecordData> ret = new ArrayList<>();
        List<ConsumptionRecord> consumptionRecordList = consumptionRecordRepository.findAll();
        for (int i = 0; i < consumptionRecordList.size(); i++) {
            ConsumptionRecord consumptionRecord = consumptionRecordList.get(i);
            ConsumptionRecordData consumptionRecordData = ModelConvert.ConsumptionRecordConvertConsumptionRecordData(consumptionRecord);
            ret.add(consumptionRecordData);
        }
        return ret;
    }
}

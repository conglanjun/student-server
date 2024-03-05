package com.clj.student.controller;

import com.clj.student.model.dto.ConsumptionData;
import com.clj.student.model.vo.ConsumptionResponse;
import com.clj.student.service.ConsumptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping("consumptionList")
    public ConsumptionResponse consumptionDataList() {
        List<ConsumptionData> consumptionDataList = consumptionService.consumptionDataList();
        return new ConsumptionResponse(200, "save consumption successfully!", consumptionDataList);
    }

    @PostMapping("consumption/save")
    public ConsumptionResponse consumptionSave(@RequestBody ConsumptionData cd) {
        ConsumptionData consumptionData = null;
        if (cd.getId() == null) {
            consumptionData = consumptionService.save(cd);
        } else {
            consumptionData = consumptionService.update(cd);
        }
        return new ConsumptionResponse(200, "save consumption successfully!", consumptionData);
    }

    @DeleteMapping("consumption/delete/{id}")
    public ConsumptionResponse consumptionDelete(@PathVariable Long id) {
        log.info("consumption delete id:{}", id);
        consumptionService.delete(id);
        return new ConsumptionResponse(200, "delete consumption successfully!");
    }

    @PostMapping("consumption/update")
    public ConsumptionResponse consumptionUpdate(@RequestBody ConsumptionData cd) {
        if (cd.getConsumptionNum() > cd.getInventory()) {
            return new ConsumptionResponse(400, "consumption number is must greater than inventory.");
        }
        ConsumptionData consumptionData = consumptionService.update(cd);
        return new ConsumptionResponse(200, "update consumption successfully!", consumptionData);
    }
}

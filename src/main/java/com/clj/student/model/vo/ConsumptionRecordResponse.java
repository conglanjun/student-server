package com.clj.student.model.vo;

import com.clj.student.model.dto.ConsumptionRecordData;
import lombok.Data;

import java.util.List;

@Data
public class ConsumptionRecordResponse {
    private int code;
    private String message;
    private List<ConsumptionRecordData> consumptionRecordDataList;
    private ConsumptionRecordData consumptionData;

    public ConsumptionRecordResponse(int code, String message, ConsumptionRecordData crd) {
        this.code = code;
        this.message = message;
        this.consumptionData = crd;
    }

    public ConsumptionRecordResponse(int code, String message, List<ConsumptionRecordData> crds) {
        this.code = code;
        this.message = message;
        this.consumptionRecordDataList = crds;
    }
}

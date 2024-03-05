package com.clj.student.model.vo;

import com.clj.student.model.dto.ConsumptionData;
import lombok.Data;

import java.util.List;

@Data
public class ConsumptionResponse {
    private int code;
    private String message;
    private List<ConsumptionData> consumptionDataList;
    private ConsumptionData consumptionData;

    public ConsumptionResponse(int code, String message, List<ConsumptionData> consumptionDataList, ConsumptionData consumptionData) {
        this.code = code;
        this.message = message;
        this.consumptionDataList = consumptionDataList;
        this.consumptionData = consumptionData;
    }

    public ConsumptionResponse(int code, String message, List<ConsumptionData> consumptionDataList) {
        this.code = code;
        this.message = message;
        this.consumptionDataList = consumptionDataList;
    }

    public ConsumptionResponse(int code, String message, ConsumptionData consumptionData) {
        this.code = code;
        this.message = message;
        this.consumptionData = consumptionData;
    }

    public ConsumptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

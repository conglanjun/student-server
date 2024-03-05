package com.clj.student.model.vo;

import com.clj.student.model.dto.BuildingData;
import lombok.Data;

import java.util.List;

@Data
public class BuildingResponse {
    private int code;
    private String message;
    private List<BuildingData> buildingDataList;
    private BuildingData buildingData;

    public BuildingResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BuildingResponse(int code, String message, List<BuildingData> buildingDataList) {
        this.code = code;
        this.message = message;
        this.buildingDataList = buildingDataList;
    }

    public BuildingResponse(int code, String message, BuildingData buildingData) {
        this.code = code;
        this.message = message;
        this.buildingData = buildingData;
    }
}

package com.clj.student.model.vo;

import java.util.List;

import lombok.Data;

@Data
public class BuildingRoomResponse {
    private List<BuildingRoomItem> buildingRoomItems;
}

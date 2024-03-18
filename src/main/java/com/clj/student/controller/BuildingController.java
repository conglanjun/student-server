package com.clj.student.controller;

import com.clj.student.model.dto.BuildingData;
import com.clj.student.model.dto.RoomData;
import com.clj.student.model.po.Room;
import com.clj.student.model.vo.BuildingResponse;
import com.clj.student.model.vo.RoomResponse;
import com.clj.student.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping("buildingList")
    public BuildingResponse buildingList(@RequestParam(required = false) String name) {
        List<BuildingData> buildingDataList = buildingService.buildingList(name);
        return new BuildingResponse(200, "list building successfully!", buildingDataList);
    }

    @PostMapping("building/save")
    public BuildingResponse buildingSave(@RequestBody BuildingData buildingData) {
        BuildingData bd;
        if (buildingData.getId() == null) {
            bd = buildingService.save(buildingData);
        } else {
            bd = buildingService.update(buildingData);
        }
        return new BuildingResponse(200, "save building successfully!", bd);
    }

    @DeleteMapping("building/delete/{id}")
    public BuildingResponse buildingDelete(@PathVariable Long id) {
        buildingService.delete(id);
        return new BuildingResponse(200, "delete building successfully!");
    }

    @GetMapping("roomList")
    public RoomResponse roomList(@RequestParam Long buildingId) {
        List<RoomData> roomDataList = buildingService.roomList(buildingId);
        return new RoomResponse(200, roomDataList, "list room successfully!");
    }

    @PostMapping("room/save")
    public RoomResponse roomSave(@RequestBody Room room) {
        Room r;
        if (room.getId() == null) {
            r = buildingService.save(room);
        } else {
            r = buildingService.update(room);
        }
        return new RoomResponse(200, "save room successfully!", r);
    }

    @DeleteMapping("room/delete/{id}")
    public RoomResponse roomDelete(@PathVariable Long id) {
        buildingService.deleteRoom(id);
        return new RoomResponse(200, "delete room successfully!");
    }
}

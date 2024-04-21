package com.clj.student.controller;

import com.alibaba.excel.EasyExcel;
import com.clj.student.model.dto.BuildingData;
import com.clj.student.model.dto.BuildingRoomData;
import com.clj.student.model.dto.BuildingRoomListener;
import com.clj.student.model.dto.RoomData;
import com.clj.student.model.po.Room;
import com.clj.student.model.vo.BuildingResponse;
import com.clj.student.model.vo.BuildingRoomCallbackResponse;
import com.clj.student.model.vo.BuildingRoomItem;
import com.clj.student.model.vo.BuildingRoomResponse;
import com.clj.student.model.vo.BuildingRoomResponseResult;
import com.clj.student.model.vo.RoomResponse;
import com.clj.student.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    @RequestMapping("building/fileUpload")
    public BuildingRoomResponse buildingFileUpload(@RequestParam("file") MultipartFile files[]) throws IOException {
        BuildingRoomResponse response = new BuildingRoomResponse();
        BuildingRoomItem item = new BuildingRoomItem();
        BuildingRoomCallbackResponse callbackResponse = new BuildingRoomCallbackResponse();
        BuildingRoomResponseResult result = new BuildingRoomResponseResult();
        List<BuildingRoomItem> items = new ArrayList<>();
        if (files.length == 0) {
            callbackResponse.setSuccess(false);
            item.setResponse(callbackResponse);
            items.add(item);
            response.setBuildingRoomItems(items);
            return response;
        }
        InputStream is = files[0].getInputStream();
        buildingService.importDataFromFileUpload(is);
        item.setSize(files[0].getSize());
        item.setName(files[0].getOriginalFilename());
        item.setTempFilePath("");
        item.setFileType("file");
        result.setFileName(files[0].getOriginalFilename());
        result.setFilePath("");
        callbackResponse.setResult(result);
        callbackResponse.setSuccess(true);
        item.setResponse(callbackResponse);
        items.add(item);
        response.setBuildingRoomItems(items);
        return response;
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

    @PostMapping("building/name/check")
    public BuildingResponse checkBuildingName(@RequestBody BuildingData buildingData) {
        BuildingData bd = buildingService.checkName(buildingData);
        return new BuildingResponse(200, "check name successfully!", bd);
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

    @PostMapping("room/name/check")
    public RoomResponse checkRoomName(@RequestBody RoomData roomData) {
        RoomData rd = buildingService.checkRoomName(roomData);
        return new RoomResponse(200, "check name successfully!", rd);
    }
    

    @DeleteMapping("room/delete/{id}")
    public RoomResponse roomDelete(@PathVariable Long id) {
        buildingService.deleteRoom(id);
        return new RoomResponse(200, "delete room successfully!");
    }
}

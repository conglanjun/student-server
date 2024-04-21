package com.clj.student.service;

import com.alibaba.excel.EasyExcel;
import com.clj.student.dao.BuildingRepository;
import com.clj.student.dao.RoomRepository;
import com.clj.student.model.dto.BuildingData;
import com.clj.student.model.dto.BuildingRoomData;
import com.clj.student.model.dto.BuildingRoomListener;
import com.clj.student.model.dto.RoomData;
import com.clj.student.model.po.Building;
import com.clj.student.model.po.Room;
import com.clj.student.utils.ModelConvert;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RoomRepository roomRepository;
    public List<BuildingData> buildingList(String name) {
        List<BuildingData> ret = new ArrayList<>();
        List<Building> buildingList;
        if (Strings.isEmpty(name)) {
            buildingList = buildingRepository.findAllByOrderByCreateTimeDesc();
        } else {
            buildingList = buildingRepository.findByNameOrderByCreateTimeDesc(name);
        }
        for (int i = 0; i < buildingList.size(); i++) {
            Building building = buildingList.get(i);
            BuildingData bd = ModelConvert.BuildingConvertBuildingData(building);
            ret.add(bd);
        }
        return ret;
    }

    public BuildingData save(BuildingData buildingData) {
        Building b = ModelConvert.BuildingDataConvertBuilding(buildingData, new Building());
        b.setCreateTime(Calendar.getInstance().getTime());
        Building save = buildingRepository.save(b);
        return ModelConvert.BuildingConvertBuildingData(save);
    }

    public void delete(Long id) {
        Building b = new Building();
        b.setId(id);
        buildingRepository.delete(b);
    }

    public BuildingData update(BuildingData buildingData) {
        Optional<Building> buildingById = buildingRepository.findById(buildingData.getId());
        if (buildingById.isEmpty()) {
            return null;
        }
        Building b = buildingById.get();
        Building building = ModelConvert.BuildingDataConvertBuilding(buildingData, b);
        Building save = buildingRepository.save(building);
        return ModelConvert.BuildingConvertBuildingData(save);
    }

    public List<RoomData> roomList(Long buildingId) {
        List<RoomData> ret = new ArrayList<>();
        List<Room> roomList = roomRepository.findAllByBuildingId(buildingId);
        for (Room r: roomList) {
            RoomData rd = ModelConvert.RoomConvertRoomData(r);
            ret.add(rd);
        }
        return ret;
    }

    public Room save(Room room) {
        if (room.getBuildingId() != null) {
            Optional<Building> buildingById = buildingRepository.findById(room.getBuildingId());
            buildingById.ifPresent(room::setBuilding);
        }
        room.setCreateTime(Calendar.getInstance().getTime());
        return roomRepository.save(room);
    }

    public Room update(Room room) {
        Optional<Room> roomById = roomRepository.findById(room.getId());
        if (roomById.isEmpty()) {
            return null;
        }
        Room r = roomById.get();
        if (room.getName() != null) {
            r.setName(room.getName());
        }
        if (room.getCreateTime() != null) {
            r.setCreateTime(room.getCreateTime());
        }
        if (room.getDescription() != null) {
            r.setDescription(room.getDescription());
        }
        if (room.getBuilding() != null) {
            r.setBuilding(room.getBuilding());
        }
        return roomRepository.save(r);
    }

    public void deleteRoom(Long id) {
        Room r = new Room();
        r.setId(id);
        roomRepository.delete(r);
    }

    public BuildingData checkName(BuildingData buildingData) {
        BuildingData ret = new BuildingData();
        List<Building> buildings = buildingRepository.findAllByName(buildingData.getName());
        if (buildings != null && buildings.size() > 0) {
            ret.setNameDuplicated(true);
        } else {
            ret.setNameDuplicated(false);
        }
        return ret;
    }

    public RoomData checkRoomName(RoomData roomData) {
        RoomData rd = new RoomData();
        List<Room> rooms = roomRepository.findAllByNameAndBuildingId(roomData.getName(), roomData.getBuildingId());
        if (rooms != null && rooms.size() > 0) {
            rd.setNameDuplicated(true);
        } else {
            rd.setNameDuplicated(false);
        }
        return rd;
    }

    public void importDataFromFileUpload(InputStream is) {
        EasyExcel.read(is, BuildingRoomData.class, new BuildingRoomListener(buildingRepository, roomRepository)).sheet().doRead();
    }
}

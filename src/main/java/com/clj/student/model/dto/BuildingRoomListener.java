package com.clj.student.model.dto;



import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.clj.student.dao.BuildingRepository;
import com.clj.student.dao.RoomRepository;
import com.clj.student.model.po.Building;
import com.clj.student.model.po.Room;

public class BuildingRoomListener extends AnalysisEventListener<BuildingRoomData>{

    @Override
    public void invoke(BuildingRoomData data, AnalysisContext context) {
        String buildingDetail = data.getBuildingDetail();
        String buildingName = data.getBuildingName();
        String roomName = data.getRoomName();
        String roomDetail = data.getRoomDetail();
        List<Building> buildingListByName = buildingRepository.findByNameOrderByCreateTimeDesc(buildingName);
        Building buildingDb;
        if (buildingListByName.isEmpty()) {
            Building building = new Building();
            building.setCreateTime(Calendar.getInstance().getTime());
            building.setName(buildingName);
            building.setDescription(buildingDetail);
            buildingDb = buildingRepository.save(building);
        } else {
            buildingDb = buildingListByName.get(0);
        }
        List<Room> roomListByName = roomRepository.findAllByNameAndBuildingId(roomName, buildingDb.getId());
        if (roomListByName.isEmpty()) {
            Room room = new Room();
            room.setName(roomName);
            room.setDescription(roomDetail);
            room.setCreateTime(Calendar.getInstance().getTime());
            room.setBuilding(buildingDb);
            roomRepository.save(room);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        
    }

    private BuildingRepository buildingRepository;
    private RoomRepository roomRepository;
    public BuildingRoomListener(BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }
    
}

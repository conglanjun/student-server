package com.clj.student.model.vo;

import com.clj.student.model.dto.RoomData;
import com.clj.student.model.po.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {
    private int code;
    private String message;
    private List<Room> roomList;
    private List<RoomData> roomDataList;
    private Room room;

    public RoomResponse(int code, String message, List<Room> roomList) {
        this.code = code;
        this.message = message;
        this.roomList = roomList;
    }

    public RoomResponse(int code, List<RoomData> roomDataList, String message) {
        this.code = code;
        this.message = message;
        this.roomDataList = roomDataList;
    }

    public RoomResponse(int code, String message, Room room) {
        this.code = code;
        this.message = message;
        this.room = room;
    }

    public RoomResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

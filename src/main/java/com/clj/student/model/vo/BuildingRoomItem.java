package com.clj.student.model.vo;

import lombok.Data;

@Data
public class BuildingRoomItem {
    private Long size;
    private String name;
    private String type;
    private String tempFilePath;
    private String fileType;  // 文件类型[image, video, file]
    private BuildingRoomCallbackResponse response;
}




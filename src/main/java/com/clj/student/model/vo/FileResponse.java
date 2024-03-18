package com.clj.student.model.vo;

import lombok.Data;

@Data
public class FileResponse {
    int code;
    String message;
    String filePath;

    public FileResponse(int code, String message, String filePath) {
        this.code = code;
        this.message = message;
        this.filePath = filePath;
    }
}

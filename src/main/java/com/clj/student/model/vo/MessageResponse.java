package com.clj.student.model.vo;

import com.clj.student.model.dto.MessageData;
import lombok.Data;

import java.util.List;

@Data
public class MessageResponse {
    private int code;
    private String message;
    private List<MessageData> messageDataList;
    private MessageData messageData;

    public MessageResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public MessageResponse(int code, String message, List<MessageData> messageDataList) {
        this.code = code;
        this.message = message;
        this.messageDataList = messageDataList;
    }

    public MessageResponse(int code, String message, MessageData messageData) {
        this.code = code;
        this.message = message;
        this.messageData = messageData;
    }
}

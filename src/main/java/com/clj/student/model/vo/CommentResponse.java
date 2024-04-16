package com.clj.student.model.vo;

import java.util.List;

import com.clj.student.model.dto.CommentData;

import lombok.Data;

@Data
public class CommentResponse {
    private int code;
    private String message;
    private List<CommentData> commentDataList;
    private CommentData commentData;

    public CommentResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public CommentResponse(int code, String message, List<CommentData> commentDataList) {
        this.code = code;
        this.message = message;
        this.commentDataList = commentDataList;
    }
    
    public CommentResponse(int code, String message, CommentData commentData) {
        this.code = code;
        this.message = message;
        this.commentData = commentData;
    }
}

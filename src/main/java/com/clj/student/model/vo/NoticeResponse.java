package com.clj.student.model.vo;

import com.clj.student.model.dto.NoticeData;
import com.clj.student.model.po.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoticeResponse {
    private int code;
    private String message;
    private Notice notice;
    private List<NoticeData> noticeList;

    private Long id;

    public NoticeResponse(int code, String message, List<NoticeData> noticeList) {
        this.code = code;
        this.message = message;
        this.noticeList = noticeList;
    }

    public NoticeResponse(int code, String message, Long id) {
        this.code = code;
        this.message = message;
        this.id = id;
    }
    public NoticeResponse(int code, String message, Notice notice) {
        this.code = code;
        this.message = message;
        this.notice = notice;
    }
}

package com.clj.student.controller;

import com.clj.student.model.po.Notice;
import com.clj.student.model.dto.NoticeData;
import com.clj.student.model.dto.NoticeRequest;
import com.clj.student.model.vo.NoticeResponse;
import com.clj.student.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("noticeList")
    public NoticeResponse noticeList() {
        List<NoticeData> noticeList = noticeService.noticeList();
        return new NoticeResponse(200, "list notice successfully!", noticeList);
    }

    @PostMapping("notice/update")
    public NoticeResponse noticeUpdate(@RequestBody NoticeRequest noticeRequest) {
        Notice notice = noticeService.updateNotice(noticeRequest);
        if (notice == null) {
            return new NoticeResponse(-1, "update notice failed!", notice);
        }
        return new NoticeResponse(200, "update notice successfully!", notice);
    }

    @DeleteMapping("notice/delete/{id}")
    public NoticeResponse noticeDelete(@PathVariable Long id) {
        noticeService.noticeDelete(id);
        return new NoticeResponse(200, "delete notice successfully!", 0L);
    }
}

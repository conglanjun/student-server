package com.clj.student.service;

import com.clj.student.dao.NoticeRepository;
import com.clj.student.model.po.Notice;
import com.clj.student.model.dto.NoticeData;
import com.clj.student.model.dto.NoticeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Component
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    private final Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<NoticeData> noticeList() {
        List<NoticeData> res = new ArrayList<>();
        List<Notice> noticeList = noticeRepository.findAll();
        for (Notice n: noticeList) {
            NoticeData nd = new NoticeData();
            if (n.getText() != null && n.getText().length() > 10) {
                nd.setDisplayText(n.getText().substring(0, 10) + "...");
            } else {
                nd.setDisplayText(n.getText());
            }
            nd.setText(n.getText());
            nd.setId(n.getId());
            nd.setCreateTime(n.getCreateTime());
            String formatCreateTime = format.format(n.getCreateTime());
            nd.setDisplayCreateTime(formatCreateTime);
            nd.setImageAddress(n.getImageAddress());
            res.add(nd);
        }
        return res;
    }

    public Notice updateNotice(NoticeRequest noticeRequest) {
        Notice n = new Notice();
        n.setText(noticeRequest.getText());
        if (noticeRequest.getId() !=null && noticeRequest.getId() > 0) {
            n.setId(noticeRequest.getId());
            n.setCreateTime(noticeRequest.getCreateTime());
        } else {
            n.setCreateTime(Calendar.getInstance().getTime());
        }
        if (noticeRequest.getImageAddress() != null) {
            n.setImageAddress(noticeRequest.getImageAddress());
        }
        return noticeRepository.save(n);
    }

    public void noticeDelete(Long id) {
        Notice n = new Notice();
        n.setId(id);
        noticeRepository.delete(n);
    }
}

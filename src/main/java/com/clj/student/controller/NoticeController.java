package com.clj.student.controller;

import com.clj.student.model.po.Notice;
import com.clj.student.model.dto.NoticeData;
import com.clj.student.model.dto.NoticeRequest;
import com.clj.student.model.vo.NoticeResponse;
import com.clj.student.service.NoticeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Slf4j
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

    @PostMapping("fileUpload")
    public String fileUpload(HttpServletRequest httpServletRequest, @RequestParam(value = "file") MultipartFile file) throws URISyntaxException {
        if (file.isEmpty()) {
            log.error("file upload error!");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "images/";
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest.getAbsoluteFile());
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/" + fileName;
    }
    
    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }
}

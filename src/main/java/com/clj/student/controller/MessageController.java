package com.clj.student.controller;

import com.clj.student.model.dto.CommentData;
import com.clj.student.model.dto.MessageData;
import com.clj.student.model.vo.CommentResponse;
import com.clj.student.model.vo.MessageResponse;
import com.clj.student.service.MessageService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@RequestMapping("api")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("messageList")
    public MessageResponse messageList() {
        List<MessageData> messageDataList = messageService.messageList();
        return new MessageResponse(200, "list message successfully!", messageDataList);
    }

    @GetMapping("message/get/{id}")
    public MessageResponse messageGet(@PathVariable Long id) {
        MessageData messageData = messageService.messageGet(id);
        return new MessageResponse(200, "get message successfully!", messageData);
    }

    @PostMapping("message/save")
    public MessageResponse messageSave(@RequestBody MessageData messageData) {
        MessageData md;
        if (messageData.getId() == null) {
            md = messageService.save(messageData);
        } else {
            md = messageService.update(messageData);
        }
        return new MessageResponse(200, "save message successfully!", md);
    }

    @DeleteMapping("message/delete/{id}")
    public MessageResponse messageDelete(@PathVariable Long id) {
        messageService.delete(id);
        return new MessageResponse(200, "delete message successfully!");
    }

    @PostMapping("comment/save")
    public CommentResponse commentSave(@RequestBody CommentData commentData) {
        CommentData data = messageService.commentSave(commentData);
        return new CommentResponse(200, "create comment successfully!", data);
    }

    @GetMapping("comment/list")
    public CommentResponse commentList(@RequestParam Long messageId) {
        return new CommentResponse(200, "create comment successfully!", new ArrayList<>());
    }
    
    
}

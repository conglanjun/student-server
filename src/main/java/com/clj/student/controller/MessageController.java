package com.clj.student.controller;

import com.clj.student.model.dto.MessageData;
import com.clj.student.model.po.Message;
import com.clj.student.model.vo.MessageResponse;
import com.clj.student.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new MessageResponse(200, "delete message successfuuly!");
    }
}

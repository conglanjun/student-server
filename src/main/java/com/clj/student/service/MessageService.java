package com.clj.student.service;

import com.clj.student.dao.CommentRepository;
import com.clj.student.dao.MessageRepository;
import com.clj.student.dao.UserRepository;
import com.clj.student.model.dto.CommentData;
import com.clj.student.model.dto.MessageData;
import com.clj.student.model.po.Comment;
import com.clj.student.model.po.Message;
import com.clj.student.model.po.User;
import com.clj.student.utils.ModelConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<MessageData> messageList() {
        List<MessageData> ret = new ArrayList<>();
        List<Message> messageList = messageRepository.findAll();
        for (Message m: messageList) {
            MessageData md = ModelConvert.MessageConvertMessageData(m);
            ret.add(md);
        }
        return ret;
    }

    public MessageData save(MessageData messageData) {
        if (messageData.getUserId() != null) {
            Optional<User> userById = userRepository.findById(messageData.getUserId());
            userById.ifPresent(messageData::setUser);
        }
        messageData.setCreateTime(Calendar.getInstance().getTime());
        Message message = ModelConvert.MessageDataConvertMessage(messageData);
        Message save = messageRepository.save(message);
        return ModelConvert.MessageConvertMessageData(save);
    }

    public MessageData update(MessageData messageData) {
        if (messageData.getUserId() != null) {
            Optional<User> userById = userRepository.findById(messageData.getUserId());
            userById.ifPresent(messageData::setUser);
        }
        Message message = ModelConvert.MessageDataConvertMessage(messageData);
        Message save = messageRepository.save(message);
        return ModelConvert.MessageConvertMessageData(save);
    }

    public void delete(Long id) {
        Message m = new Message();
        m.setId(id);
        messageRepository.delete(m);
    }

    public MessageData messageGet(Long id) {
        Optional<Message> messageById = messageRepository.findById(id);
        if (messageById.isEmpty()) {
            return null;
        }
        Message message = messageById.get();
        return ModelConvert.MessageConvertMessageData(message);
    }

    public CommentData commentSave(CommentData commentData) {
        commentData.setCreateTime(Calendar.getInstance().getTime());
        commentData.setUpdateTime(Calendar.getInstance().getTime());
        if (commentData.getArticleId() != null) {
            Optional<Message> messageOptional = messageRepository.findById(Long.valueOf(commentData.getArticleId()));
            messageOptional.ifPresent(commentData::setMessage);
        }
        if (commentData.getCommentUserId() != null) {
            Optional<User> userOptional = userRepository.findById(commentData.getCommentUserId());
            userOptional.ifPresent(commentData::setCommentUser);
        }
        if (commentData.getParentId() != null) {
            Optional<Comment> comOptional = commentRepository.findById(commentData.getParentId());
            comOptional.ifPresent(commentData::setComment);
        }
        Comment comment = ModelConvert.CommentDataConvertComment(commentData);
        Comment save = commentRepository.save(comment);
        return ModelConvert.CommentConvertCommentData(save);
    }
}

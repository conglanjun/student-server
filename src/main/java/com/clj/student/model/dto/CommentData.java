package com.clj.student.model.dto;

import lombok.Data;
import java.util.Date;

import com.clj.student.model.po.Comment;
import com.clj.student.model.po.Message;
import com.clj.student.model.po.User;

@Data
public class CommentData {
    private Long id;
    private String articleId;
    private Message message;
    private Long commentUserId;
    private User commentUser;
    private Long parentId;
    private Comment comment;
    private String content;
    private String like;
    private Integer status; // 状态，0-未审核，1-展现，2-审核驳回，3-已删除
    private Date createTime;
    private String displayCreateTime;
    private Date updateTime;
    private String displayUpdateTime;
    private String nickName;
    private boolean owner;
    private boolean hasLike;
    private Integer likeNum;
}

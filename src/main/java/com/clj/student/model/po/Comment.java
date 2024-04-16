package com.clj.student.model.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(insertable = false,updatable = false)
    private String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    @JsonIgnore
    private Message message;
    @Column(insertable = false,updatable = false)
    private Long commentUserId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentUserId")
    @JsonIgnore
    private User commentUser;
    @Column(insertable = false,updatable = false)
    private Long parentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private Comment comment;
    private String content;
    @Column(name = "`like`")
    private String like;
    private Integer status; // 状态，0-未审核，1-展现，2-审核驳回，3-已删除
    private Date createTime;
    private Date updateTime;
}

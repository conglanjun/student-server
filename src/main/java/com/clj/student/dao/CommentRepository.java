package com.clj.student.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clj.student.model.po.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(String messageId);
}

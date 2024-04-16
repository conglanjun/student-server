package com.clj.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clj.student.model.po.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}

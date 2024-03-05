package com.clj.student.model.dto;

import com.clj.student.model.po.Student;
import com.clj.student.service.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentServiceResult {
    private Student student;
    private UserStatus status;
}

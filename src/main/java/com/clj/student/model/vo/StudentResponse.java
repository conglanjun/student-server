package com.clj.student.model.vo;

import com.clj.student.model.dto.StudentServiceResult;
import com.clj.student.model.po.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentResponse {
    private int code;
    private String message;
    private Long id;
    private List<Student> studentList;
    private Student student;
    private StudentServiceResult studentServiceResult;

    public StudentResponse(int code, String message, Long id) {
        this.code = code;
        this.message = message;
        this.id = id;
    }

    public StudentResponse(int code, String message, List<Student> studentList) {
        this.code = code;
        this.message = message;
        this.studentList = studentList;
    }

    public StudentResponse(int code, String message, Student student) {
        this.code = code;
        this.message = message;
        this.student = student;
    }

    public StudentResponse(int code, String message, StudentServiceResult studentServiceResult) {
        this.code = code;
        this.message = message;
        this.studentServiceResult = studentServiceResult;
    }
}

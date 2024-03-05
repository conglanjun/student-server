package com.clj.student.controller;

import com.clj.student.dao.UserRepository;
import com.clj.student.model.dto.StudentRequest;
import com.clj.student.model.vo.StudentResponse;
import com.clj.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api1")
public class StudentController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("queryAll")
    public StudentResponse queryAll() {
        // query db
        return new StudentResponse(200, "get student list successfully!", 0L);
    }

    // save student
    @PostMapping("save/student")
    public StudentResponse saveStudent(@RequestBody StudentRequest student) {
//        if (Strings.isEmpty(student.getPhone())) {
//            return new StudentResponse(400, "电话号空，请输入电话号！", 0L);
//        }
//        List<Student> studentList = studentService.studentByPhone(student);
//        if (studentList.size() > 0) {
//            return new StudentResponse(400, "电话号已存在！", 0L);
//        }
//        StudentServiceResult save = studentService.save(student);
//        return new StudentResponse(200, "save student successfully!", save.getStudent());
        return null;
    }

    @PostMapping("login")
    public StudentResponse login(@RequestBody StudentRequest student) {
//        log.info("login student:" + student);
//        StudentServiceResult loginResult = studentService.login(student);
//        return new StudentResponse(200, "login handle successfully!", loginResult);
        return null;
    }

}
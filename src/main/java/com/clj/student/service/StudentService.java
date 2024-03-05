package com.clj.student.service;

import com.clj.student.dao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentService {

    @Autowired
    private UserRepository userRepository;

//    public StudentServiceResult login(StudentRequest student){
//        List<Student> studentListByPhone = studentByPhone(student);
//        if (studentListByPhone.size() == 0) {
//            return new StudentServiceResult(null, UserStatus.NOTLOGIN);
//        }
//        Student ret = studentListByPhone.get(0);
//        if (!Secret.md5(student.getPassword()).equals(ret.getPassword())) {
//            return  new StudentServiceResult(null, UserStatus.INCORRECTPASSWORD);
//        }
//        return new StudentServiceResult(ret, UserStatus.LOGIN);
//    }

//    public StudentServiceResult save(StudentRequest student) {
//        student.setCreateTime(Calendar.getInstance().getTime());
//        Student studentEntity = ModelConvert.StudentRequestConvertStudent(student);
//        String secret = Secret.md5(studentEntity.getPassword());
//        studentEntity.setPassword(secret);
//        Student save = userRepository.save(studentEntity);
//        log.info("saved student:" + save);
//        return new StudentServiceResult(save, UserStatus.SAVED);
//    }
//
//    public List<Student> studentByPhone(StudentRequest studentRequest) {
//        return userRepository.findAllByPhone(studentRequest.getPhone());
//    }
}


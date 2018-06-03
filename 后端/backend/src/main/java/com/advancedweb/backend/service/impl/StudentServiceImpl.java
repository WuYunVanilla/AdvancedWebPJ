package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.repository.StudentRepository;
import com.advancedweb.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Student")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public void save(Student teacher) {
        studentRepository.save(teacher);
    }
//    @Override
//    public void saveStudyIn(String name, String course_id) {
//        studentRepository.saveStudyIn(name, course_id);
//    }
}

package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserTempRepository userTempRepository;

    public boolean findUser(String name) {
        Student stu = studentRepository.findByName(name);
        Teacher tea = teacherRepository.findByName(name);

        return (stu != null || tea != null);
    }

    public boolean findTemp(String name) {
        UserTemp userTemp = userTempRepository.findByUser_name(name);
        return (userTemp != null);
    }

    public Teacher findTeacherByName(String name) {
        return teacherRepository.findByName(name);
    }

    public Student findStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void saveUserTemp(UserTemp userTemp) {
        userTempRepository.save(userTemp);
    }

    public UserTemp findUserByName(String name) {
        return userTempRepository.findByUser_name(name);
    }

    public void deleteUser(UserTemp userTemp) {
        userTempRepository.delete(userTemp);
    }

    public Course[] getStudentCourses(long id) {
        return studentRepository.findCourses(id);
    }

    public Course[] getTeacherCourses(long id) {
        return teacherRepository.findCourses(id);
    }

    public Note[] getStudentNotes(long id){return studentRepository.findNotes(id);}
}

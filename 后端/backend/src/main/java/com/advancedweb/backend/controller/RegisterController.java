package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.controller.json_model.User;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.repository.StudentRepository;
import com.advancedweb.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private StudentRepository sr;

    @Autowired
    private TeacherRepository tr;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Success register(@RequestBody User user) {

        String name = user.getUser_name();
        String password = user.getUser_pwd();
        String identity = user.getIdentity();

        //首先判断user_name是否已经存在
        boolean if_exist = false;
        Student stu = sr.findByName(name);
        Teacher tea = tr.findByName(name);
        if (stu != null || tea != null) {
            if_exist = true;
        }


        else {
            switch (identity) {
                case "student":
                    Student student_new = new Student();
                    student_new.setName(name);
                    student_new.setPassword(password);
                    sr.save(student_new);
                    break;
                case "teacher":
                    Teacher teacher_new = new Teacher();
                    teacher_new.setName(name);
                    teacher_new.setPassword(password);
                    tr.save(teacher_new);
                    break;
            }
        }

        Success s = new Success();
        s.setSuccess(!if_exist);
        return s;
    }
}


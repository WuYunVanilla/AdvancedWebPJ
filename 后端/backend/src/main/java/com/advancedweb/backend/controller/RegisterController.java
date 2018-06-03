package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.controller.json_model.User;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.service.impl.StudentServiceImpl;
import com.advancedweb.backend.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private StudentServiceImpl ssl;

    @Autowired
    private TeacherServiceImpl tsl;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Success register(@RequestBody User user) {

        String name = user.getUser_name();
        String password = user.getUser_pwd();
        String identity = user.getIdentity();

        //首先判断user_name是否已经存在
        boolean if_exist = false;
        Student stu = ssl.findByName(name);
        Teacher tea = tsl.findByName(name);
        if (stu != null || tea != null) {
            if_exist = true;
        }


        else {
            switch (identity) {
                case "student":
                    Student student_new = new Student();
                    student_new.setName(name);
                    student_new.setPassword(password);
                    ssl.save(student_new);
                    break;
                case "teacher":
                    Teacher teacher_new = new Teacher();
                    teacher_new.setName(name);
                    teacher_new.setPassword(password);
                    tsl.save(teacher_new);
                    break;
            }
        }

        Success s = new Success();
        s.setSuccess(!if_exist);
        return s;
    }
}


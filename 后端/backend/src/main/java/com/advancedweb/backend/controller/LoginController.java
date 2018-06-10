package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.controller.json_model.User;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.repository.StudentRepository;
import com.advancedweb.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private StudentRepository sr;

    @Autowired
    private TeacherRepository tr;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Success login(@RequestBody User user) {

        Success s = new Success();
        s.setSuccess(false);

        String name = user.getUser_name();
        String password = user.getUser_pwd();
        String identity = user.getIdentity();

        //首先判断user_name是否已经存在
        if (identity.equals("teacher")) {
            Teacher tea = tr.findByName(name);
            if (tea != null) {
                if (tea.getPassword().equals(password))
                    s.setSuccess(true);

            }
        } else if (identity.equals("student")) {
            Student stu = sr.findByName(name);
            //再判断密码是否一致
            if (stu != null) {
                if (stu.getPassword().equals(password))
                    s.setSuccess(true);
            }
        }

        return s;
    }
}

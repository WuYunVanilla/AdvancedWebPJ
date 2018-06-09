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
public class ModifyPasswordController {
    @Autowired
    private StudentRepository sr;

    @Autowired
    private TeacherRepository tr;


    @RequestMapping(value = "/modify_password", method = RequestMethod.POST)
    public Success modifyPassword(@RequestBody User user) {

        String name = user.getUser_name();
        String password = user.getUser_pwd();

        //首先判断user_name是否已经存在
        boolean if_exist = false;
        Student stu = sr.findByName(name);
        Teacher tea = tr.findByName(name);
        if (stu != null || tea != null) {
            if_exist = true;
        }


        if (stu != null) {
            stu.setPassword(password);
            sr.save(stu);
        }

        if (tea != null) {
            tea.setPassword(password);
            tr.save(tea);
        }

        Success s = new Success();
        s.setSuccess(if_exist);
        return s;
    }
}

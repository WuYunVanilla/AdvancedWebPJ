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
public class LoginController {
    @Autowired
    private StudentServiceImpl ssl;

    @Autowired
    private TeacherServiceImpl tsl;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Success register(@RequestBody User user) {
        Success s = new Success();
        s.setSuccess(false);

        String name = user.getUser_name();
        String password = user.getUser_pwd();

        //首先判断user_name是否已经存在

        Student stu = ssl.findByName(name);
        Teacher tea = tsl.findByName(name);
        if (stu == null && tea == null) {
            return s;
        }

        //再判断密码是否一致
        if (stu!=null){
            if (stu.getPassword().equals(password))
                s.setSuccess(true);

        }

        if (tea!=null){
            if (tea.getPassword().equals(password))
                s.setSuccess(true);
        }
        return s;
    }
}

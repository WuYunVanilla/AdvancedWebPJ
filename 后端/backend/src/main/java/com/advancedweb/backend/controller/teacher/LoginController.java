package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Identity;
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
    public Identity login(@RequestBody User user) {

        Identity identity = new Identity();
        identity.setIdentity("illegal");

        String name = user.getUser_name();
        String password = user.getUser_pwd();

        //首先判断user_name是否已经存在

        Student stu = sr.findByName(name);
        Teacher tea = tr.findByName(name);
        if (stu == null && tea == null) {
            return identity;
        }

        //再判断密码是否一致
        if (stu!=null){
            if (stu.getPassword().equals(password))
                identity.setIdentity("student");
        }

        if (tea!=null){
            if (tea.getPassword().equals(password))
                identity.setIdentity("teacher");
        }
        return identity;
    }
}

package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.controller.json_model.User;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.model.UserTemp;
import com.advancedweb.backend.service.UserService;
import com.advancedweb.backend.service.mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private mailService mailService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Success login(@RequestBody User user) {

        Success s = new Success();
        s.setSuccess(false);

        String name = user.getUser_name();
        String password = user.getUser_pwd();
        String identity = user.getIdentity();

        //首先判断user_name是否已经存在
        if (identity.equals("teacher")) {
            Teacher tea = userService.findTeacherByName(name);
            //再判断密码是否一致
            if (tea != null) {
                if (tea.getPassword().equals(password))
                    s.setSuccess(true);
            }
        } else if (identity.equals("student")) {
            Student stu = userService.findStudentByName(name);
            //再判断密码是否一致
            if (stu != null) {
                if (stu.getPassword().equals(password))
                    s.setSuccess(true);
            }
        }

        return s;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Success register(@RequestBody UserTemp user) {
        String name = user.getUser_name();
        String password = user.getUser_pwd();
        String email = user.getEmail();
        String identity = user.getIdentity();

        //首先判断user_name是否已经存在
        boolean if_exist = (userService.findUser(name) || userService.findTemp(name));
        if (!if_exist) {
            Long curTime = System.currentTimeMillis();
            int hash = curTime.hashCode() % 100000000 + 100000000;
            String code = hash + "";

            user.setCode(code);
            userService.saveUserTemp(user);

            // 标题
            String subject = "登录验证码";
            // 正文
            StringBuilder builder = new StringBuilder();
            builder.append("<html><head>");
            builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            builder.append("</head><body>");
            builder.append("您好：<br/>");
            builder.append("您在课程网站上进行了注册，账户信息如下：<br/>");
            builder.append("用户账号：" + name + "<br/>用户密码：" + password + "<br/>");
            builder.append("用户权限：" + identity + "<br/><br/>");
            builder.append("您的邮箱验证码为：" + code);
            builder.append("</body></html>");
            String htmlContent = builder.toString();

            mailService.sendHtmlMail(email, subject, htmlContent);
        }

        Success s = new Success();
        s.setSuccess(!if_exist);
        return s;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Success transfer(@RequestBody UserTemp user) {
        String name = user.getUser_name();
        String password = user.getUser_pwd();
        String email = user.getEmail();
        String identity = user.getIdentity();
        String code = user.getCode();

        Success s = new Success();
        s.setSuccess(false);

        UserTemp temp = userService.findUserByName(name);
        if (temp == null) {
            return s;
        } else {
            if (!(temp.getUser_name().equals(name) && temp.getUser_pwd().equals(password) && temp.getEmail().equals(email) && temp.getIdentity().equals(identity) && temp.getCode().equals(code)))
                return s;
            else {
                userService.deleteUser(temp);
                if (identity.equalsIgnoreCase("teacher")) {
                    Teacher teacher = new Teacher();
                    teacher.setName(name);
                    teacher.setPassword(password);
                    userService.saveTeacher(teacher);
                } else if (identity.equalsIgnoreCase("student")) {
                    Student student = new Student();
                    student.setName(name);
                    student.setPassword(password);
                    userService.saveStudent(student);
                } else {
                    return s;
                }
                s.setSuccess(true);
            }
        }

        return s;
    }

    @RequestMapping(value = "/modify_password", method = RequestMethod.POST)
    public Success modifyPassword(@RequestBody UserTemp user) {

        String name = user.getUser_name();
        String password = user.getUser_pwd();

        //首先判断user_name是否已经存在
        boolean if_exist = userService.findUser(name);

        Student stu = userService.findStudentByName(name);
        Teacher tea = userService.findTeacherByName(name);

        if (stu != null) {
            stu.setPassword(password);
            userService.saveStudent(stu);
        }

        if (tea != null) {
            tea.setPassword(password);
            userService.saveTeacher(tea);
        }

        Success s = new Success();
        s.setSuccess(if_exist);
        return s;
    }
}

package com.advancedweb.backend.controller;

import com.advancedweb.backend.model.node.*;
import com.advancedweb.backend.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private TeacherServiceImpl teacherService;

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(@RequestParam String name, @RequestParam String password) {
        if (teacherService.findByName(name) != null) {
            return "exist!";
        } else {
            Teacher temp = new Teacher();
            temp.setName(name);
            temp.setPassword(password);
            teacherService.save(temp);
            return temp.getName();
        }
    }
}

package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;

    @Relationship(type = "TEACH_IN", direction = Relationship.UNDIRECTED)
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    public void teachIn(Course course) {
        if (courses == null) {
            courses = new HashSet<>();
        }
        courses.add(course);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

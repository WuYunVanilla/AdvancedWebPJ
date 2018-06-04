package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Course")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "course_id")
    private String course_id;
    @Property(name = "course_name")
    private String course_name;
    @Property(name = "course_number")
    private String course_number;

    @Relationship(type = "OWN")
    private Set<Mindmap> mindmaps;

    public Set<Mindmap> getMindmaps() {
        return mindmaps;
    }

    public void owns(Mindmap mindmap) {
        if (mindmaps == null) {
            mindmaps = new HashSet<>();
        }
        mindmaps.add(mindmap);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }
}

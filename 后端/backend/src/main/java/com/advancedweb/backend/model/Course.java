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
    private String courseId;
    @Property(name = "course_name")
    private String name;
    @Property(name = "course_number")
    private String number;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

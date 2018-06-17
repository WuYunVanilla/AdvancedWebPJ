package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity(label = "Node")
public class Node {
    @Id
    @GeneratedValue
    private Long long_id;

    private String course_mindmap;
    @Property(name = "node_id")
    private String id;
    private String topic;
    private String background_color;
    private String foreground_color;

    private Boolean expanded;
    private String direction;

    @Relationship(type = "HAS_CHILD")
    private List<Node> children;

    @Relationship(type = "HAS_MATERIAL")
    private Set<Material> materials;
    @Relationship(type = "HAS_COURSEWARE")
    private Set<Courseware> coursewares;
    @Relationship(type = "HAS_LINK")
    private Set<Link> links;
    @Relationship(type = "HAS_ASSIGNMENT_MULTI")
    private Set<AssignmentMultiple> assignmentMultiples;
    @Relationship(type = "HAS_ASSIGNMENT_SHORT")
    private Set<AssignmentShort> assignmentShorts;

    @Relationship(type = "HAS_NOTE")
    private Set<Note> notes;

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public void saveNote(Note note) {
        if (notes == null) {
            notes = new HashSet<>();
        }
        notes.add(note);
    }


    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterial(Material material) {
        if (materials == null) {
            materials = new HashSet<>();
        }
        materials.add(material);
    }

    public Set<Courseware> getCoursewares() {
        return coursewares;
    }

    public void setCourseware(Courseware courseware) {
        if (coursewares == null) {
            coursewares = new HashSet<>();
        }
        coursewares.add(courseware);
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLink(Link link) {
        if (links == null) {
            links = new HashSet<>();
        }
        links.add(link);
    }

    public Set<AssignmentMultiple> getAssignmentMultiples() {
        return assignmentMultiples;
    }

    public void setAssignmentMultiple(AssignmentMultiple assignmentMultiple) {
        if (assignmentMultiples == null) {
            assignmentMultiples = new HashSet<>();
        }
        assignmentMultiples.add(assignmentMultiple);
    }

    public Set<AssignmentShort> getAssignmentShorts() {
        return assignmentShorts;
    }

    public void setAssignmentShorts(AssignmentShort assignmentShort) {
        if (assignmentShorts == null) {
            assignmentShorts = new HashSet<>();
        }
        assignmentShorts.add(assignmentShort);
    }

    public Long getLong_id() {
        return long_id;
    }

    public void setLong_id(Long long_id) {
        this.long_id = long_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getForeground_color() {
        return foreground_color;
    }

    public void setForeground_color(String foreground_color) {
        this.foreground_color = foreground_color;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }


    public String getCourse_mindmap() {
        return course_mindmap;
    }

    public void setCourse_mindmap(String course_mindmap) {
        this.course_mindmap = course_mindmap;
    }
}

package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Courseware")
public class Courseware {
    @Id
    @GeneratedValue
    private Long id;
    private String courseware_name;
    private String store_address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseware_name() {
        return courseware_name;
    }

    public void setCourseware_name(String courseware_name) {
        this.courseware_name = courseware_name;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }
}

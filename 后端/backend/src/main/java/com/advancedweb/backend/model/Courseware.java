package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Courseware")
public class Courseware {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "courseware_name")
    private String coursewareName;
    @Property(name = "store_address")
    private String storeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}

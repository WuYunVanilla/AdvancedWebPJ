package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Link")
public class Link {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "link_address")
    private String linkAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }
}

package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Note")
public class Note {
    @Id
    @GeneratedValue
    private Long long_id;

    private String title;
    private String content;
    private String access;

    
}

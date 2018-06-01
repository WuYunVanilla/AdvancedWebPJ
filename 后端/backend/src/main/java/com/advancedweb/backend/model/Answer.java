package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Answer")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "answer_id")
    private String answerId;
    @Property(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

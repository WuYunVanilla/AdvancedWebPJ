package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Mindmap")
public class Mindmap {
    @Id
    @GeneratedValue
    private Long id;

    private String mindmap_name;
    private String mindmap_id;
    private String json_string;

    @Relationship(type = "HAS_ROOT")
    private Set<Node> nodes;

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setRootNode(Node node) {
        if (nodes == null) {
            nodes = new HashSet<>();
        }
        nodes.add(node);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMindmap_name() {
        return mindmap_name;
    }

    public void setMindmap_name(String mindmap_name) {
        this.mindmap_name = mindmap_name;
    }

    public String getMindmap_id() {
        return mindmap_id;
    }

    public void setMindmap_id(String mindmap_id) {
        this.mindmap_id = mindmap_id;
    }

    public String getJson_string() {
        return json_string;
    }

    public void setJson_string(String json_string) {
        this.json_string = json_string;
    }
}

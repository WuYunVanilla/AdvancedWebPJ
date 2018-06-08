package com.advancedweb.backend.controller.json_model;

import java.util.List;

public class Node_json {
    private String node_color;
    private String node_name;
    private String node_name_color;
    private List<Node_json> node_children;

    public String getNode_color() {
        return node_color;
    }

    public void setNode_color(String node_color) {
        this.node_color = node_color;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNode_name_color() {
        return node_name_color;
    }

    public void setNode_name_color(String node_name_color) {
        this.node_name_color = node_name_color;
    }

    public List<Node_json> getNode_children() {
        return node_children;
    }

    public void setNode_children(List<Node_json> node_children) {
        this.node_children = node_children;
    }
}

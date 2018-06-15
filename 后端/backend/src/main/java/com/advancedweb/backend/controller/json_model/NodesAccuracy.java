package com.advancedweb.backend.controller.json_model;

public class NodesAccuracy {
    private String node_topic;
    private double accuracy;

    public String getNode_topic() {
        return node_topic;
    }

    public void setNode_topic(String node_topic) {
        this.node_topic = node_topic;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}


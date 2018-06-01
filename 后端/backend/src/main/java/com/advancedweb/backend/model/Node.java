package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Node")
public class Node {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "node_id")
    private String nodeId;
    @Property(name = "node_color")
    private String nodeColor;
    @Property(name = "node_name")
    private String nodeName;
    @Property(name = "node_name_color")
    private String nodeNameColor;

    @Relationship(type = "HAS_CHILD")
    private Set<Node> childNodes;
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

    public Set<Node> getChildNodes() {
        return childNodes;
    }

    public void setChild(Node node) {
        if (childNodes == null) {
            childNodes = new HashSet<>();
        }
        childNodes.add(node);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(String nodeColor) {
        this.nodeColor = nodeColor;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeNameColor() {
        return nodeNameColor;
    }

    public void setNodeNameColor(String nodeNameColor) {
        this.nodeNameColor = nodeNameColor;
    }
}

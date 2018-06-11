package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;

    public Node findByNodeId(String course_mindmap, String nodeId) {
        return nodeRepository.findByNodeId(course_mindmap, nodeId);
    }

    public Courseware[] findCoursewares(long id) {
        return nodeRepository.findCoursewares(id);
    }

    public Material[] findMaterials(long id) {
        return nodeRepository.findMaterials(id);
    }

    public Link[] findLinks(long id) {
        return nodeRepository.findLinks(id);
    }

    public AssignmentMultiple[] findAssignmentMultiple(long id) {
        return nodeRepository.findAssignmentMultiple(id);
    }

    public AssignmentShort[] findAssignmentShort(long id) {
        return nodeRepository.findAssignmentShort(id);
    }

    public void delete(Node node) {
        nodeRepository.delete(node);
    }

    public void save(Node node) {
        nodeRepository.save(node);
    }
}

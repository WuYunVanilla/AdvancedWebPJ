package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.NodeRepository;
import com.advancedweb.backend.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Node")
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public Node findByNodeId(String node_id) {
        return nodeRepository.findByNodeId(node_id);
    }

    @Override
    public void save(Node node) {
        nodeRepository.save(node);
    }
}

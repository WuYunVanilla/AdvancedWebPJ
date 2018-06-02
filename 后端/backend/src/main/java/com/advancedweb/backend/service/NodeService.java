package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface NodeService {
    Node findByNodeId(String node_id);
    void save(Node node);
}

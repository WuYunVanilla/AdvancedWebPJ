package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface MindmapService {
    Mindmap findByMindmap_id(int mindmap_id);
    void save(Mindmap mindmap);
}

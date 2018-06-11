package com.advancedweb.backend.service;

import com.advancedweb.backend.model.Mindmap;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.MindmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MindmapService {
    @Autowired
    private MindmapRepository mindmapRepository;

    public Mindmap findByMindmapId(String id) {
        return mindmapRepository.findByMindmap_id(id);
    }

    public Node findRootNode(long id) {
        return mindmapRepository.findRootNode(id);
    }

    public void delete(Mindmap mindmap) {
        mindmapRepository.delete(mindmap);
    }

    public void save(Mindmap mindmap) {
        mindmapRepository.save(mindmap);
    }
}

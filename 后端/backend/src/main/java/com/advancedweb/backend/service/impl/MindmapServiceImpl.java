package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.MindmapRepository;
import com.advancedweb.backend.service.MindmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Mindmap")
public class MindmapServiceImpl implements MindmapService {
    @Autowired
    private MindmapRepository mindmapRepository;

    @Override
    public Mindmap findByMindmap_id(int mindmap_id) {
        return mindmapRepository.findByMindmap_id(mindmap_id);
    }

    @Override
    public void save(Mindmap mindmap) {
        mindmapRepository.save(mindmap);
    }
}

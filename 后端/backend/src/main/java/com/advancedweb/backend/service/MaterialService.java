package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface MaterialService {
    Material findByMaterialName(String material_name);
    void save(Material material);
}

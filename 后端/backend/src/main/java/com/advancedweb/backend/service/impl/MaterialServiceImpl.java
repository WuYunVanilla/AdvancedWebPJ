package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.Material;
import com.advancedweb.backend.repository.MaterialRepository;
import com.advancedweb.backend.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Material")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Material findByMaterialName(String material_name) {
        return materialRepository.findByMaterialName(material_name);
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }
}

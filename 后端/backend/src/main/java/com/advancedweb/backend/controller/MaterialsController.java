package com.advancedweb.backend.controller;

import com.advancedweb.backend.model.Material;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaterialsController {
    @Autowired
    private NodeRepository nr;

    @RequestMapping(value = "/materials/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public MaterialName[] materials(@PathVariable String course_id, @PathVariable String mindmap_id,
                                                              @PathVariable String node_id) {

        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);
        Material[] materials = nr.findMaterials(result_node.getLong_id());

        MaterialName[] materialNames= new MaterialName[materials.length];

        for (int i=0;i<materials.length;i++){
            materialNames[i] = new MaterialName();
            materialNames[i].setMaterial_name(materials[i].getMaterialName());
        }

        return materialNames;
    }


    private class MaterialName {
        private String material_name;

        public String getMaterial_name() {
            return material_name;
        }

        public void setMaterial_name(String material_name) {
            this.material_name = material_name;
        }
    }

}



package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Material")
public class Material {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "material_name")
    private String materialName;
    @Property(name = "store_address")
    private String storeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}

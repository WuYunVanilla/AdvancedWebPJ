package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface MaterialRepository extends Neo4jRepository<Material, Long> {
    @Query("MATCH (n:Material) WHERE n.material_name = ({material_name}) RETURN n")
    Material findByMaterialName(@Param("material_name") String material_name);

}

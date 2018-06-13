package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface MaterialRepository extends Neo4jRepository<Material, Long> {

    @Query("MATCH (n:Material) WHERE n.store_address = ({store_address}) RETURN n")
    Material findByStoreAddress(@Param("store_address") String store_address);

    @Query("MATCH (n:Material) WHERE n.material_name = ({material_name}) " +
            "MATCH (:Node)-[r:HAS_MATERIAL]->(n) DELETE r")
    void deleteFather(@Param("material_name") String material_name);

    @Query("MATCH (n:Material) WHERE n.material_name = ({material_name}) " +
            "MATCH (m:Node) WHERE m.course_mindmap = ({course_mindmap}) and m.node_id=({node_id}) " +
            "CREATE (m)-[:HAS_MATERIAL]->(n)")
    void createFather(@Param("material_name") String material_name, @Param("course_mindmap") String course_mindmap, @Param("node_id") String node_id);
}

package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AssignmentShortRepository extends Neo4jRepository<AssignmentShort, Long> {

    @Query("MATCH (n:Assignment_short) WHERE n.short_id = ({short_id}) RETURN n")
    List<AssignmentShort> findByShortId(@Param("short_id") String short_id);

    @Query("start n = node({id}) " +
            "MATCH (:Node)-[r:HAS_ASSIGNMENT_SHORT]->(n) DELETE r")
    void deleteFather(@Param("id") Long id);

    @Query("start n = node({id}) " +
            "MATCH (m:Node) WHERE m.course_mindmap = ({course_mindmap}) and m.node_id=({node_id}) " +
            "CREATE (m)-[:HAS_ASSIGNMENT_SHORT]->(n)")
    void createFather(@Param("id") Long id, @Param("course_mindmap") String course_mindmap, @Param("node_id") String node_id);

}

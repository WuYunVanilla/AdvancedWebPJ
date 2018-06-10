package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface CoursewareRepository extends Neo4jRepository<Courseware, Long> {
    @Query("MATCH (n:Courseware) WHERE n.store_address = ({store_address}) RETURN n")
    Courseware findByStoreAddress(@Param("store_address") String store_address);

    @Query("MATCH (n:Courseware) WHERE n.courseware_name = ({courseware_name}) " +
            "MATCH (:Node)-[r:HAS_COURSEWARE]->(n) DELETE r")
    void deleteFather(@Param("courseware_name") String courseware_name);

    @Query("MATCH (n:Courseware) WHERE n.courseware_name = ({courseware_name}) " +
            "MATCH (m:Node) WHERE m.course_mindmap = ({course_mindmap}) and m.node_id=({node_id}) " +
            "CREATE (m)-[:HAS_COURSEWARE]->(n)")
    void createFather(@Param("courseware_name") String courseware_name, @Param("course_mindmap") String course_mindmap, @Param("node_id") String node_id);
}

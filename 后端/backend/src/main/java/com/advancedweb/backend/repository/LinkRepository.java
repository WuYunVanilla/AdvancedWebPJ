package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface LinkRepository extends Neo4jRepository<Link, Long> {
    @Query("MATCH (n:Link) WHERE n.link_address = ({link_address}) RETURN n")
    Link findByLinkAddress(@Param("link_address") String link_address);

    @Query("MATCH (n:Link) WHERE n.link_address = ({link_address}) " +
            "MATCH (:Node)-[r:HAS_LINK]->(n) DELETE r")
    void deleteFather(@Param("link_address") String link_address);

    @Query("MATCH (n:Link) WHERE n.link_address = ({link_address}) " +
            "MATCH (m:Node) WHERE m.course_mindmap = ({course_mindmap}) and m.node_id=({node_id}) " +
            "CREATE (m)-[:HAS_LINK]->(n)")
    void createFather(@Param("link_address") String link_address, @Param("course_mindmap") String course_mindmap, @Param("node_id") String node_id);
}

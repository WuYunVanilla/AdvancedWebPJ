package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface MindmapRepository extends Neo4jRepository<Mindmap, Long> {
    @Query("MATCH (n:Mindmap) WHERE  n.mindmap_id = ({mindmap_id}) RETURN n")
    Mindmap findByMindmap_id(@Param("mindmap_id") String mindmap_id);

    @Query("start mindmap = node({id}) match (mindmap)-[:HAS_ROOT]->(rootNode) return rootNode")
    Node findRootNode(@Param("id") long id);
}

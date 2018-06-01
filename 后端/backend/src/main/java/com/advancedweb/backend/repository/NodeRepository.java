package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface NodeRepository extends Neo4jRepository<Node, Long> {
    @Query("MATCH (n:Node) WHERE n.node_id = ({node_id}) RETURN n")
    Node findByNodeId(@Param("node_id") String node_id);
}

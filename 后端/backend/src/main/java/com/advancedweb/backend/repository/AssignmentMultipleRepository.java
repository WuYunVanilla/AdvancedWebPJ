package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AssignmentMultipleRepository extends Neo4jRepository<AssignmentMultiple, Long> {
    @Query("MATCH (n:Assignment_multiple) WHERE n.multi_id = ({multi_id}) RETURN n")
    public List<AssignmentMultiple> findByMultiId( @Param("multi_id") String multi_id);
}

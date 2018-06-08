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
    public List<AssignmentShort> findByShortId(@Param("short_id") String short_id);

}

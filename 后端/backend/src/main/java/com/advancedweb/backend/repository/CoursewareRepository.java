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
}

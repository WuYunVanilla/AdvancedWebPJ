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
}

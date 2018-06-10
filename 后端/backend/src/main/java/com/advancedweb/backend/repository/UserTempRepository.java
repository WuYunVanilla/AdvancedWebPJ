package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.UserTemp;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserTempRepository extends Neo4jRepository<UserTemp, Long> {
    @Query("MATCH (n:UserTemp) WHERE n.user_name = ({user_name}) RETURN n")
    UserTemp findByUser_name(@Param("user_name") String user_name);
}

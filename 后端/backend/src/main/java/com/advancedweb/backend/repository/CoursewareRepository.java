package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface CoursewareRepository extends Neo4jRepository<Courseware, Long> {
    @Query("MATCH (n:Courseware) WHERE n.courseware_name = ({course_name}) RETURN n")
    Courseware findByCoursewareName(@Param("courseware_name") String courseware_name);
}

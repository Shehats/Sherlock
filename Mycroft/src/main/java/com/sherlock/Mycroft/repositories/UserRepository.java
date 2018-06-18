package com.sherlock.Mycroft.repositories;

import com.sherlock.Mycroft.entities.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Neo4jRepository<User,Long> {
    @Query("MATCH (user:User { profileId: {profileId} }) RETURN id(user) as id")
    Long getUserIdByProfileId(@Param("profileId") Long profileId);
}

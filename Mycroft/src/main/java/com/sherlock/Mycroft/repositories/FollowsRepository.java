package com.sherlock.Mycroft.repositories;

import com.sherlock.Mycroft.entities.Follows;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface FollowsRepository extends Neo4jRepository<Follows, Long> {
    @Query("FOREACH(x in {follows} | MERGE (a:User { profileId: x.userA.profileId })\n" +
            "MERGE (b:User { profileId: x.userB.profileId })\n" +
            "MERGE (a)-[:FOLLOWS]->(b))")
    void saveFollows(@Param("follows") Set<Follows> follows);
}

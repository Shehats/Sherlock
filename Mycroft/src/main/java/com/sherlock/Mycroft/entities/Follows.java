package com.sherlock.Mycroft.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FOLLOWS")
public class Follows {
    @GraphId
    private Long relationshipId;
    @StartNode
    private User userA;
    @EndNode
    private User userB;
}

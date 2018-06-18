package com.sherlock.Mycroft.entities;

import com.sherlock.Mycroft.instance.NodeInstance;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User{
    @GraphId
    protected Long id;

    @Index(unique = true)
    private Long profileId;

    @Relationship(type = "FOLLOWS", direction = "OUTGOING")
    private Set<User> follows = new HashSet<>();

    @Relationship(type = "FOLLOWS", direction = "INCOMING")
    private Set<User> followers = new HashSet<>();

    private String screenName;
    private Float pagerank;
}

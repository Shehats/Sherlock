package com.sherlock.Mycroft.repositories;

import com.sherlock.Mycroft.instance.NodeInstance;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface InstanceGraphRepository<T extends NodeInstance> extends Neo4jRepository<T, Long> {
}

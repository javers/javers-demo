package org.javers.democlient.application;

import org.javers.democlient.domain.Hierarchy;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author bartosz walacik
 */
public interface HierarchyCrudRepository extends MongoRepository<Hierarchy, String> {

}

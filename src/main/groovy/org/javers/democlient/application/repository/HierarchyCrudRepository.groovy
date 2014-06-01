package org.javers.democlient.application.repository

import org.javers.democlient.domain.Hierarchy
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author bartosz walacik
 */
interface HierarchyCrudRepository extends MongoRepository<Hierarchy, String> {

}

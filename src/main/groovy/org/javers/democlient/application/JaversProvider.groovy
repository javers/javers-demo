package org.javers.democlient.application

import com.mongodb.Mongo
import org.javers.core.Javers
import org.javers.core.JaversBuilder
import org.javers.repository.mongo.MongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author bartosz walacik
 */
@Configuration
class JaversProvider {

    @Autowired
    private final Mongo mongo

    @Bean
    Javers javers(){
        //javers = JaversBuilder.javers().build()
        def mongoRepo =  new MongoRepository(mongo.getDB("test"))
        JaversBuilder.javers().registerJaversRepository(mongoRepo).build()
    }
}

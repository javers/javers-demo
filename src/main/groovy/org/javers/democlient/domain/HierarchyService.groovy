package org.javers.democlient.domain

import com.mongodb.Mongo
import org.javers.core.Javers
import org.javers.core.JaversBuilder
import org.javers.core.commit.Commit
import org.javers.core.diff.Diff
import org.javers.democlient.application.repository.HierarchyRepository
import org.javers.repository.mongo.MongoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author bartosz walacik
 */
@Service
public class HierarchyService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HierarchyService)

    private final Javers javers

    private final Mongo mongo;
    private final HierarchyRepository hierarchyRepository

    @Autowired
    HierarchyService(Mongo mongo, HierarchyRepository hierarchyRepository) {
        this.mongo = mongo
        this.hierarchyRepository = hierarchyRepository

        //javers = JaversBuilder.javers().build()
        def mongoRepo =  new MongoRepository(mongo.getDB("test"))
        javers = JaversBuilder.javers().registerJaversRepository(mongoRepo).build()
    }

    void save(Hierarchy hierarchy){
        Commit commit = javers.commit("demo-app",hierarchy.getRoot())
        logger.info("diff: \n{}", javers.toJson(commit.diff))
        hierarchyRepository.save(hierarchy)
    }

    String diffAsJson(Hierarchy oldHier, Hierarchy currentHier){
        def diff = diff(oldHier,currentHier)
        javers.toJson(diff)
    }

    void changeBoss(String hierarchyName, String subordinateLogin, String newBossLogin){
        Hierarchy hierarchy = hierarchyRepository.getByName(hierarchyName)

        def subordinate = hierarchy.getEmployee(subordinateLogin)
        def newBoss = hierarchy.getEmployee(newBossLogin)

        newBoss.addSubordinate(subordinate)

        save(hierarchy)
    }

    Diff diff(Hierarchy oldHier, Hierarchy currentHier) {
        Diff diff = javers.compare(oldHier.root, currentHier.root)
        diff
    }

    void updatePosition(String hierarchyName, String empLogin, Position newPosition, Integer newSalary) {
        Hierarchy hierarchy = hierarchyRepository.getByName(hierarchyName)

        def employee = hierarchy.getEmployee(empLogin)

        employee.assignPosition(newPosition, newSalary)

        save(hierarchy)
    }
}

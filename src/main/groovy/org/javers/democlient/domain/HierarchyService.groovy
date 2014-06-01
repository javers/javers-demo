package org.javers.democlient.domain


import org.javers.core.Javers
import org.javers.core.JaversBuilder
import org.javers.core.diff.Diff
import org.javers.democlient.application.repository.HierarchyRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author bartosz walacik
 */
@Service
public class HierarchyService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HierarchyService)

    private final Javers javers = JaversBuilder.javers().build()

    @Autowired private HierarchyRepository hierarchyRepository

    String diffAsJson(Hierarchy oldHier, Hierarchy currentHier){
        def diff = diff(oldHier,currentHier)
        javers.toJson(diff)
    }

    def changeBoss(String hierarchyName, String subordinateLogin, String newBossLogin){
        Hierarchy hierarchy = hierarchyRepository.getByName(hierarchyName)

        def subordinate = hierarchy.getEmployee(subordinateLogin)
        def newBoss = hierarchy.getEmployee(newBossLogin)

        newBoss.addSubordinate(subordinate)

        hierarchyRepository.save(hierarchy)
    }

    Diff diff(Hierarchy oldHier, Hierarchy currentHier) {
        Diff diff = javers.compare(oldHier.root, currentHier.root)
        diff
    }
}

package org.javers.democlient.domain

import org.javers.core.Javers
import org.javers.core.commit.Commit
import org.javers.core.diff.Diff
import org.javers.democlient.application.repository.HierarchyRepository
import org.javers.repository.jql.QueryBuilder
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
    private final HierarchyRepository hierarchyRepository

    @Autowired
    HierarchyService(Javers javers, HierarchyRepository hierarchyRepository) {
        this.javers = javers
        this.hierarchyRepository = hierarchyRepository
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

    String historySnapshotsAsJson(String employeeLogin){
        def snapshots = javers.findSnapshots(QueryBuilder.byInstanceId(employeeLogin, Employee).limit(10).build())
        javers.jsonConverter.toJson(snapshots)
    }

    String historyChangesAsJson(String employeeLogin){
        def changes = javers.findChanges(QueryBuilder.byInstanceId(employeeLogin, Employee).limit(10).build())
        logger.info("found ${changes.size()} change(s) for $employeeLogin")
        javers.jsonConverter.toJson(changes)
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

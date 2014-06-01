package org.javers.democlient.domain


import org.javers.core.Javers
import org.javers.core.JaversBuilder
import org.javers.core.diff.Diff
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @author bartosz walacik
 */
@Service
public class HierarchyService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HierarchyService)

    private final Javers javers = JaversBuilder.javers().build()

    String diffAsJson(Hierarchy oldHier, Hierarchy currentHier){
        def diff = diff(oldHier,currentHier)
        javers.toJson(diff)
    }

    //we want to treat 'Hierarchy_2013' and 'Hierarchy_2014' as both have the same GlobalId
    //TODO add javers diff feature: join two nodes with different id
    Diff diff(Hierarchy oldHier, Hierarchy currentHier) {

        oldHier.setHierarchyName(currentHier.getHierarchyName());

        Diff diff = javers.compare(oldHier, currentHier)

        diff
    }
}

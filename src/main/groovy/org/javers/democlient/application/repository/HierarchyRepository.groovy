package org.javers.democlient.application.repository

import org.javers.democlient.domain.Hierarchy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import static com.google.common.base.Preconditions.checkArgument

/**
 * @author bartosz walacik
 */
@Repository
class HierarchyRepository {
    @Autowired private HierarchyCrudRepository hierarchyCrudRepository

    void save(Hierarchy hierarchy){
        checkArgument(hierarchy != null)
        hierarchyCrudRepository.save(hierarchy)
    }

    Hierarchy getByName(String hierarchyName){
        Hierarchy hier = hierarchyCrudRepository.findOne(hierarchyName)
        if (hier == null){
            return null
        }

        hier.root.rebuildBiDirectionalRelation()
        hier
    }
}




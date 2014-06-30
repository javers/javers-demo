package org.javers.democlient.controller

import org.javers.democlient.domain.HierarchyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

/**
 * @author bartosz walacik
 */
@Controller
class EmpHistoryController {

    private final HierarchyService hierarchyService

    @Autowired
    EmpHistoryController(HierarchyService hierarchyService) {
        this.hierarchyService = hierarchyService
    }


    @RequestMapping(value="/emp-state-history")
    def showStateHistory(@RequestParam(defaultValue = "kaz") String login){

        def snapshotsJson = hierarchyService.historySnapshotsAsJson(login)

        new ModelAndView("views/emp-state-history",[snapshots: snapshotsJson, login:login])
    }

    @RequestMapping(value="/emp-change-history")
    def showDiffHistory(@RequestParam(defaultValue = "kaz") String login){

        def changesJson = hierarchyService.historyChangesAsJson(login)

        new ModelAndView("views/emp-change-history",[changes: changesJson, login:login])
    }
}

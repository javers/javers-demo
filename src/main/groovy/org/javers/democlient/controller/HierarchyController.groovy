package org.javers.democlient.controller

import org.javers.democlient.application.repository.HierarchyRepository
import org.javers.democlient.domain.HierarchyService
import org.javers.democlient.domain.Position
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

/**
 * @author bartosz walacik
 */
@Controller
class HierarchyController {
    static final String DEF_HIER = "Hier_2014"

    @Autowired HierarchyRepository hierarchyRepository;
    @Autowired HierarchyService hierarchyService

    @RequestMapping(value="/hierarchy/{hierName}")
    def show(@PathVariable("hierName") String hierName){

        def hier = hierarchyRepository.getByName(hierName)

        new ModelAndView("views/hierarchy",[hier: hier])
    }

    @RequestMapping(value="/hierarchy-diff/{leftHierName}/{rightHierName}")
    def getDiff(@PathVariable("leftHierName")  String leftHierName,
                @PathVariable("rightHierName") String rightHierName){

        def leftHier =  hierarchyRepository.getByName(leftHierName)
        def rightHier = hierarchyRepository.getByName(rightHierName)

        def diff = hierarchyService.diffAsJson(leftHier, rightHier)

        new ModelAndView("views/hierarchy-diff",[diffJson: diff,
                                                 leftHierName: leftHierName,
                                                 rightHierName: rightHierName])
    }

    @RequestMapping(value="/hierarchy-edit/change-boss", method = RequestMethod.POST)
    def changeBoss(@RequestParam String subordinateLogin,
                   @RequestParam String newBossLogin){
        hierarchyService.changeBoss(DEF_HIER, subordinateLogin, newBossLogin)

        showAndEdit()
    }

    @RequestMapping(value="/hierarchy-edit/update-position", method = RequestMethod.POST)
    def updatePosition(@RequestParam String empLogin,
                       @RequestParam String newPosition,
                       @RequestParam Integer newSalary){
        hierarchyService.updatePosition(DEF_HIER, empLogin, Position.valueOf(newPosition), newSalary)

        showAndEdit()
    }

    @RequestMapping(value="/hierarchy-edit")
    def showAndEdit(){
        def hierarchy = hierarchyRepository.getByName(DEF_HIER)
        new ModelAndView("views/hierarchy-edit",[hierarchy:hierarchy])
    }

}

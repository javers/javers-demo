package org.javers.democlient.controller

import org.javers.democlient.application.repository.HierarchyRepository
import org.javers.democlient.domain.HierarchyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @author bartosz walacik
 */
@Controller
class HierarchyController {

    @Autowired HierarchyRepository hierarchyRepository;
    @Autowired HierarchyService hierarchyService

    @RequestMapping(value="/hierarchy/{hierName}")
    def get(@PathVariable("hierName") String hierName){

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

}

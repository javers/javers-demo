package org.javers.democlient.controller

import org.javers.democlient.application.HierarchyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
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

    @RequestMapping(value="/hierarchy/{hierName}")
    def get(@PathVariable("hierName") String hierName){

        def hier = hierarchyRepository.getByName(hierName)

        new ModelAndView("views/hierarchy",[hier: hier])
    }

}

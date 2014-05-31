package org.javers.democlient.application;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.javers.democlient.domain.Employee
import org.javers.democlient.domain.Hierarchy;
import org.javers.democlient.domain.Position;
import org.javers.democlient.domain.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

import static org.javers.democlient.domain.Position.CTO
import static org.javers.democlient.domain.Position.DEVELOPER
import static org.javers.democlient.domain.Position.IT_MANAGER
import static org.javers.democlient.domain.Position.SCRUM_MASTER;
import static org.javers.democlient.domain.Position.TEAM_LEAD;

/**
 * @author bartosz walacik
 */
@Service
class DataInitializer {
    @Autowired
    private HierarchyRepository hierarchyRepository

    void populate(){
        hierarchyRepository.save(new Hierarchy(createBobTree(),"Hier_2014"))

        def bolBold = createBobTree()
        def lucyOld = bolBold.getSubordinate('kaz').get().getSubordinate('lucy').get()
        def evaOld = bolBold.getSubordinate('kaz').get().getSubordinate('eva').get()
        lucyOld.addSubordinate(evaOld.getSubordinate('merry').get())
        lucyOld.assignPosition(TEAM_LEAD,13500)
        lucyOld.getSubordinate('frodo').get().assignPosition(SCRUM_MASTER,9_000)
        hierarchyRepository.save(new Hierarchy(bolBold,"Hier_2013"))
    }

    Employee createBobTree(){

        def frodo = new Employee("frodo", "Frodo", "Baggins", Sex.MALE).assignPosition(DEVELOPER, 9_000)
        def bilbo = new Employee("bilbo", "Bilbo", "Baggins", Sex.MALE).assignPosition(SCRUM_MASTER, 10_000)
        def sam = new Employee("sam", "Sam", "Gamgee", Sex.MALE).assignPosition(DEVELOPER, 11_000)
        def merry = new Employee("merry", "Meriadoc", "Brandybuck", Sex.MALE).assignPosition(DEVELOPER, 12_000)


        def lucy     = new Employee("lucy","Lucy","Valinor", Sex.FEMALE).assignPosition(TEAM_LEAD, 13_000).addSubordinates([frodo,bilbo])
        def eva      = new Employee("eva","Eva","Celebrimbor", Sex.FEMALE).assignPosition(TEAM_LEAD, 14_000).addSubordinates([sam,merry])
        def charlie  = new Employee("charlie","Charlie","Big", Sex.MALE).assignPosition(TEAM_LEAD, 15_000)

        def kaz  = new Employee("kaz","Mad","Kaz", Sex.MALE).assignPosition(IT_MANAGER, 16_000).addSubordinates([lucy, eva])
        def stef = new Employee("stef","Crazy","Stefan", Sex.MALE).assignPosition(IT_MANAGER, 17_000).addSubordinate(charlie)

        new Employee("bob","Uncle","Bob", Sex.MALE).assignPosition(CTO, 20_000)
           .addSubordinates([kaz,stef])
    }
}

package org.javers.democlient.application;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.javers.democlient.domain.Employee;
import org.javers.democlient.domain.Position;
import org.javers.democlient.domain.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

import static org.javers.democlient.domain.Position.CTO;
import static org.javers.democlient.domain.Position.TEAM_LEAD;

/**
 * @author bartosz walacik
 */
@Service
public class DataInitializer {
    @Autowired
    EmployeeRepository employeeRepository;

    public void populate(){
        Employee kaz = new Employee("kaz","Kazik","Mad", Sex.MALE)
                      .assignPosition(TEAM_LEAD, 10_000);


        Employee bob = new Employee("bob","Uncle","Bob", Sex.MALE)
                      .assignPosition(CTO, 20_000)
                      .addSubordinate(kaz);


        employeeRepository.save(ImmutableList.of(bob,kaz));
    }
}

package org.javers.democlient.domain;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author bartosz walacik
 */
public class Employee extends Person implements Serializable {

    public Employee(String login, String firstName, String lastName, Sex sex) {
       super(login, firstName, lastName, sex);
    }

    private Position position;
    private Integer salary;
    private Person boss;
    private Set<Person> subordinates;

    public void fire(){
        position = null;
        salary = 0;
        boss = null;
        subordinates = null;
    }

    public void assignPosition(Position position, Integer salary){
        Preconditions.checkArgument(position != null);
        Preconditions.checkArgument(salary != null);
    }
}

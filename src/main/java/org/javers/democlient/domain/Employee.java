package org.javers.democlient.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bartosz walacik
 */
@Document
public class Employee extends Person implements Serializable {

    public Employee(String login, String firstName, String lastName, Sex sex) {
       super(login, firstName, lastName, sex);
    }

    private Position position;
    private Integer salary;

    @DBRef
    private Person boss;

    @Transient
    private Set<Employee> subordinates = new HashSet<>();

    public void fire(){
        position = null;
        salary = 0;
        boss = null;
        subordinates = null;
    }

    public Employee assignPosition(Position position, Integer salary){
        Preconditions.checkArgument(position != null);
        Preconditions.checkArgument(salary >= 0);
        this.position = position;
        this.salary = salary;
        return this;
    }

    public Employee addSubordinate(Employee subordinate){
        Preconditions.checkArgument(subordinate != null);
        subordinate.boss = this;
        subordinates.add(subordinate);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.getFirstName() + " "+ this.getLastName() + " aka "+this.getLogin())
                .append(", "+this.getSex())
                .append(",position: " + position)
                .append(" $", salary)
                .append(", boss: ", boss!=null ? boss.getLogin() : "")
                .append(", subordinates: ", subordinates.stream().map(e -> e.getLogin()))
                .toString();
    }
}

package org.javers.democlient.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author bartosz walacik
 */
@Document
public class Employee extends Person implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    @Id
    private String login;
    private Position position;
    private Integer salary;

    public Employee(String login, String firstName, String lastName, Sex sex) {
       super(firstName, lastName, sex);
        Preconditions.checkArgument(StringUtils.isNoneBlank(login));
       this.login = login;
    }

    @Transient
    private Employee boss;

    private List<Employee> subordinates = new ArrayList<>();

    public Position getPosition() {
        return position;
    }

    public Integer getSalary() {
        return salary;
    }

    public Person getBoss() {
        return boss;
    }

    public String getLogin() {
        return login;
    }

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

    public Employee addSubordinates(List<Employee> subordinates){
        Preconditions.checkArgument(subordinates != null);
        subordinates.forEach(s-> addSubordinate(s));
        return this;
    }

    public Employee addSubordinate(Employee subordinate){
        Preconditions.checkArgument(subordinate != null);

        if (subordinate.boss!=null){
            subordinate.boss.subordinates.remove(subordinate);
        }
        subordinate.boss = this;

        subordinates.add(subordinate);

        logger.info("subordinate [{}] has a new boss [{}]", subordinate.getLogin(), this.getLogin());

        return this;
    }

    public void forEachDirectSubordinate(Consumer<Employee> action){
        subordinates.forEach(action);
    }

    /**
     * @throws java.util.NoSuchElementException
     */
    public Employee getSubordinate(String login) {
        return getAllEmployees().stream().filter(s->s.getLogin().equals(login)).findFirst().get();
    }

    List<Employee> getAllEmployees(){
        List<Employee> all = new ArrayList<>();
        this.acceptVisitor(e-> all.add(e));
        return all;
    }

    void acceptVisitor(Consumer<Employee> action){
        action.accept(this);
        subordinates.forEach(s->s.acceptVisitor(action));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Employee)){
            return false;
        }

        return login.equals( ((Employee)obj).getLogin());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(getLogin())
                .append(" name",getFirstName() + " "+ this.getLastName())
                .append(" sex",this.getSex())
                .append(" position",position)
                .append(" $", salary)
                .append(" boss", boss!=null ? boss.getLogin() : "")
                .append(" subordinates", subordinates.stream().map(e -> e.getLogin()).collect(Collectors.toList()))
                .toString();
    }

    public void rebuildBiDirectionalRelation() {
        subordinates.forEach(s -> {
            s.boss = this;
            s.rebuildBiDirectionalRelation();
        });
    }
}

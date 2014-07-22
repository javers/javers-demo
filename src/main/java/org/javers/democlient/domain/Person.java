package org.javers.democlient.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

/**
 * @author bartosz walacik
 */
public class Person {

    private String firstName;
    private String lastName;
    private Sex sex;
    private Address address;

    public Person(String lastName) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(lastName));
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, Sex sex) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(lastName));
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public Address getAddress() {
        return address;
    }
}

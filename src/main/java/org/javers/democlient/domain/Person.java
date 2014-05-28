package org.javers.democlient.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

/**
 * @author bartosz walacik
 */
public class Person {

    @Id
    private String login;
    private String firstName;
    private String lastName;
    private Sex sex;
    private Address address;

    public Person(String login, String firstName, String lastName, Sex sex) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(login));
        Preconditions.checkArgument(StringUtils.isNoneBlank(lastName));
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }

    public String getLogin() {
        return login;
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

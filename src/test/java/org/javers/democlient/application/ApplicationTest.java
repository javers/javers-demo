package org.javers.democlient.application;


import org.javers.democlient.domain.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author bartosz walacik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest  {

    @Autowired EmployeeRepository employeeRepository;

    @Test
    public void shouldInitKazik() {


        Employee employee = employeeRepository.findOne("kaz");

        Assert.assertEquals("Mad",employee.getLastName());
        Assert.assertNotNull(employeeRepository);
    }
}

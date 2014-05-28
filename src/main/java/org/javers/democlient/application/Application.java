package org.javers.democlient.application;

import org.javers.democlient.domain.Employee;
import org.javers.democlient.domain.Position;
import org.javers.democlient.domain.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.math.BigDecimal;

/**
 * @author bartosz walacik
 */
@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee kaz = new Employee("kaz","Kazik","Mad", Sex.MALE);
        kaz.assignPosition(Position.TEAM_LEAD, 10_000);
        employeeRepository.save(kaz);

        Employee bob = new Employee("bob","Uncle","Bob", Sex.MALE);
        bob.assignPosition(Position.CTO, 20_000);
        employeeRepository.save(bob);
    }
}

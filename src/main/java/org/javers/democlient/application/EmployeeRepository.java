package org.javers.democlient.application;

import org.javers.democlient.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author bartosz walacik
 */
//@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public Employee findByLastName(String lastName);
}

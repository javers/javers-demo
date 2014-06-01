package org.javers.democlient.application.repository

import org.javers.democlient.domain.Employee

/**
 * @author bartosz walacik
 */
//@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
interface EmployeeRepository {//extends MongoRepository<Employee, String> {
    public Employee findByLastName(String lastName)
}

package org.javers.democlient.examples

import org.javers.core.Javers
import org.javers.core.JaversBuilder
import org.javers.core.diff.Diff
import org.javers.core.diff.changetype.ValueChange
import org.javers.core.metamodel.property.Property
import org.javers.democlient.domain.Person
import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class BasicDiffExample extends Specification{

    def "should compare two objects"() {

        given:
            Javers javers = JaversBuilder.javers().build()

            Person p1 = new Person("Chris")
            Person p2 = new Person("Maya")

        when:
            Diff diff = javers.compare(p1, p2)

        then:
            println javers.toJson(diff)

            /** there should be one change of type {@link ValueChange} */
            assert diff.changes.size() == 1
            assert diff.changes[0] instanceof ValueChange

            ValueChange change = diff.changes[0]

            /** change should have {@link Property} with name 'lastName'
             * and old value = 'Chris'
             * and new value = 'Maya' */
            println "propertyName: $change.property.name"
            println "old value:    $change.left"
            println "new value:    $change.right"
            assert change.property.name == 'lastName'
            assert change.left == 'Chris'
            assert change.right == 'Maya'

        }

}

package org.javers.democlient.application;


import org.javers.democlient.domain.Employee;
import org.javers.democlient.domain.Hierarchy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author bartosz walacik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest  {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired HierarchyRepository hierarchyRepository;

    @Test
    public void shouldInitKazik() {
        Hierarchy hierarchy = hierarchyRepository.getByName("Hier_2014");
        Hierarchy hierarchyOld = hierarchyRepository.getByName("Hier_2013");

        System.out.println("Hier_2013 :");
        System.out.println(hierarchyOld.print());

        System.out.println("Hier_2014 :");
        System.out.println(hierarchy.print());

        Employee bob = hierarchy.getRoot();

        Assert.assertEquals("Bob", bob.getLastName());
        Employee kaz = bob.getSubordinate("kaz");
        Assert.assertEquals("Kaz", kaz.getLastName());
        Assert.assertEquals(bob, kaz.getBoss());

    }
}

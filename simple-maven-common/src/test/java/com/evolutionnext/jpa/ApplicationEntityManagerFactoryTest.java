package com.evolutionnext.jpa;

import com.evolutionnext.jpa.ApplicationEntityManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.easymock.EasyMock.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Persistence.class)
public class ApplicationEntityManagerFactoryTest {

    @Test
    public void testCreate() {
        EntityManager entityManagerMock = createMock(EntityManager.class);
        EntityManagerFactory entityManagerFactoryMock = createMock(EntityManagerFactory.class);
        mockStatic(Persistence.class);

        /*
         * The static method call to IdGenerator.generateNewId() expectation.
         * This is why we need PowerMock.
         */
        expect(Persistence.createEntityManagerFactory("myPersistenceUnit")).andReturn(entityManagerFactoryMock).once();
        expect(entityManagerFactoryMock.createEntityManager()).andReturn(entityManagerMock).anyTimes();

        // Note how we replay the class, not the instance!
        replayAll(entityManagerMock, entityManagerFactoryMock);

        ApplicationEntityManagerFactory applicationEntityManagerFactory = new ApplicationEntityManagerFactory();
        assertThat(applicationEntityManagerFactory.createEntityManager()).isEqualTo(entityManagerMock);

        System.out.println(applicationEntityManagerFactory.createEntityManager());
        // Note how we verify the class, not the instance!
        verifyAll();
    }
}
package com.evolutionnext.dao;

import com.evolutionnext.jpa.ApplicationEntityManagerFactory;
import com.evolutionnext.model.Album;
import com.google.common.collect.Lists;
import org.junit.Test;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

public class JPAAlbumDAOTest {
    @Test
    public void testPersist() throws DAOException {
        Album album = new Album();
        JPAAlbumDAO jpaAlbumDAO = new JPAAlbumDAO();
        ApplicationEntityManagerFactory applicationEntityManagerFactory = createMock(ApplicationEntityManagerFactory.class);
        EntityManager entityManager = createMock(EntityManager.class);
        EntityTransaction entityTransaction = createMock(EntityTransaction.class);

        expect(applicationEntityManagerFactory.createEntityManager()).andReturn(entityManager);
        expect(entityManager.getTransaction()).andReturn(entityTransaction);
        entityTransaction.begin();
        entityManager.persist(album);
        entityManager.flush();
        entityTransaction.commit();
        entityManager.close();

        replay(applicationEntityManagerFactory, entityManager, entityTransaction);
        jpaAlbumDAO.setApplicationEntityManagerFactory(applicationEntityManagerFactory);
        jpaAlbumDAO.persist(album);
        verify(applicationEntityManagerFactory, entityManager, entityTransaction);
    }

    @Test
    public void testPersistWithException() {
        Album album = new Album();
        JPAAlbumDAO jpaAlbumDAO = new JPAAlbumDAO();
        ApplicationEntityManagerFactory applicationEntityManagerFactory = createMock(ApplicationEntityManagerFactory.class);
        EntityManager entityManager = createMock(EntityManager.class);
        EntityTransaction entityTransaction = createMock(EntityTransaction.class);

        expect(applicationEntityManagerFactory.createEntityManager()).andReturn(entityManager);
        expect(entityManager.getTransaction()).andReturn(entityTransaction);
        entityTransaction.begin();
        entityManager.persist(album);
        expectLastCall().andThrow(new EntityExistsException("EntityAlreadyExists"));
        entityTransaction.rollback();
        entityManager.close();

        replay(applicationEntityManagerFactory, entityManager, entityTransaction);
        jpaAlbumDAO.setApplicationEntityManagerFactory(applicationEntityManagerFactory);
        try {
            jpaAlbumDAO.persist(album);
            fail("Should not reach this point");
        } catch (DAOException e) {
            assertThat(e).hasMessage("javax.persistence.EntityExistsException: EntityAlreadyExists");
        }
        verify(applicationEntityManagerFactory, entityManager, entityTransaction);
    }

    @Test
    public void testAllAlbums() throws DAOException {
        JPAAlbumDAO jpaAlbumDAO = new JPAAlbumDAO();
        ApplicationEntityManagerFactory applicationEntityManagerFactory = createMock(ApplicationEntityManagerFactory.class);
        EntityManager entityManager = createMock(EntityManager.class);
        TypedQuery query = createMock(TypedQuery.class);

        expect(applicationEntityManagerFactory.createEntityManager()).andReturn(entityManager);
        expect(entityManager.createQuery("SELECT a from Album a", Album.class)).andReturn(query);
        ArrayList<Album> expectedAlbums = Lists.newArrayList(new Album("Zeppelin I"), new Album("Zeppelin II"), new Album("Thriller"));
        expect(query.getResultList()).andReturn(expectedAlbums);
        jpaAlbumDAO.setApplicationEntityManagerFactory(applicationEntityManagerFactory);
        entityManager.close();
        replay(applicationEntityManagerFactory, entityManager, query);
        assertThat(jpaAlbumDAO.getAllAlbums()).isEqualTo(expectedAlbums);
        verify(applicationEntityManagerFactory, entityManager, query);
    }
}
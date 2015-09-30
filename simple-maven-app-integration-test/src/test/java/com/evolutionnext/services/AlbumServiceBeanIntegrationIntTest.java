package com.evolutionnext.services;

import com.evolutionnext.dao.DAOException;
import com.evolutionnext.dao.JPAAlbumDAO;
import com.evolutionnext.jpa.ApplicationEntityManagerFactory;
import com.evolutionnext.model.Album;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.fest.assertions.Assertions.assertThat;

public class AlbumServiceBeanIntegrationIntTest {

    @Test
    public void testPersist() throws DAOException {
        AlbumServiceBean albumServiceBean = new AlbumServiceBean();
        ApplicationEntityManagerFactory applicationEntityManagerFactory = new ApplicationEntityManagerFactory();
        JPAAlbumDAO jpaAlbumDAO = new JPAAlbumDAO();
        jpaAlbumDAO.setApplicationEntityManagerFactory(applicationEntityManagerFactory);
        albumServiceBean.setAlbumDAO(jpaAlbumDAO);
        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 9");
        albumServiceBean.create();

        EntityManager entityManager = applicationEntityManagerFactory.createEntityManager();
        Album dbAlbum = entityManager.find(Album.class, albumServiceBean.getAlbum().getId());
        assertThat(dbAlbum.getName()).isEqualTo("Zeppelin 9");
    }

    @Test
    public void testPersistWith5Items() throws DAOException {
        AlbumServiceBean albumServiceBean = new AlbumServiceBean();
        ApplicationEntityManagerFactory applicationEntityManagerFactory = new ApplicationEntityManagerFactory();
        JPAAlbumDAO jpaAlbumDAO = new JPAAlbumDAO();
        jpaAlbumDAO.setApplicationEntityManagerFactory(applicationEntityManagerFactory);
        albumServiceBean.setAlbumDAO(jpaAlbumDAO);
        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 1");
        albumServiceBean.create();

        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 2");
        albumServiceBean.create();

        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 3");
        albumServiceBean.create();

        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 4");
        albumServiceBean.create();

        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 5");
        albumServiceBean.create();
        
        albumServiceBean.init();
        albumServiceBean.getAlbum().setName("Zeppelin 6");
        albumServiceBean.create();
    }
}

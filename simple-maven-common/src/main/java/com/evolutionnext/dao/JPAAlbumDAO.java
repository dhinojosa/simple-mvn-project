package com.evolutionnext.dao;

import com.evolutionnext.jpa.ApplicationEntityManagerFactory;
import com.evolutionnext.model.Album;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@ManagedBean(name="albumDAO")
@ApplicationScoped
public class JPAAlbumDAO implements AlbumDAO {

    @ManagedProperty(value="#{applicationEntityManagerFactory}")
    private ApplicationEntityManagerFactory applicationEntityManagerFactory;

    public void setApplicationEntityManagerFactory(ApplicationEntityManagerFactory applicationEntityManagerFactory) {
        this.applicationEntityManagerFactory = applicationEntityManagerFactory;
    }

    @Override
    public Long persist(Album album) throws DAOException {
        EntityManager entityManager = applicationEntityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(album);
            entityManager.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new DAOException(e);
        } finally {
            entityManager.close();
        }
        return album.getId();
    }

    @Override
    public List<Album> getAllAlbums() throws DAOException {
        EntityManager entityManager = applicationEntityManagerFactory.createEntityManager();
            TypedQuery<Album> query = entityManager.createQuery("SELECT a from Album a", Album.class);

        List<Album> result = query.getResultList();
        entityManager.close();
        return result;
    }
}

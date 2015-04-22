package com.evolutionnext.jpa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name="applicationEntityManagerFactory")
@ApplicationScoped
public class ApplicationEntityManagerFactory {

    private EntityManagerFactory entityManagerFactory;

    public EntityManager createEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        }
        return entityManagerFactory.createEntityManager();
    }
}

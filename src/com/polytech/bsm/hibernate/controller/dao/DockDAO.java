package com.polytech.bsm.hibernate.controller.dao;

import com.polytech.bsm.hibernate.model.Dock;

import javax.persistence.*;

public class DockDAO {

    public static Dock getDock(Integer code) {

        // Init
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tpHibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Object to return
        Dock dock = entityManager.getReference(Dock.class, Long.valueOf(code));

        // Closing managers
        entityManager.close();
        entityManagerFactory.close();

        return dock;
    }
}

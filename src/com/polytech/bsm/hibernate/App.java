package com.polytech.bsm.hibernate;

import com.polytech.bsm.hibernate.controller.dao.DockDAO;
import com.polytech.bsm.hibernate.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public App()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("tpHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void createDatabase()
    {
        entityTransaction = entityManager.getTransaction();

        Dock dock = new Dock();
        Owner jack = new Owner("Jack Sparrow", "132 Rue du poney");

        Space space = new Space(1);
        Sailboat blackpearl = new Sailboat("Le Blackpear", 5000, 15);
        Space space2 = new Space(1);
        Sailboat blackpearl2 = new Sailboat("Le Blackpear2", 5000, 15);
        Space space3 = new Space(1);
        Motorboat blackpearl3 = new Motorboat("Le Blackpear3", 5000, 15);

        try
        {
            blackpearl.addSpace(space);
            blackpearl.addOwner(jack);
            dock.addSpace(space);

            blackpearl2.addSpace(space2);
            blackpearl2.addOwner(jack);
            dock.addSpace(space2);

            blackpearl3.addSpace(space3);
            blackpearl3.addOwner(jack);
            dock.addSpace(space3);

            //SELECT COUNT(*) FROM Space s, Boat b WHERE s.boat_boatID=b.boatID AND b.DTYPE='Sailboat' AND b.sailSurface>150

        }
        catch (Exception d)
        {

        }

        entityTransaction.begin();
        entityManager.persist(dock);
        entityManager.persist(jack);

        entityManager.persist(space);
        entityManager.persist(blackpearl);
        entityManager.persist(space2);
        entityManager.persist(blackpearl2);
        entityManager.persist(space3);
        entityManager.persist(blackpearl3);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityTransaction getEntityTransaction() {
        return entityTransaction;
    }

    public void setEntityTransaction(EntityTransaction entityTransaction) {
        this.entityTransaction = entityTransaction;
    }

    public void close()
    {
        entityManager.close();
        entityManagerFactory.close();
    }
}

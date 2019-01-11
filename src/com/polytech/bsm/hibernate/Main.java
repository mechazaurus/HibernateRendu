package com.polytech.bsm.hibernate;

import com.polytech.bsm.hibernate.model.Dock;
import com.polytech.bsm.hibernate.model.Space;
import com.polytech.bsm.hibernate.model.Owner;
import com.polytech.bsm.hibernate.model.Sailboat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tpHibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        Space space = new Space(1);
        Owner jack = new Owner("Jack Sparrow", "132 Rue du poney");
        Sailboat blackpearl = new Sailboat("Le Blackpear", 5000, 15);
        Dock dock = new Dock();
        try
        {
            blackpearl.addSpace(space);
            dock.addSpace(space);
        }
        catch (Exception d)
        {

        }

        entityTransaction.begin();
        entityManager.persist(space);
        entityManager.persist(jack);
        entityManager.persist(blackpearl);
        entityManager.persist(dock);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}

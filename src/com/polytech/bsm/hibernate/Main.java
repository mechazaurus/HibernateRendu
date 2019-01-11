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

        Dock dock = new Dock();
        Owner jack = new Owner("Jack Sparrow", "132 Rue du poney");

        Space space = new Space(1);
        Sailboat blackpearl = new Sailboat("Le Blackpear", 5000, 15);
        Space space2 = new Space(1);
        Sailboat blackpearl2 = new Sailboat("Le Blackpear2", 5000, 15);
        Space space3 = new Space(1);
        Sailboat blackpearl3 = new Sailboat("Le Blackpear3", 5000, 15);

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
}

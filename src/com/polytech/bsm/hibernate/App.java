package com.polytech.bsm.hibernate;

import com.polytech.bsm.hibernate.controller.dao.DockDAO;
import com.polytech.bsm.hibernate.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.print.Doc;
import javax.xml.soap.SAAJMetaFactory;

public class App {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public App(EntityManager em)
    {
        entityManager = em;
    }

    public void createDatabase()
    {
        entityTransaction = entityManager.getTransaction();

        //Creating docks
        Dock dock1 = new Dock(12);
        Dock dock2 = new Dock(12);

        //Creating owners
        //Jack owns all sailboat
        Owner jack = new Owner("Jack Sparrow", "132 Rue du poney");
        //Bob owns all motorboat
        Owner bob = new Owner("Bob", "Champs Elysees");

        //Creating spaces with boats
        //Sailboats
        Space space0 = new Space(200);
        Sailboat sailboat0 = new Sailboat("Le Blackpear", 5000, 300);
        Space space1 = new Space(200);
        Sailboat sailboat1 = new Sailboat("Le hollandais volant", 300, 600);
        Space space2 = new Space(200);
        Sailboat sailboat2 = new Sailboat("Destroyer", 14000, 150);
        Space space3 = new Space(400);
        Sailboat sailboat3 = new Sailboat("Royal Fortune", 8000, 500);

        //Motorboats
        Space space4 = new Space(200);
        Motorboat motorboat0 = new Motorboat("Titanic", 5000, 4500);
        Space space5 = new Space(200);
        Motorboat motorboat1 = new Motorboat("Charles de gaulle", 150000, 39000);
        Space space6 = new Space(200);
        Motorboat motorboat2 = new Motorboat("Fregate", 2000, 4500);
        Space space7 = new Space(200);
        Motorboat motorboat3 = new Motorboat("EL BATO", 25000, 5400);

        //Empty spaces, 1 for each dock
        Space space8 = new Space(200);
        Space space9 = new Space(200);


        try
        {
            //Adding to dock1
            sailboat0.addSpace(space0);
            sailboat0.addOwner(jack);
            dock1.addSpace(space0);

            sailboat1.addSpace(space1);
            sailboat1.addOwner(jack);
            dock1.addSpace(space1);

            sailboat2.addSpace(space2);
            sailboat2.addOwner(jack);
            dock1.addSpace(space2);

            motorboat0.addSpace(space4);
            motorboat0.addOwner(bob);
            dock1.addSpace(space4);

            //Adding empty space to dock1
            dock1.addSpace(space8);

            //Adding to dock2
            sailboat3.addSpace(space3);
            sailboat3.addOwner(jack);
            dock2.addSpace(space3);

            motorboat1.addSpace(space5);
            motorboat1.addOwner(bob);
            dock2.addSpace(space5);

            motorboat2.addSpace(space6);
            motorboat2.addOwner(bob);
            dock2.addSpace(space6);

            motorboat3.addSpace(space7);
            motorboat3.addOwner(bob);
            dock2.addSpace(space7);

            //Adding empty space to dock2
            dock2.addSpace(space9);

        }
        catch (Exception d)
        {
            System.out.println("Something went wrong");
        }

        entityTransaction.begin();

        //Commit docks
        entityManager.persist(dock1);
        entityManager.persist(dock2);

        //Commit owners
        entityManager.persist(jack);
        entityManager.persist(bob);

        //Commit spaces
        entityManager.persist(space0);
        entityManager.persist(space1);
        entityManager.persist(space2);
        entityManager.persist(space3);
        entityManager.persist(space4);
        entityManager.persist(space5);
        entityManager.persist(space6);
        entityManager.persist(space7);
        entityManager.persist(space8);
        entityManager.persist(space9);

        //Commit sailboats
        entityManager.persist(sailboat0);
        entityManager.persist(sailboat1);
        entityManager.persist(sailboat2);
        entityManager.persist(sailboat3);

        //Commit motorboats
        entityManager.persist(motorboat0);
        entityManager.persist(motorboat1);
        entityManager.persist(motorboat2);
        entityManager.persist(motorboat3);

        entityTransaction.commit();
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
    }
}

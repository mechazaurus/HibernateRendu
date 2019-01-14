package com.polytech.bsm.hibernate.view;

import com.polytech.bsm.hibernate.controller.dao.DockDAO;
import com.polytech.bsm.hibernate.controller.dao.SpaceDAO;

import javax.persistence.EntityManager;
import java.util.Scanner;

public abstract class MainView {

    public static void mainMenu(EntityManager em) {

        // Looping
        Boolean stillGoing = true;

        while (stillGoing) {

            // Menu choice
            System.out.println("========================================");
            System.out.println("Please choose your action (type in) :");
            System.out.println("1 - Number of boats from a dock.");
            System.out.println("2 - Number of spaces with sailboats having a given sail surface.");
            System.out.println("3 - Exit.");
            System.out.println("========================================");
            System.out.print("Choice : ");

            // Get user's choice
            Scanner reader = new Scanner(System.in);
            reader.reset();
            Integer choice = reader.nextInt();

            switch (choice) {
                case 1:
                    numberOfBoatsMenu(em);
                    break;

                case 2:
                    sailSurfaceMenu(em);
                    break;

                case 3:
                    stillGoing = false;
                    break;

                default:
                    stillGoing = false;
            }

            reader.close();
        }

        System.out.println("Goodbye !");
    }

    /**
     * Terminal display of the "menu" which allows the user to get a number of boats present from a dock.
     * @param em The EntityManager to use to connect to the database.
     */
    public static void numberOfBoatsMenu(EntityManager em) {

        // Scanner init
        Scanner reader = new Scanner(System.in);

        // Get dock's ID
        System.out.println("Enter dock's ID : ");
        Long code = reader.nextLong();

        // Don't forget to close the scanner
        reader.close();

        // Get result
        Long result = DockDAO.numberOfBoats(em, code);

        // Print it out
        System.out.println("There is/are " + result + " boat(s) currently present in dock number " + code);
    }

    /**
     * Terminal display of the "menu" which allows the user to get a number of spaces from a dock with a given
     * value of sail surface for boats.
     * @param em The EntityManager to use to connect to the database.
     */
    public static void sailSurfaceMenu(EntityManager em) {

        // Scanner init
        Scanner reader = new Scanner(System.in);

        // Get dock's ID
        System.out.println("Enter dock's ID : ");
        Long code = reader.nextLong();

        // Get sail's surface
        System.out.println("Enter sailboat's sail surface : ");
        Integer surface = reader.nextInt();

        // Don't forget to the close scanner
        reader.close();

        // Get result
        Long result = SpaceDAO.getSpaceWithSailboat(em, code, surface);

        // Print it out
        System.out.println("There is/are "+result+" space(s) located at Dock number "+code+" assigned to Sailboats with sailsurface > "+surface);
    }
}

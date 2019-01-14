package com.polytech.bsm.hibernate.controller.dao;

/**
 * Controller to manipulate Docks from database.
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 14/01/2019
 */

import com.polytech.bsm.hibernate.model.Dock;

import javax.persistence.EntityManager;

public class DockDAO {

    /**
     * Get a dock from the database with its code.
     * @param em The EntityManager to use to connect to the database.
     * @param dockId The dock's code.
     * @return A Dock object from the database.
     */
    public static Dock getDock(EntityManager em, Long dockId) {

        // Object to return
        Dock dock = em.getReference(Dock.class, dockId);

        return dock;
    }

}


package com.polytech.bsm.hibernate.controller.dao;

/**
 * Controller to manipulate Spaces from database.
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 14/01/2019
 */

import com.polytech.bsm.hibernate.model.Space;

import javax.persistence.EntityManager;

public class SpaceDAO {

    /**
     * Get a space from the database with its code.
     * @param em The EntityManager to use to connect to the database.
     * @param code The space's code.
     * @return A Space object from the database.
     */
    public static Space getSpace(EntityManager em, Long code) {

        // Object to return
        Space space = em.getReference(Space.class, code);

        return space;
    }

    /**
     * Get the number of spaces with boats having their sails with a surface bigger or equal to the value entered.
     * @param em The EntityManager to use to connect to the database.
     * @param dockId The dock's ID.
     * @param surface The surface we want.
     * @return The number of spaces.
     */
    public static Long getSpaceWithSailboat(EntityManager em, Long dockId, Integer surface) {

        // SQL query
        String sql = "SELECT COUNT(*) FROM Space s, Dock d " +
                "WHERE s.boat.class = :type " +
                "AND s.boat.sailSurface>:criteria " +
                "AND d.dockCode=s.dock.dockCode " +
                "AND d.dockCode=:dockId";

        // Get result
        Long result = (Long) em.createQuery(sql)
                .setParameter("type", "Sailboat")
                .setParameter("criteria", surface)
                .setParameter("dockId", dockId)
                .getSingleResult();

        return result;
    }

    /**
     * Get the number of boats currently in the dock.
     * @param em The EntityManager to use to connect to the database.
     * @param dockId The dock's code.
     * @return The number of boats.
     */
    public static Long numberOfBoats(EntityManager em, Long dockId) {

        // SQL query
        String sql = "SELECT COUNT(*) FROM Space s " +
                "WHERE s.dock.dockCode=:dockId " +
                "AND s.boat IS NOT NULL";

        // Get result
        Long result = (Long) em.createQuery(sql)
                .setParameter("dockId", dockId)
                .getSingleResult();

        return result;
    }
}

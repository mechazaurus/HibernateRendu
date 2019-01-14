package com.polytech.bsm.hibernate.controller.dao;

import com.polytech.bsm.hibernate.model.Dock;
import com.polytech.bsm.hibernate.model.Space;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SpaceDAO
{
    public static Space getSpace(EntityManager em, Integer code) {

        // Object to return
        Space space = em.getReference(Space.class, Long.valueOf(code));

        return space;
    }

    public static void getSpaceWithSailboat(EntityManager em)
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter dock's id : ");
        long dockId = reader.nextLong();
        System.out.println("Enter sailboat's sailsurface : ");
        int surface = reader.nextInt();
        reader.close();
        String sql = "select Count(*) from Space s, Dock d where s.boat.class = :type AND s.boat.sailSurface>:criteria AND d.dockCode=s.dock.dockCode AND d.dockCode=:dockId";
        Long result = (Long) em.createQuery(sql)
                .setParameter("type", "Sailboat")
                .setParameter("criteria", surface)
                .setParameter("dockId", dockId)
                .getSingleResult();

        System.out.println("There is/are "+result+" spaces located at Dock number "+dockId+" assigned to Sailboats with sailsurface > "+surface);
    }
}

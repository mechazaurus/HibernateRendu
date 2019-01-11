package com.polytech.bsm.hibernate.controller.dao;

import com.polytech.bsm.hibernate.model.Dock;

import javax.persistence.*;

public class DockDAO {

    public static Dock getDock(EntityManager em, Integer code) {

        // Object to return
        Dock dock = em.getReference(Dock.class, Long.valueOf(code));

        return dock;
    }
}

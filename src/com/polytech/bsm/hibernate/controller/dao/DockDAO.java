package com.polytech.bsm.hibernate.controller.dao;

import com.polytech.bsm.hibernate.model.Boat;
import com.polytech.bsm.hibernate.model.Dock;

import javax.persistence.*;
import javax.print.Doc;
import java.util.List;

public class DockDAO {

    public static Dock getDock(EntityManager em, Integer code) {

        // Object to return
        Dock dock = em.getReference(Dock.class, Long.valueOf(code));

        return dock;
    }




    }


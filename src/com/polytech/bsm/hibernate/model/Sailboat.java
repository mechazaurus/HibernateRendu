package com.polytech.bsm.hibernate.model;

import javax.persistence.Entity;

/**
 * Class to create a sailboat
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 08/01/2019
 */

@Entity
public class Sailboat extends Boat {

    // Attributes
    private Integer sailSurface;

    // Constructors
    public Sailboat (String name, Integer weight, Integer surface) {
        super(name, weight);
        this.sailSurface = surface;
    }

    // Getters and setters
    public Integer getSailSurface() {
        return sailSurface;
    }
    public void setSailSurface(Integer sailSurface) {
        this.sailSurface = sailSurface;
    }
}

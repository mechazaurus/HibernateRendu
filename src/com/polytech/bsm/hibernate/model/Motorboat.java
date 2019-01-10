package com.polytech.bsm.hibernate.model;

import javax.persistence.Entity;

/**
 * Class to create a motorboat
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 08/01/2019
 */

@Entity
public class Motorboat extends Boat {

    // Attributes
    private Integer horsepower;

    // Constructors
    public Motorboat() {
        super();
    }

    public Motorboat(String name, Integer weight, Integer horsepower) {
        super(name, weight);
        this.horsepower = horsepower;
    }

    // Getters and setters
    public Integer getHorsepower() {
        return horsepower;
    }
    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }
}

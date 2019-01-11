package com.polytech.bsm.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class to create an owner
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 08/01/2019
 */

@Entity
public class Owner {

    // Attributes
    @Id
    @GenericGenerator(name="ownerID", strategy = "increment")
    @GeneratedValue(generator = "ownerID")
    private Long ownerID;
    private String name;
    private String address;
    // Associated boat
    @OneToOne
    private Boat boat;

    // Constructors
    public Owner() {

    }

    public Owner(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters and setters
    public Long getOwnerID() {
        return ownerID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boat getBoat() {
        return boat;
    }

    // ===================================================================
    //                            Other methods
    // ===================================================================

    /**
     * Adds a boat to the owner.
     * @param boatArg The owner's boat
     * @throws Exception If the owner already has a boat or if the boat already has an owner
     */
    public void addBoat(Boat boatArg) throws Exception {

        if (this.boat != null) {
            throw new Exception("Cette personne est déjà propriétaire d'un bateau.");
        } else {
            this.boat = boatArg;

            if (boatArg.getOwner() == null) {
                boatArg.addOwner(this);
            }
        }
    }

    /**
     * Removes the boat.
     * @param boatArg The boat to remove.
     */
    public void removeBoat(Boat boatArg)  {
        if (this.boat.equals(boatArg)) {
            this.boat = null;

            if (boatArg.getOwner().equals(this)) {
                boatArg.removeOwner(this);
            }
        }
    }
}

package com.polytech.bsm.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class to create a boat
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 08/01/2019
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Boat {

    // Attributes
    @Id
    @GenericGenerator(name="boatID", strategy = "increment")
    @GeneratedValue(generator = "boatID")
    private Long boatID;
    private String name;
    private Integer weight;
    // Space
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boat")
    private Space space;
    // Owner
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boat")
    private Owner owner;

    // Constructors
    public Boat (String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    // Getters and setters
    public Long getBoatID() {
        return boatID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Space getSpace() {
        return space;
    }
    public Owner getOwner() {
        return owner;
    }

    // Other methods

    public void addDock (Space space) throws Exception {

        if (this.space != null && this.space != space) {
            throw new Exception("Le bateau est déjà dans sur un autre quai.");
        } else {
            try {
                space.addBoat(this);
                this.space = space;
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    /**
     * Adds an owner to the boat only if the boat doesn't have an owner and the owner doesn't have a boat.
     * @param owner The boat's owner
     * @throws Exception If the boat already has an owner or if the owner already has a boat
     */
    public void addOwner (Owner owner) throws Exception {

        if (this.owner != null && this.owner != owner) {
            throw new Exception("Ce bateau possède déjà un propriétaire.");
        } else if (owner.getBoat() != null && owner.getBoat() != this) {
            throw new Exception("Cette personne est déjà propriétaire d'un bateau.");
        } else {
            this.owner = owner;

            if (owner.getBoat() == null) {
                owner.addBoat(this);
            }
        }
    }


}

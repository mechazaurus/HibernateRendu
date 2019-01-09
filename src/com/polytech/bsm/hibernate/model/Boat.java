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

    // ===================================================================
    //                            Other methods
    // ===================================================================

    /**
     * Adds a space to the boat.
     * @param space The space to add.
     * @throws Exception If the boat is already associated to a space and if the space is already associated to a boat.
     */
    public void addSpace(Space space) throws Exception {

        if (this.space != null && this.space != space) {
            throw new Exception("Le bateau est déjà associé à un autre quai.");
        } else if (this.space.equals(space)) {
            throw new Exception("Le bateau est déjà associé à ce quai.");
        } else if (space.getBoat() != null && space.getBoat() != this) {
            throw new Exception("L'emplacement est déjà associé à un autre bateau.");
        } else {
            this.space = space;

            if(space.getBoat() == null) {
                space.addBoat(this);
            }
        }
    }

    /**
     * Removes the space.
     * @param space The space to remove.
     */
    public void removeSpace(Space space) {
        if (this.space.equals(space)) {
            this.space = null;

            if (space.getBoat().equals(this)) {
                space.removeBoat(this);
            }
        }
    }

    /**
     * Adds an owner to the boat.
     * @param owner The boat's owner.
     * @throws Exception If the boat is already associated to an owner or if the owner is already associated to a boat.
     */
    public void addOwner(Owner owner) throws Exception {

        if (this.owner != null && this.owner != owner) {
            throw new Exception("Ce bateau possède déjà associé à un autre propriétaire.");
        } else if (this.owner.equals(owner)) {
            throw new Exception("Ce bateau est déjà associé à ce propriétaire.");
        } else if (owner.getBoat() != null && owner.getBoat() != this) {
            throw new Exception("Cette personne est déjà propriétaire d'un bateau.");
        } else {
            this.owner = owner;

            if (owner.getBoat() == null) {
                owner.addBoat(this);
            }
        }
    }

    /**
     * Removes the owner.
     * @param owner The owner to remove.
     * @throws Exception If the owner isn't the one associated to it.
     */
    public void removeOwner(Owner owner) {
        if (this.owner.equals(owner)) {
            this.owner = null;

            if (owner.getBoat().equals(this)) {
                owner.removeBoat(this);
            }
        }
    }
}

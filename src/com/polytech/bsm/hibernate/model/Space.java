package com.polytech.bsm.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class to create a space
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 08/01/2019
 */

@Entity
public class Space {

    // Attributes
    @Id
    @GenericGenerator(name="spaceCode", strategy = "increment")
    @GeneratedValue(generator = "spaceCode")
    private Long spaceCode;
    private Integer size;
    // Associated boat
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "space")
    private Boat boat;
    // Associated dock
    @ManyToOne
    private Dock dock;

    // Constructors
    public Space(Integer size) {
        this.size = size;
    }

    // Getters and setters
    public Long getCode() {
        return spaceCode;
    }
    public void setCode(Long code) {
        this.spaceCode = code;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Boat getBoat() {
        return boat;
    }
    public Dock getDock() {
        return dock;
    }

    // ===================================================================
    //                            Other methods
    // ===================================================================

    public void addBoat(Boat boat) throws Exception {

        if (this.boat != null && !this.boat.equals(boat)) {
            throw new Exception("L'emplacement est déjà associé à un autre bateau.");
        } else if (this.boat.equals(boat)) {
            throw new Exception("L'emplacement est déjà associé à ce bateau.");
        } else if (boat.getSpace() != null && boat.getSpace().equals(this)) {
            throw new Exception("Le bateau est déjà associé à un emplacement.");
        } else {
            this.boat = boat;

            if(!boat.getSpace().equals(this)) {
                boat.addSpace(this);
            }
        }
    }

    /**
     * Removes the boat.
     * @param boat The boat to remove.
     */
    public void removeBoat(Boat boat) {

        if(this.boat.equals(boat)) {
            this.boat = null;

            if(boat.getSpace().equals(this)) {
                boat.removeSpace(this);
            }
        }
    }

    /**
     * Adds a dock to the space.
     * @param dock The dock to add.
     * @throws Exception If the dock is already associated to this space or if the space is already associated to another dock.
     */
    public void addDock(Dock dock) throws Exception {

        if (this.dock != null && !this.dock.equals(dock)) {
            throw new Exception("L'emplacement est déjà associé à un autre quai.");
        } else if (this.dock.equals(dock)) {
            throw new Exception("L'emplacement est déjà associé à ce quai.");
        } else {
            this.dock = dock;

            if (!dock.isSpaceInList(this)) {
                dock.addSpace(this);
            }
        }
    }

    /**
     * Removes the dock.
     * @param dock The dock to remove.
     */
    public void removeDock(Dock dock) {
        if (this.dock.equals(dock)) {
            this.dock = null;

            if (dock.isSpaceInList(this)) {
                dock.removeSpace(this);
            }
        }
    }
}

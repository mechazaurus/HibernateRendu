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
    @OneToOne
    private Boat boat;
    // Associated dock
    @ManyToOne
    private Dock dock;

    // Constructors
    public Space() {

    }

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
    public void setDock(Dock dock) {
        this.dock = dock;
    }

    // ===================================================================
    //                            Other methods
    // ===================================================================

    public void addBoat(Boat boatArg) throws Exception {

        if (this.boat != null) {
            throw new Exception("L'emplacement est déjà associé à un autre bateau.");
        }  else {
            this.boat = boatArg;

            if(!boatArg.getSpace().equals(this)) {
                boatArg.addSpace(this);
            }
        }
    }

    /**
     * Removes the boat.
     * @param boatArg The boat to remove.
     */
    public void removeBoat(Boat boatArg) {

        if(this.boat.equals(boatArg)) {
            this.boat = null;

            if(boatArg.getSpace().equals(this)) {
                boatArg.removeSpace(this);
            }
        }
    }

    /**
     * Adds a dock to the space.
     * @param dockArg The dock to add.
     * @throws Exception If the dock is already associated to this space or if the space is already associated to another dock.
     */
    public void addDock(Dock dockArg) throws Exception {

        if (this.dock != null) {
            throw new Exception("L'emplacement est déjà associé à un autre quai.");
        } else {
            this.dock = dockArg;

            if (!dockArg.isSpaceInList(this)) {
                dockArg.addSpace(this);
            }
        }
    }

    /**
     * Removes the dock.
     * @param dockArg The dock to remove.
     */
    public void removeDock(Dock dockArg) {
        if (this.dock.equals(dockArg)) {
            this.dock = null;

            if (dockArg.isSpaceInList(this)) {
                dockArg.removeSpace(this);
            }
        }
    }
}

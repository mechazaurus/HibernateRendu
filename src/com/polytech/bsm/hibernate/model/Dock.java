package com.polytech.bsm.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to create a dock
 * @author Yohann BENETREAULT
 * @author Irteza SHEIKH MUHAMMAD
 * @version 0.1
 * @since 09/01/2019
 */

@Entity
public class Dock {

    // Attributes
    @Id
    @GenericGenerator(name="dockCode", strategy = "increment")
    @GeneratedValue(generator = "dockCode")
    private Long dockCode;
    private Integer spacesNumber;
    // Associated spaces
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dock")
    private List<Space> spaces;

    // Constructors
    public Dock() {
        spaces = new ArrayList<>(); // To avoid null pointer exceptions
    }

    public Dock(Integer number) {
        this.spacesNumber = number;
        spaces = new ArrayList<>(); // To avoid null pointer exceptions
    }

    // Getters (no setters here)
    public Long getDockCode() {
        return dockCode;
    }
    public Integer getSpacesNumber() {
        return spacesNumber;
    }
    public void setSpacesNumber(Integer spacesNumber) {
        this.spacesNumber = spacesNumber;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    // ===================================================================
    //                            Other methods
    // ===================================================================

    /**
     * Adds a space to the dock.
     * @param spaceArg The space to add.
     * @throws Exception If the space is already in the list or if the space is already associated to another dock.
     */
    public void addSpace(Space spaceArg) throws Exception {

        for(Space s : spaces) {
            if (s.equals(spaceArg)) {
                throw new Exception("L'emplacement est déjà associé à ce quai.");
            }
        }

        if (spaceArg.getDock() != null) {
            throw new Exception("L'emplacement est déjà associé à un autre quai.");
        } else {
            spaces.add(spaceArg);

            if (spaceArg.getDock() == null) {
                spaceArg.addDock(this);
            }
        }
    }

    /**
     * Removes a space associated to the dock.
     * @param spaceArg The space to remove.
     */
    public void removeSpace(Space spaceArg) {

        for(Space s : spaces) {
            if (s.equals(spaceArg)) {
                spaces.remove(s);

                if (spaceArg.getDock().equals(this)) {
                    spaceArg.removeDock(this);
                }
            }
        }
    }

    /**
     * Checks if the space is associated to the dock.
     * @param space The space to check.
     * @return True if the space is in the list, else false.
     */
    public Boolean isSpaceInList(Space space) {

        for(Space s : spaces) {
            if(s.equals(space)) {
                return true;
            }
        }

        return false;
    }
}

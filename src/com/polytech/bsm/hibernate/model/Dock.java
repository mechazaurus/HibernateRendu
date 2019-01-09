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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dock")
    private List<Space> spaces;

    // Constructors
    public Dock () {
        spaces = new ArrayList<>(); // To avoid null pointer exceptions
    }

    // Getters (no setters here)
    public Long getDockCode() {
        return dockCode;
    }
    public List<Space> getSpaces() {
        return spaces;
    }

    // Other methods

}

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
    // Boats list
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "space")
    private Boat boats;
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
    public Integer getSpace() {
        return size;
    }
    public void setSpace(Integer size) {
        this.size = size;
    }

    // Other methods

    public void addBoat (Boat boat) throws Exception {

        //TODO
    }
}

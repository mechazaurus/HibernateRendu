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
 * @since 08/01/2019
 */

@Entity
public class Dock {

    // Attributes
    @Id
    @GenericGenerator(name="codeGen", strategy = "increment")
    @GeneratedValue(generator = "codeGen")
    private Integer code;
    private Integer space;
    // Boats list
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dock")
    private List<Boat> boats;

    // Constructors
    public Dock () {
        boats = new ArrayList<>();
        space = 0; // By default the size is 0, so we can't add any boat until resize
    }

    public Dock(Integer space) {
        this.space = space;
        boats = new ArrayList<>();
    }

    // Getters and setters
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getSpace() {
        return space;
    }
    public void setSpace(Integer space) {
        this.space = space;
    }
}

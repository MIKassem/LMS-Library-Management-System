package Classes;

import java.util.*;

/**
 * faculty class represents a faculty in the university.
 */
public class faculty {

    //attributes
    private String name;
    private List<library> libraries;
    private Rules rules;

    //constructor

    /**
     * Constructor for faculty.
     *
     * @param nm Name of the faculty.
     */
    public faculty(String nm) {
        this.name = nm;
        this.libraries = new ArrayList<>();
        this.rules = new Rules();
    }

    //getters
    public String getname() {return this.name;}
    public List<library> getlibraries() {return this.libraries;}
    public Rules getRules() {return this.rules;}

    //setters
    public void setname(String nm) {this.name = nm;}
    //adding library and not setting it

    /**
     * Adds a library to the faculty.
     *
     * @param ly Library to be added.
     */
    public void addLibrary(library ly) {libraries.add(ly);}


}

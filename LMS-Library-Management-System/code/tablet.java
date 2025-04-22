package Classes;

/**
 * tablet class represents a Tablet in the library's electronic resources.
 */
public class tablet extends electronicResources{

    //attributes
    private String shelf;

    /**
     * Constructor for tablet.
     *
     * @param shlf Shelf where the tablet is located.
     * @param brd  Brand of the tablet.
     */
    public tablet(String shlf, String brd) {
        super(brd);
        this.shelf = shlf;
    }

    //getter and setter
    public String getShelf() {return shelf;}
    public void setShelf(String shelf) {this.shelf = shelf;}

}

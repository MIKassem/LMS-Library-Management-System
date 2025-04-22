package Classes;

/**
 * pc class represents a PC in the library's electronic resources.
 */
public class pc extends electronicResources{

    //attributes
    private String labRoom;

    /**
     * Constructor for pc.
     *
     * @param lbrm Lab room where the PC is located.
     * @param brd  Brand of the PC.
     */
    public pc(String lbrm, String brd) {
        super(brd);
        this.labRoom = lbrm;
    }

    //getter and setter
    public String getLabRoom() {return labRoom;}
    public void setLabRoom(String lbrm) {this.labRoom = lbrm;}
}

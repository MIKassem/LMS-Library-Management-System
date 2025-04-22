package Classes;

/**
 * electronicResources class represents electronic resources in the library.
 */
public abstract class electronicResources {

    //attributes
    protected String brand;
    protected boolean isInUse;
    protected java.util.Date usageEndTime;

    /**
     * Constructor for electronicResources.
     *
     * @param brd Brand of the electronic resource.
     */
    public electronicResources(String brd) {
        this.brand = brd;
        this.isInUse = false;
        this.usageEndTime = null;
    }

    //getter & setter
    public String getbrand() {return brand;}
    public void setbrand(String brd) {this.brand = brd;}

    /**
     * Allows a user to use the electronic resource.
     *
     * @param usr The user requesting to use the resource.
     */
    public void useResource(user usr) {
        if (!isInUse && usr instanceof student) {
            isInUse = true;
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.add(java.util.Calendar.HOUR, 1); // Usage duration is 1 hour
            usageEndTime = cal.getTime();
            System.out.println("Electronic resource is now in use by " + usr.getname() + " until " + usageEndTime);
        } else {
            System.out.println("Resource is already in use or user not authorized.");
        }
    }

    /**
     * Releases the electronic resource after use.
     */
    public void releaseResource() {
        isInUse = false;
        usageEndTime = null;
        System.out.println("Electronic resource is now available.");
    }

}

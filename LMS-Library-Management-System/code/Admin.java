package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin class represents the administrator responsible for managing library resources.
 */
public class Admin {

    //attributes
    private String name;
    private String address;
    private int phoneNumber;
    private String emailAddress;
    private String schedule;
    private library library;

    /**
     * Constructor for Admin.
     *
     * @param nm     Name of the admin.
     * @param adr    Address of the admin.
     * @param nbr    Phone number of the admin.
     * @param email  Email address of the admin.
     * @param sched  Schedule of the admin.
     * @param lr     Library managed by the admin.
     */
    public Admin(String nm, String adr, int nbr, String email, String sched, library lr) {
        this.name = nm;
        this.address = adr;
        this.phoneNumber = nbr;
        this.emailAddress = email;
        this.schedule = sched;
        this.library = lr;
    }

    //getters
    public String getname() {return this.name;}
    public String getaddress() {return this.address;}
    public int getphoneNumber() {return this.phoneNumber;}
    public String getemailAddress() {return this.emailAddress;}
    public String getschedule() {return this.schedule;}

    //setters
    public void setname(String nm) {this.name = nm;}
    public void setaddress(String adr) {this.address = adr;}
    public void setphoneNumber(int nbr) {this.phoneNumber = nbr;}
    public void setemailAddress(String email) {this.emailAddress = email;}
    public void setschedule(String sched) {this.schedule = sched;}

    //methods for books' managing

    /**
     * Adds a book to the library.
     *
     * @param bk Book to be added.
     */
    public void addBook(book bk) {
        library.addBook(bk);
        System.out.println("Book added: " + bk.gettitle());
    }

    /**
     * Deletes a book from the library.
     *
     * @param bk Book to be deleted.
     */
    public void deleteBook(book bk) {
        library.deleteBook(bk);
        System.out.println("Book deleted: " + bk.gettitle());
    }

    /**
     * Updates a book's information in the library.
     *
     * @param bk    Book to be updated.
     * @param field Field to update.
     * @param value New value for the field.
     */
    public void updateBook(book bk, String field, Object value) {
        library.updateBook(bk, field, value);
    }

    /**
     * Views all books in the library.
     *
     * @return List of all books.
     */
    public List<book> viewAllBooks() {
        return library.viewAllBooks();
    }

    //view all users

    /**
     * Views all users registered in the library.
     */
    public void viewAllUsers() {
        List<user> users = library.viewAllUsers();
        for (user usr : users) {
            System.out.println("User: " + usr.getname() + ", Email: " + usr.getemailAddress());
        }
    }

    //checking logs

    /**
     * Checks the borrow/return logs of the library.
     */
    public void checkLogs() {
        library.checkLogs();
    }

    //rules

    /**
     * Sets borrowing duration for resources in the faculty.
     *
     * @param resrcType 	Type of resource (e.g., book, meetingRoom).
     * @param usrType     	Type of user (e.g., student, professor).
     * @param duration     	Duration for which the resource can be borrowed.
     */
    public void setBorrowingDuration(String resrcType, String usrType, int duration) {
        library.getfaculty().getRules().setBorrowingDuration(resrcType, usrType, duration);
        System.out.println("Set borrowing duration for " + resrcType + " and " + usrType + ": " + duration + " days at faculty " + library.getfaculty().getname());
    }

    /**
     * Sets renewal option for resources in the faculty.
     *
     * @param resrcType Type of resource.
     * @param userType     Type of user.
     * @param canRenew     Whether the resource can be renewed.
     */
    public void setRenewalOption(String resrcType, String usrType, boolean canRenew) {
        library.getfaculty().getRules().setRenewalOption(resrcType, usrType, canRenew);
        System.out.println("Set renewal option for " + resrcType + " and " + usrType + ": " + (canRenew ? "allowed" : "not allowed") + " at faculty " + library.getfaculty().getname());
    }

    
    //stats

    /**
     * Views usage statistics of the library.
     */
    public void viewUsageStatistics() {
        library.generateUsageStatistics();
    }

    //populating

    /**
     * Adds a meeting room to the library.
     *
     * @param room Meeting room to be added.
     */
    public void addMeetingRoom(meetingRoom room) {
        library.addMeetingRoom(room);
        System.out.println("Meeting room added: " + room.getLocation());
    }

    /**
     * Adds an electronic resource to the library.
     *
     * @param resource Electronic resource to be added.
     */
    public void addElectronicResource(electronicResources resource) {
        library.addElectronicResource(resource);
        System.out.println("Electronic resource added: " + resource.getbrand());
    }
}

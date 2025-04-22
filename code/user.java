package Classes;

import java.util.*;

/**
 * user class represents a user in the library system.
 */
public class user {

    //attributes
    protected String name;
    protected String address;
    protected int phoneNumber;
    protected String emailAddress;
    protected faculty faculty;
    private int borrowCount;
    private List<book> reservedBooks;
    private Date penaltyEndDate;

    //borrowing history
    private List<borrowingRecord> borrowingHistory;

    /**
     * Constructor for user.
     *
     * @param nm    Name of the user.
     * @param adr   Address of the user.
     * @param nbr   Phone number of the user.
     * @param email Email address of the user.
     * @param fty   Faculty the user belongs to.
     */
    public user(String nm, String adr, int nbr, String email, faculty fty) {
        this.name = nm;
        this.address = adr;
        this.phoneNumber = nbr;
        this.emailAddress = email;
        this.faculty = fty;
        this.borrowingHistory = new ArrayList<>();
        this.borrowCount = 0;
        this.reservedBooks = new ArrayList<>();
        this.penaltyEndDate = null;
    }

    //getters
    public String getname() {return this.name;}
    public String getaddress() {return this.address;}
    public int getphoneNumber() {return this.phoneNumber;}
    public String getemailAddress() {return this.emailAddress;}
    public faculty getfaculty() {return this.faculty;}

    //setters
    public void setName(String nm) {this.name = nm;}
    public void setAddress(String adr) {this.address = adr;}
    public void setPhoneNumber(int nbr) {this.phoneNumber = nbr;}
    public void setEmailAddress(String email) {this.emailAddress = email;}
    public void setFaculty(faculty fty) {this.faculty = fty;}

    //methods for managing borrowing history

    /**
     * Adds a borrowing record to the user's history.
     *
     * @param record Borrowing record to be added.
     */
    public void addBorrowingRecord(borrowingRecord record) {borrowingHistory.add(record);}

    /**
     * Gets the user's borrowing history.
     *
     * @return List of borrowing records.
     */
    public List<borrowingRecord> getBorrowingHistory() {return borrowingHistory;}

    //methods for borrow count managing
    public void incrementBorrowCount() {this.borrowCount++;}
    public int getBorrowCount() {return this.borrowCount;}

    //methods for reservations managing

    /**
     * Adds a reserved book to the user's list.
     *
     * @param bk Book to be added.
     */
    public void addReservedBook(book bk) {reservedBooks.add(bk);}

    /**
     * Gets the list of books reserved by the user.
     *
     * @return List of reserved books.
     */
    public List<book> getReservedBooks(){return reservedBooks;}

    //penalty methods

    /**
     * Checks if the user can borrow books (not under penalty).
     *
     * @return True if the user can borrow, false otherwise.
     */
    public boolean canBorrow() {
        if (penaltyEndDate == null) return true;
        return new Date().after(penaltyEndDate);
    }

    /**
     * Applies a late penalty to the user.
     *
     * @param days Number of days the penalty lasts.
     */
    public void applyLatePenalty(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        penaltyEndDate = cal.getTime();
        System.out.println("Late penalty applied to " + name + " until " + penaltyEndDate);
    }

    /**
     * Gets the end date of the user's penalty.
     *
     * @return Penalty end date.
     */
    public Date getPenaltyEndDate() {
        return penaltyEndDate;
    }
}

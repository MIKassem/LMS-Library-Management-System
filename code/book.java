package Classes;

import java.util.*;

/**
 * book class represents a book in the library.
 */
public class book {

    //attributes
    private String title;
    private String description;
    private int isbn;
    private String author;
    private int publicationYear;
    private String genre;
    private boolean available;
    private boolean isPhysical;
    private boolean isBorrowable;
    private boolean isReserved;
    private Queue<user> reservationList;
    private user currentBorrower;
    private int borrowCount;
    private List<borrowingRecord> borrowingHistory;
    private boolean isRenewed;
    private int copyCount;


    /**
     * Constructor for book.
     *
     * @param ttl    Title of the book.
     * @param desc   Description of the book.
     * @param nbr    ISBN number of the book.
     * @param atr    Author of the book.
     * @param year   Publication year of the book.
     * @param gen    Genre of the book.
     * @param isPhys Whether the book is physical.
     * @param isBrw  Whether the book is borrowable.
     */
    public book(String ttl, String desc, int nbr, String atr, int year, String gen, boolean isPhys, boolean isBrw, int copies) {
        this.title = ttl;
        this.description = desc;
        this.isbn = nbr;
        this.author = atr;
        this.publicationYear = year;
        this.genre = gen;
        this.available = true;
        this.isPhysical = isPhys;
        this.isBorrowable = isBrw;
        this.isReserved = false;
        this.reservationList = new LinkedList<>();
        this.currentBorrower = null;
        this.borrowCount = 0;
        this.borrowingHistory = new ArrayList<>();
        this.isRenewed = false;
        this.copyCount = copies;
    }

    //setters
    public void setisbn(int nbr) {this.isbn = nbr;}
    public void setavailable(boolean av) {this.available = av;}
    public void setisBorrowable(boolean b) {this.isBorrowable = b;}
    public void setisReserved(boolean r) {this.isReserved = r;}

    //getters
    public String gettitle() {return this.title;}
    public String getauthor() {return this.author;}
    public int getisbn() {return this.isbn;}
    public String getgenre() {return this.genre;}
    public boolean isAvailable() {return available;}
    public boolean isPhysical() {return isPhysical;}
    public boolean isBorrowable() {return isBorrowable;}
    public boolean isReserved() {return isReserved;}

    //availability

    /**
     * Checks if the book is available for borrowing.
     *
     * @return True if available, false otherwise.
     */
    public boolean checkAvailability() {
        return this.isBorrowable && !this.isReserved;
    }

    //borrowing

    /**
     * Allows a user to borrow the book from the library.
     *
     * @param usr The user borrowing the book.
     * @param lry The library from which the book is borrowed.
     */
    public void borrowBook(user usr, library lry) {
        if (!usr.canBorrow()) {
            System.out.println("User " + usr.getname() + " cannot borrow books until " + usr.getPenaltyEndDate());
            return;
        }
        if (copyCount > 0 && checkAvailability() && usr.getfaculty() == lry.getfaculty()) {
            isReserved = true;
            currentBorrower = usr;
            System.out.println("Book borrowed: " + title + " by " + usr.getname());

            // Create borrowing record
            Date borrowDate = new Date();
            int borrowDuration = lry.getBorrowingDuration("book", usr.getClass().getSimpleName());
            Calendar cal = Calendar.getInstance();
            cal.setTime(borrowDate);
            cal.add(Calendar.DAY_OF_MONTH, borrowDuration);
            Date dueDate = cal.getTime();

            borrowingRecord record = new borrowingRecord(this, usr, borrowDate, dueDate);
            usr.addBorrowingRecord(record);
            this.addBorrowingRecord(record);

            this.incrementBorrowCount();
            usr.incrementBorrowCount();
            
            lry.addLog("Borrow", "Book '" + title + "' borrowed by " + usr.getname());


        } else {
            System.out.println("Book not available or user not authorized!");
            addToReservationList(usr, lry);
        }
    }

    //returning the book

    /**
     * Allows the current borrower to return the book.
     */
    public void returnBook(library lry) {
        if (currentBorrower != null) {
            String borrowerName = currentBorrower.getname();
            //find borrowing record + set return date
        	copyCount++;
            borrowingRecord foundRecord = null;
            for (borrowingRecord record : currentBorrower.getBorrowingHistory()) {
                if (record.getBook() == this && record.getReturnDate() == null) {
                    record.setReturnDate(new Date());
                    foundRecord = record;
                    break;
                }
            }

            if (foundRecord != null) {
                //check if return is late
                if (foundRecord.isOverdue()) {
                    //apply late penalty
                    currentBorrower.applyLatePenalty(7); //this is a 7 days penalty
                    System.out.println("Late return! " + currentBorrower.getname() + " cannot borrow books until " + currentBorrower.getPenaltyEndDate());
                }
            }

            System.out.println("Book returned: " + title + " by " + currentBorrower.getname());
            isReserved = false;
            currentBorrower = null;
            isRenewed = false; //reset the renewal status upon return
            processNextReservation(lry);
            
            lry.addLog("Return", "Book '" + title + "' returned by " + borrowerName);

        } else {
            System.out.println("Book was not borrowed.");
        }
    }

    //reservations method

    /**
     * Adds a user to the reservation list for the book.
     *
     * @param usr The user to be added.
     */
    public void addToReservationList(user usr, library lry) {
        reservationList.add(usr);
        usr.addReservedBook(this);
        System.out.println("User " + usr.getname() + " added to reservation list for book: " + title);
        lry.addLog("Reservation", usr.getname() + " reserved book '" + title + "'");
    }

    /**
     * Processes the next user in the reservation list.
     */
    public void processNextReservation(library lry) {
        if (!reservationList.isEmpty()) {
            user nextUser = reservationList.poll();
            nextUser.getReservedBooks().remove(this);
            System.out.println("Notified user: " + nextUser.getname() + " that book is now available.");
            lry.addLog("Notification", "Notified user: " + nextUser.getname() + " that book '" + title + "' is now available.");
        }
    }

    //the borrowing managing methods
    public void incrementBorrowCount() {this.borrowCount++;}
    public int getBorrowCount() {return this.borrowCount;}
    public void addBorrowingRecord(borrowingRecord record) {borrowingHistory.add(record);}
    public List<borrowingRecord> getBorrowingHistory() {return borrowingHistory;}

    /**
     * Allows a professor to renew a borrowed book once.
     *
     * @param usr The user requesting the renewal.
     * @param lry The library where the book was borrowed.
     */
    public void renewBook(user usr, library lry) {
        boolean canRenew = lry.canRenew("book", usr instanceof professor ? "professor" : "student");
        if (currentBorrower == usr && !isRenewed && canRenew) {
            isRenewed = true;
            // Extend due date
            borrowingRecord record = null;
            for (borrowingRecord br : usr.getBorrowingHistory()) {
                if (br.getBook() == this && br.getReturnDate() == null) {
                    record = br;
                    break;
                }
            }
            if (record != null) {
                int renewDuration = lry.getBorrowingDuration("book", usr instanceof professor ? "professor" : "student");
                Calendar cal = Calendar.getInstance();
                cal.setTime(record.getDueDate());
                cal.add(Calendar.DAY_OF_MONTH, renewDuration);
                record.setDueDate(cal.getTime());
                System.out.println("Book renewed: " + title + " by " + usr.getname() + " until " + record.getDueDate());
            }
            lry.addLog("Renewal", "Book '" + title + "' renewed by " + usr.getname());
        } else {
            System.out.println("Renewal not allowed.");
        }
    }

}

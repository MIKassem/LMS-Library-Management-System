package Classes;

import java.util.Date;

/**
 * borrowingRecord class represents a borrowing record in the library system.
 */
public class borrowingRecord {

    private book bookx;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private user borrower;

    /**
     * Constructor for borrowingRecord.
     *
     * @param bk         Book being borrowed.
     * @param usr        User who borrowed the book.
     * @param borrowDate Date when the book was borrowed.
     * @param dueDate    Date when the book is due.
     */
    public borrowingRecord(book bk, user usr, Date borrowDate, Date dueDate) {
        this.bookx = bk;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.borrower = usr;
    }

    // Getters and Setters
    public book getBook() {return this.bookx;}
    public Date getBorrowDate() {return this.borrowDate;}
    public Date getDueDate() {return this.dueDate;}
    public Date getReturnDate() {return this.returnDate;}
    public user getBorrower() {return this.borrower;}

    public void setReturnDate(Date returnDate) {this.returnDate = returnDate;}

    /**
     * Sets the due date for the borrowing record.
     *
     * @param dueDate New due date.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Check if overdue

    /**
     * Checks if the borrowing record is overdue.
     *
     * @return True if overdue, false otherwise.
     */
    public boolean isOverdue() {
        Date currentDate = (returnDate == null) ? new Date() : returnDate;
        return currentDate.after(dueDate);
    }

}

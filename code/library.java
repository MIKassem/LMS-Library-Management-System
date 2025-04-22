package Classes;

import java.util.*;

/**
 * library class represents a library in the university.
 */
public class library {

    //attributes
    private String name;
    private String location;
    private faculty faculty;
    private List<book> books;
    private List<user> users;
    public List<meetingRoom> meetingRooms;
    public List<electronicResources> electronicResources;
    private List<String> logs = new ArrayList<>();

    //constructor

    /**
     * Constructor for library.
     *
     * @param nm   Name of the library.
     * @param lctn Location of the library.
     * @param fty  Faculty the library belongs to.
     */
    public library(String nm, String lctn, faculty fty) {
        this.name = nm;
        this.location = lctn;
        this.faculty = fty;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.meetingRooms = new ArrayList<>();
        this.electronicResources = new ArrayList<>();
        faculty.addLibrary(this);
    }

    //getters
    public String getname() {return this.name;}
    public faculty getfaculty() {return this.faculty;}

    //methods for books

    /**
     * Adds a book to the library.
     *
     * @param bk Book to be added.
     */
    public void addBook(book bk) {
        books.add(bk);
    }

    /**
     * Deletes a book from the library.
     *
     * @param bk Book to be deleted.
     */
    public void deleteBook(book bk) {
        books.remove(bk);
    }

    /**
     * Updates a book's information in the library.
     *
     * @param bk    Book to be updated.
     * @param field Field to update.
     * @param value New value for the field.
     */
    public void updateBook(book bk, String field, Object value) {
        //new type of built in functions i found to better hold this function
        switch (field.toLowerCase()) {
            case "isbn":
                if (value instanceof Integer) {
                    bk.setisbn((Integer) value);
                }
                break;
            case "availability":
                if (value instanceof Boolean) {
                    bk.setavailable((Boolean) value);
                }
                break;
            case "isborrowable":
                if (value instanceof Boolean) {
                    bk.setisBorrowable((Boolean) value);
                }
                break;
            case "isreserved":
                if (value instanceof Boolean) {
                    bk.setisReserved((Boolean) value);
                }
                break;
            default:
                System.out.println("Invalid field: " + field);
        }
    }

    /**
     * Views all books in the library.
     *
     * @return List of all books.
     */
    public List<book> viewAllBooks() {
        return books;
    }

    //view all users

    /**
     * Views all users registered in the library.
     *
     * @return List of all users.
     */
    public List<user> viewAllUsers() {
        return users;
    }

    //add users

    /**
     * Adds a user to the library.
     *
     * @param usr User to be added.
     */
    public void addUser(user usr) {
        if (usr.getfaculty() == this.faculty) {
            users.add(usr);
        } else {
            System.out.println("User does not belong to this faculty.");
        }
    }

    //checking logs

    /**
     * Checks the borrow/return logs of the library.
     */
    public void checkLogs() {
        System.out.println("Activity Logs for " + this.name + " Library:");
        for (String log : logs) {
            System.out.println(log);
        }
        System.out.println("\nBorrowing and Returning Records:");
        for (user usr : users) {
            List<borrowingRecord> history = usr.getBorrowingHistory();
            for (borrowingRecord record : history) {
                String status = (record.getReturnDate() == null) ? "Borrowed" : "Returned";
                System.out.println("- Book: " + record.getBook().gettitle() +
                                   ", User: " + usr.getname() +
                                   ", Status: " + status +
                                   ", Borrow Date: " + record.getBorrowDate() +
                                   (status.equals("Returned") ? ", Return Date: " + record.getReturnDate() : ""));
            }
        }
    }
    

    /**
     * Searches for books in the library based on a keyword.
     *
     * @param keyword Keyword to search for.
     * @return List of books matching the keyword.
     */
    public List<book> search(String keyword) {
        List<book> searchResults = new ArrayList<>();
        for(book b : books) {
            //holding cases
            if (b.gettitle().contains(keyword) ||
                b.getauthor().contains(keyword) ||
                b.getgenre().contains(keyword) ||
                String.valueOf(b.getisbn()).contains(keyword))
            {
                searchResults.add(b);
            }
        }
        return searchResults;
    }

    //know the overdue books

    /**
     * Retrieves a list of overdue books in the library.
     *
     * @return List of borrowing records that are overdue.
     */
    public List<borrowingRecord> getOverdueBooks() {
        List<borrowingRecord> overdueRecords = new ArrayList<>();
        for (user usr : users) {
            for (borrowingRecord record : usr.getBorrowingHistory()) {
                if (record.isOverdue() && record.getReturnDate() == null) {
                    overdueRecords.add(record);
                }
            }
        }
        return overdueRecords;
    }

    //rules

    /**
     * Gets the borrowing duration for a resource type and user type.
     *
     * @param resourceType Type of resource.
     * @param userType     Type of user.
     * @return Borrowing duration in days.
     */
    public int getBorrowingDuration(String resourceType, String userType) {
        return faculty.getRules().getBorrowingDuration(resourceType, userType);
    }

    /**
     * Checks if a user can renew a resource.
     *
     * @param resourceType Type of resource.
     * @param userType     Type of user.
     * @return True if the user can renew, false otherwise.
     */
    public boolean canRenew(String resourceType, String userType) {
        return faculty.getRules().canRenew(resourceType, userType);
    }
    
    
    /**
     * Generates usage statistics for the library.
     */
    public void generateUsageStatistics() {

        // Total number of borrowings
        int totalBorrowings = 0;
        for (book bk : books) {
            totalBorrowings += bk.getBorrowCount();
        }
        System.out.println("Total Number of Borrowings: " + totalBorrowings);

        // Popular Books
        System.out.println("\nPopular Books:");
        books.sort((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()));
        for (book bk : books) {
            if (bk.getBorrowCount() > 0) {
                System.out.println("- " + bk.gettitle() + " borrowed " + bk.getBorrowCount() + " times");
            }
        }

        // Frequent Borrowers
        System.out.println("\nFrequent Borrowers:");
        users.sort((u1, u2) -> Integer.compare(u2.getBorrowCount(), u1.getBorrowCount()));
        for (user usr : users) {
            if (usr.getBorrowCount() > 0) {
                System.out.println("- " + usr.getname() + " borrowed " + usr.getBorrowCount() + " times");
            }
        }

        // Overdue Books
        System.out.println("\nOverdue Books:");
        List<borrowingRecord> overdueRecords = getOverdueBooks();
        for (borrowingRecord record : overdueRecords) {
            long overdueDays = (new Date().getTime() - record.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
            System.out.println("- " + record.getBook().gettitle() + " borrowed by " + record.getBorrower().getname() + ", overdue by " + overdueDays + " days");
        }
    }



    /**
     * Adds a meeting room to the library.
     *
     * @param room Meeting room to be added.
     */
    public void addMeetingRoom(meetingRoom room) {
        meetingRooms.add(room);
    }

    /**
     * Adds an electronic resource to the library.
     *
     * @param resource Electronic resource to be added.
     */
    public void addElectronicResource(electronicResources resource) {
        electronicResources.add(resource);
    }
    
    
    /**
     * Adds a log
     * 
     * @param action action to be done
     * @param details details of the action
     */
    public void addLog(String action, String details) {
        String logEntry = action + ": " + details + " on " + new Date();
        logs.add(logEntry);
    }

}

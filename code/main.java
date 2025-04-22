package Classes;

import java.util.*;

/**
 * Main class to simulate the Library Management System.
 */
public class main {

    /**
     * Main method to run the Library Management System simulation.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        //create faculties
        faculty engineering = new faculty("Engineering");
        faculty science = new faculty("Science");
        faculty arts = new faculty("Arts");

        //create libraries for each faculty
        library[] engLibraries = {
            new library("Engineering Library 1", "Campus A", engineering),
            new library("Engineering Library 2", "Campus B", engineering),
            new library("Engineering Library 3", "Campus C", engineering)
        };

        library[] sciLibraries = {
            new library("Science Library 1", "Campus A", science),
            new library("Science Library 2", "Campus B", science),
            new library("Science Library 3", "Campus C", science)
        };

        library[] artLibraries = {
            new library("Arts Library 1", "Campus A", arts),
            new library("Arts Library 2", "Campus B", arts),
            new library("Arts Library 3", "Campus C", arts)
        };

        //define arrays of real brand names for PCs and Tablets
        String[] pcBrands = {"Dell", "HP", "Lenovo", "Asus", "Acer", "MSI", "Apple", "Samsung", "Toshiba"};
        String[] tabletBrands = {"Apple", "Samsung", "Microsoft", "Lenovo", "Amazon", "Huawei", "Google", "Asus", "LG"};

        //define arrays of real book titles and authors
        String[] bookTitles = {
            "Introduction to Algorithms", "Design Patterns", "Clean Code",
            "Artificial Intelligence: A Modern Approach", "The C Programming Language",
            "Effective Java", "Java Concurrency in Practice", "Head First Design Patterns",
            "Cracking the Coding Interview"
        };

        String[] bookAuthors = {
            "Thomas H. Cormen", "Erich Gamma", "Robert C. Martin",
            "Stuart Russell", "Brian W. Kernighan", "Joshua Bloch",
            "Brian Goetz", "Eric Freeman", "Gayle Laakmann McDowell"
        };

        //create admins and assign them to libraries
        Admin[] admins = new Admin[9];
        String[] adminNames = {"Admin Eng1", "Admin Eng2", "Admin Eng3", "Admin Sci1", "Admin Sci2", "Admin Sci3", "Admin Art1", "Admin Art2", "Admin Art3"};
        library[] allLibraries = {engLibraries[0], engLibraries[1], engLibraries[2], sciLibraries[0], sciLibraries[1], sciLibraries[2], artLibraries[0], artLibraries[1], artLibraries[2]};
        for (int i = 0; i < 9; i++) {
            admins[i] = new Admin(
                adminNames[i],
                "Address " + (i+1),
                100000000 + i,
                "admin" + (i+1) + "@library.com",
                "9-5",
                allLibraries[i]
            );
        }

        //populate libraries with resources
        for (int i = 0; i < 9; i++) {
            Admin admin = admins[i];
            library lib = allLibraries[i];

            // Add books with real titles and authors
            admin.addBook(new book(
                bookTitles[i % bookTitles.length],
                "Description of " + bookTitles[i % bookTitles.length],
                10000 + i,
                bookAuthors[i % bookAuthors.length],
                2000 + i,
                "Computer Science",
                true,
                true,
                3
            ));
            admin.addBook(new book(
                bookTitles[(i+1) % bookTitles.length],
                "Description of " + bookTitles[(i+1) % bookTitles.length],
                10000 + i + 100,
                bookAuthors[(i+1) % bookAuthors.length],
                2000 + i,
                "Computer Science",
                true,
                true,
                2
            ));

            //add meeting rooms
            admin.addMeetingRoom(new meetingRoom("Conference Room " + (i+1) + "A", 5550000 + i));
            admin.addMeetingRoom(new meetingRoom("Conference Room " + (i+1) + "B", 5551000 + i));

            //add electronic resources with real brand names
            admin.addElectronicResource(new pc("Lab " + (i+1) + "A", pcBrands[i % pcBrands.length]));
            admin.addElectronicResource(new tablet("Shelf " + (i+1) + "A", tabletBrands[i % tabletBrands.length]));
        }

        //configure borrowing rules and assign them to resources
        for (Admin admin : admins) {
            //set borrowing durations
            admin.setBorrowingDuration("book", "student", 3);
            admin.setBorrowingDuration("book", "professor", 7);

            //set renewal options
            admin.setRenewalOption("book", "student", false);
            admin.setRenewalOption("book", "professor", true);
        }

        //create students and professors per faculty
        student engStudent = new student(
            "Alice",
            "Address Eng Student",
            101010101,
            "alice@engstudent.com",
            engineering,
            1001
        );

        professor engProfessor = new professor(
            "Dr. Smith",
            "Address Eng Professor",
            202020202,
            "drsmith@engfaculty.com",
            engineering,
            "Professor"
        );

        student sciStudent = new student(
            "Bob",
            "Address Sci Student",
            111111111,
            "bob@scistudent.com",
            science,
            2002
        );

        professor sciProfessor = new professor(
            "Dr. Johnson",
            "Address Sci Professor",
            222222222,
            "drjohnson@scifaculty.com",
            science,
            "Professor"
        );

        student artStudent = new student(
            "Carol",
            "Address Art Student",
            121212121,
            "carol@artstudent.com",
            arts,
            3003
        );

        professor artProfessor = new professor(
            "Dr. Williams",
            "Address Art Professor",
            232323232,
            "drwilliams@artfaculty.com",
            arts,
            "Professor"
        );

        //add users to their respective libraries
        engLibraries[0].addUser(engStudent);
        engLibraries[0].addUser(engProfessor);

        sciLibraries[0].addUser(sciStudent);
        sciLibraries[0].addUser(sciProfessor);

        artLibraries[0].addUser(artStudent);
        artLibraries[0].addUser(artProfessor);

        //display simulation header
        System.out.println("=== Library Management System Simulation ===\n");

        //students and professors search for a book
        System.out.println("--- Students and Professors Search for Books ---");

        //for Engineering
        System.out.println("\nEngineering Faculty:");
        List<book> engSearchResults = engLibraries[0].search("Code");
        for (book b : engSearchResults) {
            System.out.println("- Found Book: " + b.gettitle() + " by " + b.getauthor());
        }

        //for Science
        System.out.println("\nScience Faculty:");
        List<book> sciSearchResults = sciLibraries[0].search("Algorithms");
        for (book b : sciSearchResults) {
            System.out.println("- Found Book: " + b.gettitle() + " by " + b.getauthor());
        }

        //for Arts
        System.out.println("\nArts Faculty:");
        List<book> artSearchResults = artLibraries[0].search("Design");
        for (book b : artSearchResults) {
            System.out.println("- Found Book: " + b.gettitle() + " by " + b.getauthor());
        }

        System.out.println("\n--- End of Search Results ---\n");

        //students and professors borrow books multiple times to increase borrow counts
        System.out.println("--- Students and Professors Borrow Books Multiple Times ---");

        //create a list of users for borrowing activities
        List<user> engUsers = Arrays.asList(engStudent, engProfessor);
        List<user> sciUsers = Arrays.asList(sciStudent, sciProfessor);
        List<user> artUsers = Arrays.asList(artStudent, artProfessor);

        //simulate borrowing activities
        Random random = new Random();
        for (int i = 0; i < 5; i++) { // Simulate 5 rounds of borrowing
            //engineering Library
            user engUser = engUsers.get(random.nextInt(engUsers.size()));
            book engBook = engLibraries[0].viewAllBooks().get(random.nextInt(engLibraries[0].viewAllBooks().size()));
            engBook.borrowBook(engUser, engLibraries[0]);

            //science Library
            user sciUser = sciUsers.get(random.nextInt(sciUsers.size()));
            book sciBook = sciLibraries[0].viewAllBooks().get(random.nextInt(sciLibraries[0].viewAllBooks().size()));
            sciBook.borrowBook(sciUser, sciLibraries[0]);

            //arts Library
            user artUser = artUsers.get(random.nextInt(artUsers.size()));
            book artBook = artLibraries[0].viewAllBooks().get(random.nextInt(artLibraries[0].viewAllBooks().size()));
            artBook.borrowBook(artUser, artLibraries[0]);
        }

        System.out.println("\n--- End of Borrowing Activities ---\n");

        //simulate returns and create overdue books
        System.out.println("--- Simulating Returns and Overdue Books ---");

        //engineering Student returns books
        for (borrowingRecord record : engStudent.getBorrowingHistory()) {
            //randomly decide if the return is late
            boolean isLate = random.nextBoolean();
            if (isLate) {
                //set due date to past to make it overdue
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -5);
                record.setDueDate(cal.getTime());
            }
            record.getBook().returnBook(engLibraries[0]);
        }

        //engineering Professor returns books
        for (borrowingRecord record : engProfessor.getBorrowingHistory()) {
            boolean isLate = random.nextBoolean();
            if (isLate) {
                // Set due date to past to make it overdue
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -8);
                record.setDueDate(cal.getTime());
            }
            record.getBook().returnBook(engLibraries[0]);
        }

        //similar returns for Science and Arts users
        for (user usr : sciUsers) {
            for (borrowingRecord record : usr.getBorrowingHistory()) {
                boolean isLate = random.nextBoolean();
                if (isLate) {
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DAY_OF_MONTH, -6);
                    record.setDueDate(cal.getTime());
                }
                record.getBook().returnBook(sciLibraries[0]);
            }
        }

        for (user usr : artUsers) {
            for (borrowingRecord record : usr.getBorrowingHistory()) {
                boolean isLate = random.nextBoolean();
                if (isLate) {
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DAY_OF_MONTH, -7);
                    record.setDueDate(cal.getTime());
                }
                record.getBook().returnBook(artLibraries[0]);
            }
        }

        System.out.println("\n--- End of Return Activities ---\n");
        
        
        System.out.println();
        System.out.println("\n--- Usage Statistics ---\n");

        // generate usage statistics
        System.out.println("--- Generating Usage Statistics for Engineering Library ---");
        engLibraries[0].generateUsageStatistics();

        System.out.println("\n--- Generating Usage Statistics for Science Library ---");
        sciLibraries[0].generateUsageStatistics();

        System.out.println("\n--- Generating Usage Statistics for Arts Library ---");
        artLibraries[0].generateUsageStatistics();
        
        System.out.println();
        System.out.println("\n--- Checking Logs ---\n");

        // check logs for Engineering Library 1
        System.out.println("\nEngineering Library 1 Logs:");
        engLibraries[0].checkLogs();

        //check logs for Science Library 1
        System.out.println("\nScience Library 1 Logs:");
        sciLibraries[0].checkLogs();

        //check logs for Arts Library 1
        System.out.println("\nArts Library 1 Logs:");
        artLibraries[0].checkLogs();

        System.out.println("\n=== End of Simulation ===");
    }
}

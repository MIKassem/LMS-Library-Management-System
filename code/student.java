package Classes;

/**
 * Student class represents a student user in the library system.
 */
public class student extends user{

    //attributes
    private int id;

    /**
     * Constructor for student.
     *
     * @param nm   Name of the student.
     * @param adr  Address of the student.
     * @param nbr  Phone number of the student.
     * @param email Email address of the student.
     * @param fty  Faculty the student belongs to.
     * @param idx  ID of the student.
     */
    public student(String nm, String adr, int nbr, String email, faculty fty, int idx) {
        super(nm, adr, nbr, email, fty);
        this.id = idx;
    }

    //getters
    public String getname() {return this.name;}
    public String getaddress() {return this.address;}
    public int getphoneNumber() {return this.phoneNumber;}
    public String getemailAddress() {return this.emailAddress;}
    public faculty getfaculty() {return this.faculty;}
    public int getid() {return this.id;}

    //setters
    public void setname(String nm) {this.name = nm;}
    public void setaddress(String adr) {this.address = adr;}
    public void setphoneNumber(int nbr) {this.phoneNumber = nbr;}
    public void setemailAddress(String email) {this.emailAddress = email;}
    public void setschedule(faculty fty) {this.faculty = fty;}
    public void setid(int idx) {this.id = idx;}

}

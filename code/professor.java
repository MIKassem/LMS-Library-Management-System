package Classes;

/**
 * Professor class represents a professor user in the library system.
 */
public class professor extends user{

    //attributes
    private String rank;

    /**
     * Constructor for professor.
     *
     * @param nm   Name of the professor.
     * @param adr  Address of the professor.
     * @param nbr  Phone number of the professor.
     * @param email Email address of the professor.
     * @param fty  Faculty the professor belongs to.
     * @param rk   Rank of the professor (Professor or Lecturer).
     */
    public professor(String nm, String adr, int nbr, String email, faculty fty, String rk) {
        super(nm, adr, nbr, email, fty);
        this.rank = rk;
    }

    //getters
    public String getname() {return this.name;}
    public String getaddress() {return this.address;}
    public int getphoneNumber() {return this.phoneNumber;}
    public String getemailAddress() {return this.emailAddress;}
    public faculty getfaculty() {return this.faculty;}
    public String getrank() {return this.rank;}

    //setters
    public void setname(String nm) {this.name = nm;}
    public void setaddress(String adr) {this.address = adr;}
    public void setphoneNumber(int nbr) {this.phoneNumber = nbr;}
    public void setemailAddress(String email) {this.emailAddress = email;}
    public void setschedule(faculty fty) {this.faculty = fty;}
    public void setrank(String rk) {this.rank = rk;}

}

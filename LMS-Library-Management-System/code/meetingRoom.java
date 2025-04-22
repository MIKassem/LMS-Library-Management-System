package Classes;

/**
 * meetingRoom class represents a meeting room in the library.
 */
public class meetingRoom {

    //attributes
    private String location;
    private int phoneNumber;
    private boolean isReserved;
    private java.util.Date reservationEndTime;

    /**
     * Constructor for meetingRoom.
     *
     * @param lctn Location of the meeting room.
     * @param nmbr Phone number of the meeting room.
     */
    public meetingRoom(String lctn, int nmbr) {
        this.location = lctn;
        this.phoneNumber = nmbr;
        this.isReserved = false;
        this.reservationEndTime = null;
    }

    //getters
    public String getLocation() {return location;}
    public int getPhoneNumber() {return phoneNumber;}

    //setters
    public void setPhoneNumber(int nbr) {this.phoneNumber = nbr;}
    public void setLocation(String lctn) {this.location = lctn;}

    /**
     * Reserves the meeting room for a user.
     *
     * @param usr      The user reserving the room.
     * @param duration Duration of the reservation in hours.
     */
    public void reserveRoom(user usr, int duration) {
        if (!isReserved && duration <= 1) { // Maximum duration is 1 hour
            isReserved = true;
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.add(java.util.Calendar.HOUR, duration);
            reservationEndTime = cal.getTime();
            System.out.println("Meeting room reserved by " + usr.getname() + " until " + reservationEndTime);
        } else {
            System.out.println("Room is already reserved or duration exceeds limit.");
        }
    }

    /**
     * Releases the meeting room after use.
     */
    public void releaseRoom() {
        isReserved = false;
        reservationEndTime = null;
        System.out.println("Meeting room is now available.");
    }

}

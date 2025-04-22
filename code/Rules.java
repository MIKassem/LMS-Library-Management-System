package Classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Rules class manages borrowing rules for library resources.
 * It allows setting and retrieving borrowing durations and renewal options
 * based on the type of resource and user.
 */
public class Rules {

	//attributes
    private Map<String, Integer> borrowingDurations;
    private Map<String, Boolean> renewalOptions;

    /**
     * Constructor for the Rules class.
     * Initializes the borrowing durations and renewal options.
     */
    public Rules() {
        borrowingDurations = new HashMap<>();
        renewalOptions = new HashMap<>();
    }


    /**
     * Sets the borrowing duration for a specific resource type and user type.
     *
     * @param resourceType The type of resource (e.g., book, tablet).
     * @param userType     The type of user (e.g., student, professor).
     * @param days         The number of days the resource can be borrowed.
     */
    public void setBorrowingDuration(String resourceType, String userType, int days) {
        String key = resourceType.toLowerCase() + "_" + userType.toLowerCase();
        borrowingDurations.put(key, days);
    }

    /**
     * Retrieves the borrowing duration for a specific resource type and user type.
     *
     * @param resourceType The type of resource (e.g., book, tablet).
     * @param userType     The type of user (e.g., student, professor).
     * @return The number of days the resource can be borrowed, or 0 if not set.
     */
    public int getBorrowingDuration(String resourceType, String userType) {
        String key = resourceType.toLowerCase() + "_" + userType.toLowerCase();
        return borrowingDurations.getOrDefault(key, 0);
    }

    /**
     * Sets the renewal option for a specific resource type and user type.
     *
     * @param resourceType The type of resource (e.g., book, tablet).
     * @param userType     The type of user (e.g., student, professor).
     * @param canRenew     Whether the resource can be renewed.
     */
    public void setRenewalOption(String resourceType, String userType, boolean canRenew) {
        String key = resourceType.toLowerCase() + "_" + userType.toLowerCase();
        renewalOptions.put(key, canRenew);
    }

    /**
     * Checks whether a specific resource type and user type have renewal options.
     *
     * @param resourceType The type of resource (e.g., book, tablet).
     * @param userType     The type of user (e.g., student, professor).
     * @return True if the resource can be renewed, false otherwise.
     */
    public boolean canRenew(String resourceType, String userType) {
        String key = resourceType.toLowerCase() + "_" + userType.toLowerCase();
        return renewalOptions.getOrDefault(key, false);
    }
}

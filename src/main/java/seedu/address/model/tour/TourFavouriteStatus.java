package seedu.address.model.tour;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Favourite Status of a tour.
 */
public class TourFavouriteStatus {
    // This message constraint should not be triggered as favourite status is to be edited via commands alone and
    // not by user input.
    public static final String MESSAGE_CONSTRAINTS =
            "Tour Favourite Status should only be 'true' or 'false (default if unspecified)'.";

    public static final String VALID_TOUR_FAVOURITE_STATUS_TRUE = "true";
    public static final String VALID_TOUR_FAVOURITE_STATUS_FALSE = "false";

    /*
     * The string must be 'true' or 'false' (case-insensitive).
     */
    public static final String VALIDATION_REGEX = "(?i)^(true|false)$";

    public final boolean isTourFavourite;

    /**
     * Constructs a {@code Tour Favourite Status}.
     *
     * @param isTourFavourite A valid input string for Favourite Status.
     */
    public TourFavouriteStatus(String isTourFavourite) {
        checkArgument(isValidTourFavouriteStatus(isTourFavourite), MESSAGE_CONSTRAINTS);
        this.isTourFavourite = isTourFavourite.equals("true") || isTourFavourite.equals("TRUE");
    }

    /**
     * Returns true if a given string is a valid Tour Favourite Status.
     */
    public static boolean isValidTourFavouriteStatus(String test) {
        if (test.isEmpty()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isTourFavourite ? "Favourite" : "Regular";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TourFavouriteStatus otherTourFavouriteStatus)) {
            return false;
        }

        return isTourFavourite == otherTourFavouriteStatus.isTourFavourite;
    }

    @Override
    public int hashCode() {
        String isTourFavouriteString = isTourFavourite ? "true" : "false";
        return isTourFavouriteString.hashCode();
    }
}

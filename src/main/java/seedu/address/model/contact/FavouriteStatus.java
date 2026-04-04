package seedu.address.model.contact;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Favourite Status of a contact.
 */
public class FavouriteStatus {
    // This message constraint should not be triggered as favourite status is to be edited via commands alone and
    // not by user input.
    public static final String MESSAGE_CONSTRAINTS =
            "Favourite Status should only be 'true' or 'false (default if unspecified)'.";

    /*
     * The string must be 'true' or 'false' (case-insensitive).
     */
    public static final String VALIDATION_REGEX = "(?i)^(true|false)$";

    public final boolean isFavourite;

    /**
     * Constructs a {@code Favourite Status}.
     *
     * @param isFavourite A valid input string for Favourite Status.
     */
    public FavouriteStatus(String isFavourite) {
        checkArgument(isValidFavouriteStatus(isFavourite), MESSAGE_CONSTRAINTS);
        this.isFavourite = isFavourite.equals("true") || isFavourite.equals("TRUE");
    }

    /**
     * Returns true if a given string is a valid Favourite Status.
     */
    public static boolean isValidFavouriteStatus(String test) {
        if (test.isEmpty()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isFavourite ? "Favourite" : "Regular";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FavouriteStatus otherFavouriteStatus)) {
            return false;
        }

        return isFavourite == otherFavouriteStatus.isFavourite;
    }

    @Override
    public int hashCode() {
        String isFavouriteString = isFavourite ? "true" : "false";
        return isFavouriteString.hashCode();
    }
}

package seedu.address.model.contact;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Favorite Status of a contact.
 */
public class FavoriteStatus {
    // This message constraint should not be triggered as favorite status is to be edited via commands alone and
    // not by user input.
    public static final String MESSAGE_CONSTRAINTS =
            "Favorite Status should only be 'true' or 'false (default if unspecified)'.";

    /*
     * The string must be 'true' or 'false' (case-insensitive).
     */
    public static final String VALIDATION_REGEX = "(?i)^(true|false)$";

    public final boolean isFavorite;

    /**
     * Constructs a {@code Favorite Status}.
     *
     * @param isFavorite A valid input string for Favorite Status.
     */
    public FavoriteStatus(String isFavorite) {
        checkArgument(isValidFavoriteStatus(isFavorite), MESSAGE_CONSTRAINTS);
        this.isFavorite = isFavorite.equals("true") || isFavorite.equals("TRUE");
    }

    /**
     * Returns true if a given string is a valid Favorite Status.
     */
    public static boolean isValidFavoriteStatus(String test) {
        if (test.isEmpty()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isFavorite ? "Favorite" : "Regular";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FavoriteStatus otherFavoriteStatus)) {
            return false;
        }

        return isFavorite == otherFavoriteStatus.isFavorite;
    }

    @Override
    public int hashCode() {
        String isFavoriteString = isFavorite ? "true" : "false";
        return isFavoriteString.hashCode();
    }
}

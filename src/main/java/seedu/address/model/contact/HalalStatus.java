package seedu.address.model.contact;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Halal Status of an Fnb contact.
 */
public class HalalStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Halal Status should only be 'true' or 'false' ('false' if unspecified).";

    /*
     * The string must be 'true' or 'false' (case-insensitive).
     */
    public static final String VALIDATION_REGEX = "(?i)^(true|false)$";

    public final boolean isHalal;

    /**
     * Constructs a {@code Halal Status}.
     *
     * @param isHalal A valid input string for Halal Status.
     */
    public HalalStatus(String isHalal) {
        checkArgument(isValidHalalStatus(isHalal), MESSAGE_CONSTRAINTS);
        this.isHalal = isHalal.equals("true") || isHalal.equals("TRUE");
    }

    /**
     * Returns true if a given string is a valid Halal Status.
     */
    public static boolean isValidHalalStatus(String test) {
        if (test.isEmpty()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isHalal ? "Halal" : "Non-Halal";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HalalStatus otherHalalStatus)) {
            return false;
        }

        return isHalal == otherHalalStatus.isHalal;
    }

    @Override
    public int hashCode() {
        String isHalalString = isHalal ? "true" : "false";
        return isHalalString.hashCode();
    }
}

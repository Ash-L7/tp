package seedu.address.model.tour;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;


/**
 * Represents a tour package, which is identified by its String name
 */
public class Tour {
    public static final String MESSAGE_CONSTRAINTS = "Tour names should not have special characters,"
            + " and should not be blank.";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9 ]+$";

    public final String tourName;
    private final TourFavouriteStatus isTourFavourite;

    /**
     * Constructs a {@code Tour} with the given name.
     *
     * @param tourName the name of the tour
     */
    public Tour(String tourName) {
        requireNonNull(tourName);
        checkArgument(isValidTourName(tourName), MESSAGE_CONSTRAINTS);
        this.tourName = tourName;
        this.isTourFavourite = new TourFavouriteStatus("false");
    }

    /**
     * Constructs a {@code Tour} with the given name and specified Tour Favourite status.
     *
     * @param tourName the name of the tour
     * @param isTourFavourite favourite status of the tour
     */
    public Tour(String tourName, TourFavouriteStatus isTourFavourite) {
        requireNonNull(tourName);
        checkArgument(isValidTourName(tourName), MESSAGE_CONSTRAINTS);
        this.tourName = tourName;
        this.isTourFavourite = isTourFavourite;
    }

    /**
     * Returns true if a given string is a valid tour name.
     */
    public static boolean isValidTourName(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the name of the tour.
     *
     * @return the tour name as a {@code String}
     */
    public String getTourName() {
        return tourName;
    }


    /**
     * Returns a copy of the tour with its values edited.
     */
    public Tour setTourFavouriteStatus(TourFavouriteStatus isTourFavourite) {
        String updatedName = this.getTourName();

        return new Tour(updatedName, isTourFavourite);
    }

    public TourFavouriteStatus getTourFavouriteStatus() {
        return this.isTourFavourite;
    }

    public boolean isTourFavourite() {
        return this.isTourFavourite.isTourFavourite;
    }
    /**
     * Returns the hash code of this tour, based on its name.
     *
     * @return hash code derived from the tour name
     */
    @Override
    public int hashCode() {
        return Objects.hash(tourName);
    }

    /**
     * Returns true if both tours have the same name.
     * This defines equality between two {@code Tour} objects.
     *
     * @param other the object to compare to
     * @return true if {@code other} is a {@code Tour} with the same name
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Tour tour) {
            return this.tourName.equals(tour.getTourName());
        }
        return false;
    }

    @Override
    public String toString() {
        return tourName;
    }
}

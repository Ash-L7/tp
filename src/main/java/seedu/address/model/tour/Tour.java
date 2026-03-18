package seedu.address.model.tour;

import java.util.Objects;

/**
 * Represents a tour package, which is identified by its String name
 */
public class Tour {
    String name = "";

    /**
    * Constructs a {@code Tour} with the given name.
    *
    * @param name the name of the tour
    */
    public Tour(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the tour.
     *
     * @return the tour name as a {@code String}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hash code of this tour, based on its name.
     *
     * @return hash code derived from the tour name
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
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
            return this.name.equals(tour.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Tour %s", name);
    }
}

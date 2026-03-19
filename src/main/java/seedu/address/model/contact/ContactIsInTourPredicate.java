package seedu.address.model.contact;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tour.Tour;

/**
 * Tests that a {@code Contact} is assigned to a given {@code Tour}.
 */
public class ContactIsInTourPredicate implements Predicate<Contact> {
    private final Tour tour;

    public ContactIsInTourPredicate(Tour tour) {
        this.tour = tour;
    }

    @Override
    public boolean test(Contact contact) {
        return contact.isInTour(tour);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ContactIsInTourPredicate)) {
            return false;
        }
        return tour.equals(((ContactIsInTourPredicate) other).tour);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tour", tour).toString();
    }
}

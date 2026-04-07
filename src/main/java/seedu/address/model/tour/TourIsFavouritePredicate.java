package seedu.address.model.tour;

import java.util.function.Predicate;

/**
 * Tests that a {@code Tour} is added to favourites.
 */
public class TourIsFavouritePredicate implements Predicate<Tour> {
    public TourIsFavouritePredicate() {
    }

    @Override
    public boolean test(Tour tour) {
        return tour.isFavourite();
    }
}

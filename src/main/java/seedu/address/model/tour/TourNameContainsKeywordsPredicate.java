package seedu.address.model.tour;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Tour}'s {@code Name} matches any of the keywords given.
 */
public class TourNameContainsKeywordsPredicate implements Predicate<Tour> {
    private final List<String> keywords;

    public TourNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tour tour) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tour.getTourName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TourNameContainsKeywordsPredicate)) {
            return false;
        }

        TourNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (TourNameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Contact> {
    public static final NameContainsKeywordsPredicate ANY_NAME_ALLOWED_PREDICATE =
            new AnyNameAllowedPredicate(List.of());
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

    private static class AnyNameAllowedPredicate extends NameContainsKeywordsPredicate {
        private AnyNameAllowedPredicate(List<String> keywords) {
            super(keywords);
        }

        @Override
        public boolean test(Contact contact) {
            return true;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof AnyNameAllowedPredicate;
        }
    }
}

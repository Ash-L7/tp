package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.TYPE_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.TYPE_FNB;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests if a {@code Contact} is of a certain type.
 */
public class ContactTypePredicate implements Predicate<Contact> {
    public static final ContactTypePredicate ANY_TYPE_ALLOWED_PREDICATE = new AnyTypeAllowedPredicate("any");
    private final String type;

    public ContactTypePredicate(String type) {
        this.type = type;
    }

    @Override
    public boolean test(Contact contact) {
        switch (type) {
        case TYPE_PERSON:
            return contact instanceof Person;
        case TYPE_FNB:
            return contact instanceof Fnb;
        case TYPE_ACCOMMODATION:
            return contact instanceof Accommodation;
        case TYPE_ATTRACTION:
            return contact instanceof Attraction;
        default:
            assert false : "Unknown contact type string should have been validated by parser.";
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ContactTypePredicate p) {
            return this.type.equals(p.type);
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("type", type).toString();
    }

    private static class AnyTypeAllowedPredicate extends ContactTypePredicate {
        private AnyTypeAllowedPredicate(String type) {
            super(type);
        }

        @Override
        public boolean test(Contact contact) {
            return true;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof ContactTypePredicate.AnyTypeAllowedPredicate;
        }
    }
}

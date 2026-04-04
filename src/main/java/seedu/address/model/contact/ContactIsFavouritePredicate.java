package seedu.address.model.contact;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Contact} is assigned to a given {@code Tour}.
 */
public class ContactIsFavouritePredicate implements Predicate<Contact> {
    public ContactIsFavouritePredicate() {
    }

    @Override
    public boolean test(Contact contact) {
        return contact.isFavourite();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}

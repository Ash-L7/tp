package seedu.address.model.contact;

import java.util.function.Predicate;

/**
 * Tests that a {@code Contact} is assigned to a given {@code Tour}.
 */
public class ContactIsFavoritePredicate implements Predicate<Contact> {
    public ContactIsFavoritePredicate() {
    }

    @Override
    public boolean test(Contact contact) {
        return contact.isFavorite();
    }
}

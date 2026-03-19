package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

    /**
     * Returns an unmodifiable view of the tours list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Tour> getTourList();

}

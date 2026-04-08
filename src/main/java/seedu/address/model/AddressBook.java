package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.NotImplementedException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.tour.Tour;
import seedu.address.model.tour.UniqueTourList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameContact comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueContactList contacts;
    private final UniqueTourList tours;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
        tours = new UniqueTourList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Contacts in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Replaces the content of the tour list with {@code tours}.
     * {@code tours} must not contain duplicate tours.
     */
    public void setTours(List<Tour> tours) {
        this.tours.setTours(tours);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setContacts(newData.getContactList());
        setTours(newData.getTourList());
    }

    //// contact-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a contact to the address book.
     * The contact must not already exist in the address book.
     */
    public void addContact(Contact p) {
        contacts.add(p);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not be the same as
     * another existing contact in the address book.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        contacts.setContact(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    //// tour-level operations

    /**
     * Returns true if a tour with the same identity as {@code tour} exists in the address book.
     */
    public boolean hasTour(Tour tour) {
        requireNonNull(tour);
        return tours.contains(tour);
    }

    /**
     * Adds a tour to the address book.
     * The tour must not already exist in the address book.
     */
    public void addTour(Tour tour) {
        tours.add(tour);
    }

    /**
     * Replaces the given tour {@code target} in the list with {@code editedTour}.
     * {@code target} must exist in the address book.
     * The tour identity of {@code editedTour} must not be the same as
     * another existing tour in the address book.
     */
    public void setTour(Tour target, Tour editedTour) {
        requireNonNull(editedTour);

        tours.setTour(target, editedTour);
    }

    /**
     * Removes {@code tour} from this {@code AddressBook}.
     * {@code tour} must exist in the address book.
     */
    public void removeTour(Tour tour) {
        tours.remove(tour);
        contacts.asUnmodifiableObservableList().stream()
                .filter(contact -> contact.isInTour(tour))
                .forEach(contact -> {
                    EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
                    HashSet<Tour> editedTours = new HashSet<>(contact.getTours());
                    editedTours.remove(tour);
                    editContactDescriptor.setTours(editedTours);
                    contacts.setContact(contact, contact.edit(editContactDescriptor));
                });
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contacts", contacts)
                .add("tours", tours)
                .toString();
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tour> getTourList() {
        return tours.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return contacts.equals(otherAddressBook.contacts) && tours.equals(otherAddressBook.tours);
    }

    @Override
    public int hashCode() {
        throw new NotImplementedException("AddressBook::hashCode is not implemented yet. Need to account for tours.");
        // return contacts.hashCode();
    }
}

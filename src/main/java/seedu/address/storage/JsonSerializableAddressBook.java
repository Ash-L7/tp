package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contact(s).";
    public static final String MESSAGE_DUPLICATE_TOUR = "Tours list contains duplicate tour(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedTour> tours = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given contacts and tours.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                       @JsonProperty("tours") List<JsonAdaptedTour> tours) {
        this.contacts.addAll(contacts);
        this.tours.addAll(tours);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).toList());
        tours.addAll(source.getTourList().stream().map(JsonAdaptedTour::new).toList());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (addressBook.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            addressBook.addContact(contact);
        }
        for (JsonAdaptedTour jsonAdaptedTour : tours) {
            Tour tour = jsonAdaptedTour.toModelType();
            if (addressBook.hasTour(tour)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TOUR);
            }
            addressBook.addTour(tour);
        }
        return addressBook;
    }

}

package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;

/**
 * Represents a Contact in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Contact {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Tour> tours = new HashSet<>();
    private final FavouriteStatus isFavourite;

    /**
     * Constructs a {@code Contact}.
     * Forms the base for instantiating the Contact subclasses.
     * Every field must be present and not null.
     */
    public Contact(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Tour> tours) {
        requireAllNonNull(name, phone, email, address, tags, tours);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.tours.addAll(tours);
        this.isFavourite = new FavouriteStatus("false");
    }

    /**
     * Constructs a {@code Contact} with specified Favourite status.
     */
    public Contact(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Tour> tours,
                   FavouriteStatus isFavourite) {
        requireAllNonNull(name, phone, email, address, tags, tours, isFavourite);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.tours.addAll(tours);
        this.isFavourite = isFavourite;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if the contact is in the given tour.
     */
    public boolean isInTour(Tour tour) {
        return tours.contains(tour);
    }

    /**
     * Returns an immutable tour set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tour> getTours() {
        return Collections.unmodifiableSet(tours);
    }

    /**
     * Returns a string with all the associated tours of the contact.
     */
    public String getToursString() {
        if (this.getTours().isEmpty()) {
            return "Tours: None";
        }
        return "Tours: " + getTours().stream()
                .map(Tour::toString)
                .collect(Collectors.joining(" | "));
    }

    public FavouriteStatus getFavouriteStatus() {
        return this.isFavourite;
    }

    public boolean isFavourite() {
        return this.isFavourite.isFavourite;
    }

    /**
     * Returns true if both contacts have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getName().equals(getName());
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    public abstract Contact edit(EditContactDescriptor editContactDescriptor);

    /**
     * Returns the type of contact.
     */
    public abstract String getType();

    /**
     * Returns a list of details specific to contact type.
     */
    public abstract List<String> getTypeSpecificDetails();


    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return name.equals(otherContact.name)
                && phone.equals(otherContact.phone)
                && email.equals(otherContact.email)
                && address.equals(otherContact.address)
                && tags.equals(otherContact.tags)
                && tours.equals(otherContact.tours)
                && isFavourite.equals(otherContact.isFavourite);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("favouriteStatus", isFavourite)
                .toString();
    }
}

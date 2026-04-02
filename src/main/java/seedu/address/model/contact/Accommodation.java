package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_ACCOMMODATION;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;


/**
 * Represents an Accommodation Contact in the address book.
 * Guarantees: default number of stars (THREE_STAR) if unspecified.
 */
public class Accommodation extends Contact {
    private final AccommodationStars stars;

    /**
     * Constructs an {@code Accommodation} contact with default number of stars (THREE_STAR).
     */
    public Accommodation(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.stars = new AccommodationStars(AccommodationStars.Stars.THREE_STAR);
    }

    /**
     * Constructs an {@code Accommodation} contact with specified number of stars.
     *
     * @param stars The number of stars of the accommodation.
     */
    public Accommodation(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                         AccommodationStars stars, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.stars = stars;
    }

    /**
     * Constructs an {@code Accommodation} contact with specified Favourite status.
     *
     * @param isFavourite The Favourite status.
     */
    public Accommodation(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                         AccommodationStars stars, Set<Tour> tours, FavouriteStatus isFavourite) {
        super(name, phone, email, address, tags, tours, isFavourite);
        this.stars = stars;
    }

    public AccommodationStars getStars() {
        return stars;
    }

    @Override
    public String getType() {
        return TYPE_ACCOMMODATION;
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Accommodation otherAccommodation)) {
            return false;
        }
        return super.equals(otherAccommodation)
                && stars.equals(otherAccommodation.stars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    @Override
    public Contact edit(EditCommand.EditContactDescriptor editAccommodationDescriptor) {
        Name updatedName = editAccommodationDescriptor.getName().orElse(getName());
        Phone updatedPhone = editAccommodationDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editAccommodationDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editAccommodationDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editAccommodationDescriptor.getTags().orElse(getTags());
        AccommodationStars updatedStars = editAccommodationDescriptor.getStars().orElse(getStars());
        Set<Tour> updatedTours = editAccommodationDescriptor.getTours().orElse(getTours());
        FavouriteStatus updatedFavouriteStatus = editAccommodationDescriptor.getFavouriteStatus().orElse(
                getFavouriteStatus());

        return new Accommodation(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedStars,
                updatedTours, updatedFavouriteStatus);
    }

    @Override
    public List<String> getTypeSpecificDetails() {
        return List.of("Number of Stars: " + stars.toString(), getToursString());
    }
}

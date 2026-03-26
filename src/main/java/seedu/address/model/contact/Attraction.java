package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_ATTRACTION;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;


/**
 * Represents an Attraction Contact in the address book.
 * Guarantees: default operating hours (8am to 10pm) if unspecified.
 */
public class Attraction extends Contact {
    private final OpeningHour openingHour;
    private final ClosingHour closingHour;

    /**
     * Constructs an {@code Attraction} contact with default operating hours (8am to 10pm).
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.openingHour = new OpeningHour("08:00");
        this.closingHour = new ClosingHour("22:00");
    }

    /**
     * Constructs an {@code Attraction} contact with default opening hour (8am).
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags, ClosingHour closingHour,
                      Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.openingHour = new OpeningHour("08:00");
        this.closingHour = closingHour;
    }

    /**
     * Constructs an {@code Attraction} contact with default closing hour (10pm).
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags, OpeningHour openingHour,
                      Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.openingHour = openingHour;
        this.closingHour = new ClosingHour("22:00");
    }

    /**
     * Constructs an {@code Attraction} contact with specified operating hours.
     * @param openingHour The opening hours of the attraction.
     * @param closingHour The closing hours of the attraction.
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                      OpeningHour openingHour, ClosingHour closingHour, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    /**
     * Constructs an {@code Attraction} contact with specified operating hours and Favorite Status.
     * @param openingHour The opening hours of the attraction.
     * @param closingHour The closing hours of the attraction.
     * @param isFavorite The Favorite Status.
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                      OpeningHour openingHour, ClosingHour closingHour, Set<Tour> tours, FavoriteStatus isFavorite) {
        super(name, phone, email, address, tags, tours, isFavorite);
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public OpeningHour getOpeningHour() {
        return openingHour;
    }

    public ClosingHour getClosingHour() {
        return closingHour;
    }

    /**
     * Returns the operating (opening to closing) hours of the attraction.
     */
    public LocalTime[] getOperatingHours() {
        return new LocalTime[]{ this.openingHour.getOpeningHour(), this.closingHour.getClosingHour() };
    }

    @Override
    public String getType() {
        return TYPE_ATTRACTION;
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    @Override
    public Contact edit(EditCommand.EditContactDescriptor editAttractionDescriptor) {
        Name updatedName = editAttractionDescriptor.getName().orElse(getName());
        Phone updatedPhone = editAttractionDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editAttractionDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editAttractionDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editAttractionDescriptor.getTags().orElse(getTags());
        OpeningHour updatedOpeningHour = editAttractionDescriptor.getOpeningHour().orElse(getOpeningHour());
        ClosingHour updatedClosingHour = editAttractionDescriptor.getClosingHour().orElse(getClosingHour());
        Set<Tour> updatedTours = editAttractionDescriptor.getTours().orElse(getTours());
        FavoriteStatus updatedFavoriteStatus = editAttractionDescriptor.getFavoriteStatus().orElse(getFavoriteStatus());

        return new Attraction(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedOpeningHour, updatedClosingHour, updatedTours, updatedFavoriteStatus);
    }

    @Override
    public List<String> getTypeSpecificDetails() {
        return List.of(
                "Operating Hours: " + openingHour.toString() + " to " + closingHour.toString(),
                getToursString());
    }
}



package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_FNB;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;


/**
 * Represents an Fnb Contact in the address book.
 * Guarantees: default Halal status (false) if unspecified.
 */
public class Fnb extends Contact {
    private final HalalStatus isHalal;

    /**
     * Constructs an {@code Fnb} contact with default Halal status (false).
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.isHalal = new HalalStatus("false");
    }

    /**
     * Constructs an {@code Fnb} contact with specified Halal status.
     * @param isHalal A Halal status.
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
               HalalStatus isHalal, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
        this.isHalal = isHalal;
    }

    /**
     * Constructs an {@code Fnb} contact with specified Halal and Favourite status.
     * @param favouriteStatus A Favourite status.
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
               HalalStatus isHalal, Set<Tour> tours, FavouriteStatus favouriteStatus) {
        super(name, phone, email, address, tags, tours, favouriteStatus);
        this.isHalal = isHalal;
    }

    public boolean isHalal() {
        return this.isHalal.isHalal;
    }

    public HalalStatus getHalalStatus() {
        return this.isHalal;
    }

    public Set<Tour> getTours() {
        return super.getTours();
    }

    @Override
    public String getType() {
        return TYPE_FNB;
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
        if (!(other instanceof Fnb otherFnb)) {
            return false;
        }
        return super.equals(otherFnb)
                && isHalal.equals(otherFnb.isHalal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isHalal);
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    @Override
    public Contact edit(EditCommand.EditContactDescriptor editFnbDescriptor) {
        Name updatedName = editFnbDescriptor.getName().orElse(getName());
        Phone updatedPhone = editFnbDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editFnbDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editFnbDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editFnbDescriptor.getTags().orElse(getTags());
        HalalStatus updatedHalalStatus = editFnbDescriptor.getHalalStatus().orElse(getHalalStatus());
        Set<Tour> updatedTours = editFnbDescriptor.getTours().orElse(getTours());
        FavouriteStatus updatedFavouriteStatus = editFnbDescriptor.getFavouriteStatus().orElse(getFavouriteStatus());

        return new Fnb(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedHalalStatus, updatedTours, updatedFavouriteStatus);
    }

    @Override
    public List<String> getTypeSpecificDetails() {
        return List.of("Halal Status: " + isHalal.toString(), getToursString());
    }
}

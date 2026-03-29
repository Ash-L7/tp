package seedu.address.testutil;

import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.AccommodationStars;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building Accommodation objects.
 */
public class AccommodationBuilder extends ContactBuilder {
    protected AccommodationStars stars;

    /**
     * Sets the {@code AccommodationStars} of the {@code Accommodation} that we are building.
     */
    public ContactBuilder withStars(String stars) {
        this.stars = new AccommodationStars(stars);
        return this;
    }

    @Override
    public Contact build() {
        return new Accommodation(name, phone, email, address, tags, stars, tours, favoriteStatus);
    }
}

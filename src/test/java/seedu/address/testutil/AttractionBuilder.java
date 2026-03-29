package seedu.address.testutil;

import seedu.address.model.contact.Attraction;
import seedu.address.model.contact.ClosingHour;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.OpeningHour;

/**
 * A utility class to help with building Person objects.
 */
public class AttractionBuilder extends ContactBuilder {
    protected OpeningHour openingHour;
    protected ClosingHour closingHour;

    /**
     * Sets the {@code OpeningHour} and {@code ClosingHour} of the {@code Attraction} that we are building.
     */
    public ContactBuilder withOperatingHours(String openingHour, String closingHour) {
        this.openingHour = new OpeningHour(openingHour);
        this.closingHour = new ClosingHour(closingHour);
        return this;
    }

    @Override
    public Contact build() {
        return new Attraction(name, phone, email, address, tags, openingHour, closingHour, tours, favoriteStatus);
    }
}

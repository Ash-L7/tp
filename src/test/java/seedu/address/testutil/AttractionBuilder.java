package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLOSING_HOUR_DEFAULT_ATTRACTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOUR_DEFAULT_ATTRACTION;

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
     * Creates a {@code ContactBuilder} with the default details.
     */
    public AttractionBuilder() {
        super();
        this.openingHour = new OpeningHour(VALID_OPENING_HOUR_DEFAULT_ATTRACTION);
        this.closingHour = new ClosingHour(VALID_CLOSING_HOUR_DEFAULT_ATTRACTION);
    }

    /**
     * Sets the {@code OpeningHour} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withOpeningHour(String openingHour) {
        this.openingHour = new OpeningHour(openingHour);
        return this;
    }

    /**
     * Sets the {@code ClosingHour} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withClosingHour(String closingHour) {
        this.closingHour = new ClosingHour(closingHour);
        return this;
    }
    @Override
    public Contact build() {
        return new Attraction(name, phone, email, address, tags, openingHour, closingHour, tours, favouriteStatus);
    }
}

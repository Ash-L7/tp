package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_HALAL_STATUS_FALSE_FNB;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Fnb;
import seedu.address.model.contact.HalalStatus;

/**
 * A utility class to help with building Fnb objects.
 */
public class FnbBuilder extends ContactBuilder {
    protected HalalStatus halalStatus;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public FnbBuilder() {
        super();
        this.halalStatus = new HalalStatus(VALID_HALAL_STATUS_FALSE_FNB);
    }

    /**
     * Sets the {@code HalalStatus} of the {@code Fnb} that we are building.
     */
    public FnbBuilder withHalalStatus(String halalStatus) {
        this.halalStatus = new HalalStatus(halalStatus);
        return this;
    }

    @Override
    public Contact build() {
        return new Fnb(name, phone, email, address, tags, halalStatus, tours, favouriteStatus);
    }
}

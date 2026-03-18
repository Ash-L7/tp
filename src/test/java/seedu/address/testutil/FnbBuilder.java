package seedu.address.testutil;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Fnb;
import seedu.address.model.contact.HalalStatus;

/**
 * A utility class to help with building Fnb objects.
 */
public class FnbBuilder extends ContactBuilder {
    protected HalalStatus halalStatus;

    /**
     * Sets the {@code HalalStatus} of the {@code Fnb} that we are building.
     */
    public ContactBuilder withHalalStatus(String halalStatus) {
        this.halalStatus = new HalalStatus(halalStatus);
        return this;
    }

    @Override
    public Contact build() {
        return new Fnb(name, phone, email, address, tags, halalStatus, tours);
    }
}

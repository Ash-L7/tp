package seedu.address.testutil;

import java.util.Collections;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Person;


/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder extends ContactBuilder {
    @Override
    public Contact build() {
<<<<<<< fix/resolve-save-contact-and-tour-clashes
        return new Person(name, phone, email, address, tags, tours);
=======
        return new Person(name, phone, email, address, tags, Collections.emptySet());
>>>>>>> master
    }
}

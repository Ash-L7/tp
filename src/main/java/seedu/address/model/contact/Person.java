package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person extends Contact {

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Tour> tours) {
        super(name, phone, email, address, tags, tours);
    }

    @Override
    public Contact edit(EditContactDescriptor editContactDescriptor) {
        Name updatedName = editContactDescriptor.getName().orElse(getName());
        Phone updatedPhone = editContactDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editContactDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editContactDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(getTags());
        Set<Tour> updatedTours = editContactDescriptor.getTours().orElse(getTours());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedTours);
    }

    @Override
    public String getType() {
        return TYPE_PERSON;
    }
}

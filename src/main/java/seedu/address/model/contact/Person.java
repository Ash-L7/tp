package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person extends Contact {

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    @Override
    public Contact edit(EditContactDescriptor editContactDescriptor) {
        Name updatedName = editContactDescriptor.getName().orElse(getName());
        Phone updatedPhone = editContactDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editContactDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editContactDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public String getType() {
        return TYPE_PERSON;
    }
}

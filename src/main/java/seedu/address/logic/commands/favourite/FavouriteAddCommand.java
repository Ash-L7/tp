package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.FavouriteStatus;

/**
 * Adds an existing contact in the address book to favourites.
 */
public class FavouriteAddCommand extends Command {

    public static final String COMMAND_WORD = "favourite-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds to favourites an existing contact identified by the index number used "
            + "in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADD_FAVOURITE_SUCCESS = "Added contact to favourites: %1$s";
    public static final String MESSAGE_DUPLICATE_FAVOURITE = "Contact is already in favourites.";

    private final Index contactIndex;

    /**
     * Creates an FavouriteAddCommand to add the contact at {@code contactIndex} to favourites.
     */
    public FavouriteAddCommand(Index contactIndex) {
        requireNonNull(contactIndex);
        this.contactIndex = contactIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownContactList = model.getFilteredContactList();

        if (contactIndex.getZeroBased() >= lastShownContactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownContactList.get(contactIndex.getZeroBased());

        if (contactToEdit.isFavourite()) {
            throw new CommandException(MESSAGE_DUPLICATE_FAVOURITE);
        }

        FavouriteStatus updatedFavouriteStatus = new FavouriteStatus("true");

        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setFavourite(updatedFavouriteStatus);

        Contact editedContact = contactToEdit.edit(descriptor);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_ADD_FAVOURITE_SUCCESS, Messages.format(editedContact)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavouriteAddCommand)) {
            return false;
        }

        FavouriteAddCommand otherCommand = (FavouriteAddCommand) other;
        return contactIndex.equals(otherCommand.contactIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactIndex", contactIndex)
                .toString();
    }
}

package seedu.address.logic.commands.favorite;

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
import seedu.address.model.contact.FavoriteStatus;

/**
 * Adds an existing contact in the address book to favorites.
 */
public class FavoriteAddCommand extends Command {

    public static final String COMMAND_WORD = "favorite-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds to favorites an existing contact identified by the index number used "
            + "in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADD_FAVORITE_SUCCESS = "Added contact to favorites: %1$s";
    public static final String MESSAGE_DUPLICATE_FAVORITE = "Contact is already in favorites.";

    private final Index contactIndex;

    /**
     * Creates an FavoriteAddCommand to add the contact at {@code contactIndex} to favorites.
     */
    public FavoriteAddCommand(Index contactIndex) {
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

        if (contactToEdit.isFavorite()) {
            throw new CommandException(MESSAGE_DUPLICATE_FAVORITE);
        }

        FavoriteStatus updatedFavoriteStatus = new FavoriteStatus("true");

        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setFavorite(updatedFavoriteStatus);

        Contact editedContact = contactToEdit.edit(descriptor);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_ADD_FAVORITE_SUCCESS, Messages.format(editedContact)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavoriteAddCommand)) {
            return false;
        }

        FavoriteAddCommand otherCommand = (FavoriteAddCommand) other;
        return contactIndex.equals(otherCommand.contactIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactIndex", contactIndex)
                .toString();
    }
}

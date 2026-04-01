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
 * Removes an existing contact in the address book from favorites.
 */
public class FavoriteRemoveCommand extends Command {

    public static final String COMMAND_WORD = "favorite-remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes from favorites an existing contact identified by the index number used "
            + "in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_FAVORITE_SUCCESS = "Removed contact from favorites: %1$s";
    public static final String MESSAGE_DUPLICATE_NON_FAVORITE = "Contact is already not in favorites.";

    private final Index contactIndex;

    /**
     * Creates an FavoriteRemoveCommand to remove the contact at {@code contactIndex} from favorites.
     */
    public FavoriteRemoveCommand(Index contactIndex) {
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

        if (!contactToEdit.isFavorite()) {
            throw new CommandException(MESSAGE_DUPLICATE_NON_FAVORITE);
        }

        FavoriteStatus updatedFavoriteStatus = new FavoriteStatus("false");

        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setFavorite(updatedFavoriteStatus);

        Contact editedContact = contactToEdit.edit(descriptor);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_REMOVE_FAVORITE_SUCCESS, Messages.format(editedContact)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavoriteRemoveCommand)) {
            return false;
        }

        FavoriteRemoveCommand otherCommand = (FavoriteRemoveCommand) other;
        return contactIndex.equals(otherCommand.contactIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactIndex", contactIndex)
                .toString();
    }
}

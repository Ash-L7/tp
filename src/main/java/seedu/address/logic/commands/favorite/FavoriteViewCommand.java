package seedu.address.logic.commands.favorite;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsFavoritePredicate;

/**
 * Lists all contacts in the address book in favorites to the user.
 */
public class FavoriteViewCommand extends Command {

    public static final String COMMAND_WORD = "favorite-view";

    private final ContactIsFavoritePredicate predicate = new ContactIsFavoritePredicate();

    public FavoriteViewCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

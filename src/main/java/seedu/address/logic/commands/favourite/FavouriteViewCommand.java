package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsFavouritePredicate;

/**
 * Lists all contacts in the address book in favourites to the user.
 */
public class FavouriteViewCommand extends Command {

    public static final String COMMAND_WORD = "favourite-view";

    private final ContactIsFavouritePredicate predicate = new ContactIsFavouritePredicate();

    public FavouriteViewCommand() {
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

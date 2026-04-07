package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tour.TourIsFavouritePredicate;


/**
 * Lists all tours in the address book in favourites to the user
 */
public class TourFavouriteViewCommand extends Command {
    public static final String COMMAND_WORD = "tour-favourite-view";

    private final TourIsFavouritePredicate predicate = new TourIsFavouritePredicate();

    public TourFavouriteViewCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTourList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TOURS_LISTED_OVERVIEW, model.getFilteredTourList().size()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

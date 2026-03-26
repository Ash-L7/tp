package seedu.address.logic.commands.tour;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TOURS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all unique tours in the address book to the user.
 */
public class TourListCommand extends Command {

    public static final String COMMAND_WORD = "tour-list";

    public static final String MESSAGE_SUCCESS = "Tour Packages Available";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTourList(PREDICATE_SHOW_ALL_TOURS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.address.logic.commands.tour;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TOURS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsInTourPredicate;
import seedu.address.model.tour.Tour;

/**
 * Finds and lists all contacts in the address book who are assigned to the specified tour.
 */
public class TourViewCommand extends Command {

    public static final String COMMAND_WORD = "tour-view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contacts assigned to the tour identified by the index number in the tour list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public TourViewCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTourList(PREDICATE_SHOW_ALL_TOURS);
        List<Tour> tourList = model.getFilteredTourList();

        if (targetIndex.getZeroBased() >= tourList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Tour tour = tourList.get(targetIndex.getZeroBased());
        assert tour != null : "Tour list must not contain null elements";
        model.updateFilteredContactList(new ContactIsInTourPredicate(tour));
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TourViewCommand)) {
            return false;
        }

        TourViewCommand otherTourViewCommand = (TourViewCommand) other;
        return targetIndex.equals(otherTourViewCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

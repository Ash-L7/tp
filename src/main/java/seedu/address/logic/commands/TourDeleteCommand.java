package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tour.Tour;

/**
 * Deletes a tour package from the address book.
 */
public class TourDeleteCommand extends Command {

    public static final String COMMAND_WORD = "tour-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tour identified by the index number used in the displayed tour list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TOUR_SUCCESS = "Deleted Tour: %1$s";

    private final Index targetIndex;

    public TourDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tour> lastShownList = model.getFilteredTourList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Tour tourToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTour(tourToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TOUR_SUCCESS, Messages.format(tourToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        TourDeleteCommand otherDeleteCommand = (TourDeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

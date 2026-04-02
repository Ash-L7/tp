package seedu.address.logic.commands.tour;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
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

    private static final Logger logger = LogsCenter.getLogger(TourDeleteCommand.class);

    private final Index targetIndex;

    /**
     * Instantiates a TourDeleteCommand which deletes the tour at the {@code targetIndex} in the filtered tour list.
     */
    public TourDeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tour> lastShownList = model.getFilteredTourList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            logger.info(String.format("Index (%s) is larger than filtered tour list size (%s).",
                    targetIndex.getZeroBased(), lastShownList.size()));
            logger.fine(String.format("Filtered tour list: %s", model.getFilteredTourList()));
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Tour tourToDelete = lastShownList.get(targetIndex.getZeroBased());
        assert tourToDelete != null : "Tour in tour list should not be null";
        model.deleteTour(tourToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TOUR_SUCCESS, Messages.format(tourToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TourDeleteCommand)) {
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

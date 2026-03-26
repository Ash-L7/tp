package seedu.address.logic.commands;

import java.util.List;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.tour.Tour;
import seedu.address.model.tour.TourNameContainsKeywordsPredicate;

/**
 * Finds and lists all tours in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TourFindCommand extends Command {
    public static final String COMMAND_WORD = "tour-find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tours whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Walking";

    private StringBuilder matchingList;

    private final TourNameContainsKeywordsPredicate predicate;

    public TourFindCommand(TourNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
        this.matchingList = new StringBuilder();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTourList(predicate);

        List<Tour> allTours = model.getAddressBook().getTourList();
        List<Tour> filteredTours = model.getFilteredTourList();

        if (filteredTours.isEmpty()) {
            return new CommandResult("No matching tours found.");
        }

        StringBuilder sb = new StringBuilder("Matching Tours:\n");
        for (Tour tour : filteredTours) {
            int realIndex = allTours.indexOf(tour) + 1; // +1 for 1-based index
            sb.append(realIndex).append(". ").append(tour.getTourName()).append("\n");
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TourFindCommand)) {
            return false;
        }

        TourFindCommand otherFindCommand = (TourFindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

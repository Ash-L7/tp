package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tour.Tour;

/**
 * Adds a tour package to the address book.
 */
public class TourAddCommand extends Command {

    public static final String COMMAND_WORD = "tour-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tour package to the address book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Le Royal Tour";

    public static final String MESSAGE_SUCCESS = "New tour package added: %1$s";
    public static final String MESSAGE_DUPLICATE_TOUR = "This tour package already exists in the address book";

    private final Tour toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Tour}
     */
    public TourAddCommand(Tour tour) {
        requireNonNull(tour);
        toAdd = tour;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTour(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TOUR);
        }

        model.addTour(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        TourAddCommand otherAddCommand = (TourAddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}

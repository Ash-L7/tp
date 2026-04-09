package seedu.address.logic.commands.tour;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
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
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Duplicates a tour in the address book.
 */
public class TourDuplicateCommand extends Command {
    public static final String COMMAND_WORD = "tour-duplicate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Duplicates the tour identified by the index number used in the displayed tour list.\n"
            + "Parameters: INDEX n/NAME\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + "Le Royal Tour Copy";

    public static final String MESSAGE_SUCCESS = "Tour duplicated: %1$s";
    public static final String MESSAGE_DUPLICATE_TOUR = "This tour package already exists in the address book";

    private static final Logger logger = LogsCenter.getLogger(TourDuplicateCommand.class);

    private final Index targetIndex;
    private final String name;

    /**
     * Creates a TourDuplicateCommand to duplicate the specified {@code Tour}
     */
    public TourDuplicateCommand(Index targetIndex, String name) {
        requireNonNull(targetIndex);
        requireNonNull(name);
        this.targetIndex = targetIndex;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tour> lastShownList = model.getFilteredTourList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            logger.info(String.format("Index (%s) is larger than filtered tour list size (%s).",
                    targetIndex.getZeroBased(), lastShownList.size()));
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Tour tourToDuplicate = lastShownList.get(targetIndex.getZeroBased());
        assert !name.isBlank() : "Tour name should not be blank after parsing";

        Tour newTour = new Tour(name);
        if (model.hasTour(newTour)) {
            logger.info(String.format("Tour with name '%s' already exists.", name));
            throw new CommandException(MESSAGE_DUPLICATE_TOUR);
        }

        model.addTour(newTour);

        List<Contact> contacts = new ArrayList<>(model.getFilteredContactList());
        for (Contact contact : contacts) {
            if (contact.isInTour(tourToDuplicate)) {
                model.assignTour(contact, newTour);
            }
        }

        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(newTour)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TourDuplicateCommand)) {
            return false;
        }

        TourDuplicateCommand otherDuplicateCommand = (TourDuplicateCommand) other;
        return targetIndex.equals(otherDuplicateCommand.targetIndex)
                && name.equals(otherDuplicateCommand.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("name", name)
                .toString();
    }
}

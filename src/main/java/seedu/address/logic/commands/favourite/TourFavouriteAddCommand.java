package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TOURS;
import static seedu.address.model.contact.FavouriteStatus.VALID_FAVOURITE_STATUS_TRUE;

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
import seedu.address.model.contact.FavouriteStatus;
import seedu.address.model.tour.Tour;

/**
 * Adds an existing tour in the address book to favourites.
 */
public class TourFavouriteAddCommand extends Command {
    public static final String COMMAND_WORD = "tour-favourite-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds to favourites an existing tour identified by the index number used "
            + "in the displayed tour list.\n"
            + "Parameters: TOUR_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADD_FAVOURITE_SUCCESS = "Added tour package to favourites: %1$s";
    public static final String MESSAGE_DUPLICATE_FAVOURITE = "Tour package is already in favourites.";


    private static final Logger logger = LogsCenter.getLogger(TourFavouriteAddCommand.class);

    private final Index tourIndex;

    /**
     * Creates an TourFavouriteAddCommand to add the tour at {@code tourIndex} to favourites.
     */
    public TourFavouriteAddCommand(Index tourIndex) {
        requireNonNull(tourIndex);
        this.tourIndex = tourIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Tour tourToEdit = getTourToEdit(model);
        Tour editedTour = setTourFavouriteStatus(tourToEdit);
        model.setTour(tourToEdit, editedTour);
        model.commitAddressBook();
        logger.fine(String.format("Added tour to favourites: %s", editedTour));
        model.updateFilteredTourList(PREDICATE_SHOW_ALL_TOURS);
        return new CommandResult(String.format(MESSAGE_ADD_FAVOURITE_SUCCESS, Messages.format(editedTour)));
    }

    /**
     * Returns the tour to add to favourites.
     */
    private Tour getTourToEdit(Model model) throws CommandException {
        List<Tour> lastShownTourList = model.getFilteredTourList();

        if (tourIndex.getZeroBased() >= lastShownTourList.size()) {
            logger.info("Invalid index for TourFavouriteAddCommand");
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Tour tourToEdit = lastShownTourList.get(tourIndex.getZeroBased());
        assert tourToEdit != null : "Tour from tour list must not be null.";

        if (tourToEdit.isFavourite()) {
            logger.info("Tour is already in favourites");
            throw new CommandException(MESSAGE_DUPLICATE_FAVOURITE);
        }

        return tourToEdit;
    }

    /**
     * Returns the tour with updated favourite status.
     */
    private Tour setTourFavouriteStatus(Tour tourToEdit) {
        requireNonNull(tourToEdit);

        Tour editedTour = tourToEdit.setFavouriteStatus(new FavouriteStatus(VALID_FAVOURITE_STATUS_TRUE));

        assert editedTour != null : "Edited tour must not be null.";
        assert editedTour.isFavourite() : "Tour must have been added to favourites.";

        return editedTour;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TourFavouriteAddCommand)) {
            return false;
        }

        TourFavouriteAddCommand otherCommand = (TourFavouriteAddCommand) other;
        return tourIndex.equals(otherCommand.tourIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tourIndex", tourIndex)
                .toString();
    }
}

package seedu.address.logic.commands.tour;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Unassigns a tour to an existing contact in the address book.
 */
public class TourUnassignCommand extends Command {

    public static final String COMMAND_WORD = "tour-unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns a tour to the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX tour/TOUR_INDEX (both must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 tour/2";

    public static final String MESSAGE_UNASSIGN_TOUR_SUCCESS = "Unassigned tour from contact: %1$s";
    public static final String MESSAGE_NOT_IN_TOUR = "Contact is not assigned to this tour.";

    private final Index contactIndex;
    private final Index tourIndex;

    /**
     * Creates an TourUnassignCommand to unassign the tour at {@code tourIndex} to the
     * contact at {@code contactIndex}.
     */
    public TourUnassignCommand(Index contactIndex, Index tourIndex) {
        requireNonNull(contactIndex);
        requireNonNull(tourIndex);
        this.contactIndex = contactIndex;
        this.tourIndex = tourIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownContactList = model.getFilteredContactList();
        List<Tour> lastShownTourList = model.getFilteredTourList();

        if (contactIndex.getZeroBased() >= lastShownContactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        if (tourIndex.getZeroBased() >= lastShownTourList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownContactList.get(contactIndex.getZeroBased());
        Tour tour = lastShownTourList.get(tourIndex.getZeroBased());

        if (!contactToEdit.isInTour(tour)) {
            throw new CommandException(MESSAGE_NOT_IN_TOUR);
        }

        Set<Tour> updatedTours = new HashSet<>(contactToEdit.getTours());
        updatedTours.remove(tour);

        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setTours(updatedTours);

        Contact editedContact = contactToEdit.edit(descriptor);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_UNASSIGN_TOUR_SUCCESS, Messages.format(editedContact)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TourUnassignCommand)) {
            return false;
        }

        TourUnassignCommand otherCommand = (TourUnassignCommand) other;
        return contactIndex.equals(otherCommand.contactIndex) && tourIndex.equals(otherCommand.tourIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactIndex", contactIndex)
                .add("tourIndex", tourIndex)
                .toString();
    }
}

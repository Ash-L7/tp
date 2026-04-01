package seedu.address.logic.commands.tour;

import static java.util.Objects.requireNonNull;

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
 * Assigns a tour to an existing contact in the address book.
 */
public class TourAssignCommand extends Command {

    public static final String COMMAND_WORD = "tour-assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a tour to the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX tour/TOUR_INDEX (both must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 tour/2";

    public static final String MESSAGE_ASSIGN_TOUR_SUCCESS = "Assigned tour to contact: %1$s";
    public static final String MESSAGE_DUPLICATE_TOUR = "Contact is already assigned to this tour.";

    private final Index contactIndex;
    private final Index tourIndex;

    /**
     * Creates an TourAssignCommand to assign the tour at {@code tourIndex} to the contact at {@code contactIndex}.
     */
    public TourAssignCommand(Index contactIndex, Index tourIndex) {
        requireNonNull(contactIndex);
        requireNonNull(tourIndex);
        this.contactIndex = contactIndex;
        this.tourIndex = tourIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Contact contact = getContact(model.getFilteredContactList(), contactIndex);
        Tour tour = getTour(model.getFilteredTourList(), tourIndex);
        validateNotAssigned(contact, tour);
        Contact updatedContact = buildContactWithTourAdded(contact, tour);
        model.setContact(contact, updatedContact);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TOUR_SUCCESS, Messages.format(updatedContact)));
    }

    private static Contact getContact(List<Contact> contactList, Index index) throws CommandException {
        if (index.getZeroBased() >= contactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }
        return contactList.get(index.getZeroBased());
    }

    private static Tour getTour(List<Tour> tourList, Index index) throws CommandException {
        if (index.getZeroBased() >= tourList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }
        return tourList.get(index.getZeroBased());
    }

    private static void validateNotAssigned(Contact contact, Tour tour) throws CommandException {
        if (contact.isInTour(tour)) {
            throw new CommandException(MESSAGE_DUPLICATE_TOUR);
        }
    }

    private static Contact buildContactWithTourAdded(Contact contact, Tour tour) {
        Set<Tour> updatedTours = new HashSet<>(contact.getTours());
        updatedTours.add(tour);
        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setTours(updatedTours);
        return contact.edit(descriptor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TourAssignCommand)) {
            return false;
        }

        TourAssignCommand otherCommand = (TourAssignCommand) other;
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

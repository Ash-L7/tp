package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Lists all unique tours in the address book to the user.
 */
public class TourListCommand extends Command {

    public static final String COMMAND_WORD = "tour_list";

    public static final String MESSAGE_SUCCESS = "Listed all tours";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Set<Tour> allTours = new HashSet<>();
        for (Contact contact : model.getAddressBook().getContactList()) {
            allTours.addAll(contact.getTours());
        }
        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS + ":\n");
        for (Tour tour : allTours) {
            sb.append(tour.getTourName()).append("\n");
        }
        return new CommandResult(sb.toString());
    }
}
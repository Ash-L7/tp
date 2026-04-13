package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.Attraction;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Fnb;
import seedu.address.model.tour.Tour;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed! Contact list will be in " +
            "filtered view until 'list' command is called to view all contacts.";
    public static final String MESSAGE_NON_APPLICABLE_FIELDS =
            "Values for non-applicable fields were provided! No changes were made! \n%1$s";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_TOUR_DISPLAYED_INDEX = "The tour index provided is invalid";
    public static final String MESSAGE_TOURS_LISTED_OVERVIEW = "%1$d tours listed! Tour list will be in filtered view" +
            " until 'tour-list' command is called to view all contacts.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code contact} for display to the user.
     */
    public static String format(Contact contact) {
        final StringBuilder builder = new StringBuilder();
        builder.append(contact.getName())
                .append("; Phone: ")
                .append(contact.getPhone())
                .append("; Email: ")
                .append(contact.getEmail())
                .append("; Address: ")
                .append(contact.getAddress());
        if (contact instanceof Fnb fnb) {
            builder.append("; Halal Status: ")
                    .append(fnb.getHalalStatus());
        }
        if (contact instanceof Attraction attraction) {
            String openingHour = attraction.getOpeningHour().toString();
            String closingHour = attraction.getClosingHour().toString();
            builder.append("; Operating Hours: ")
                    .append(openingHour)
                    .append(" to ")
                    .append(closingHour);
        }
        if (contact instanceof Accommodation accommodation) {
            builder.append("; Stars: ")
                    .append(accommodation.getStars());
        }
        builder.append("; Tours: ")
                .append(contact.getTours().stream()
                        .map(Tour::toString)
                        .collect(Collectors.joining(" | ")));

        builder.append("; Tags: ")
                .append(contact.getTags().stream()
                        .map(tag -> tag.tagName)
                        .collect(Collectors.joining(", ")));
        return builder.toString();
    }

    /**
     * Formats the {@code tour} for display to the user.
     */
    public static String format(Tour tour) {
        return tour.getTourName();
    }

}

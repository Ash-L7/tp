package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.Attraction;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Fnb;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

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
        builder.append("; Tours: ");
        contact.getTours().forEach(builder::append);
        builder.append("; Tags: ");
        contact.getTags().forEach(builder::append);
        return builder.toString();
    }

}

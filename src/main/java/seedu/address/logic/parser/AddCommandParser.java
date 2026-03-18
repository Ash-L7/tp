package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLOSING_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HALAL_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.TYPE_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.TYPE_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.TYPE_FNB;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.AccommodationStars;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Attraction;
import seedu.address.model.contact.ClosingHour;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Fnb;
import seedu.address.model.contact.HalalStatus;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.OpeningHour;
import seedu.address.model.contact.Person;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_HALAL_STATUS, PREFIX_OPENING_HOUR,
                        PREFIX_CLOSING_HOUR, PREFIX_STARS);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        String type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Contact contact = null;

        // Create objects based on type of contact
        if (type.equals(TYPE_PERSON)) {
            contact = new Person(name, phone, email, address, tagList);
        }

        if (type.equals(TYPE_FNB)) {
            if (arePrefixesPresent(argMultimap, PREFIX_HALAL_STATUS)) {
                HalalStatus isHalal = ParserUtil.parseHalalStatus(argMultimap.getValue(PREFIX_HALAL_STATUS).get());
                contact = new Fnb(name, phone, email, address, tagList, isHalal);
            } else {
                contact = new Fnb(name, phone, email, address, tagList);
            }
        }

        if (type.equals(TYPE_ATTRACTION)) {
            OpeningHour openingHour = null;
            ClosingHour closingHour = null;
            if (arePrefixesPresent(argMultimap, PREFIX_OPENING_HOUR)) {
                openingHour = ParserUtil.parseOpeningHour(argMultimap.getValue(PREFIX_OPENING_HOUR).get());
            }
            if (arePrefixesPresent(argMultimap, PREFIX_CLOSING_HOUR)) {
                closingHour = ParserUtil.parseClosingHour(argMultimap.getValue(PREFIX_CLOSING_HOUR).get());
            }
            if (openingHour == null && closingHour == null) {
                contact = new Attraction(name, phone, email, address, tagList);
            } else if (openingHour == null) {
                contact = new Attraction(name, phone, email, address, tagList, closingHour);
            } else if (closingHour == null) {
                contact = new Attraction(name, phone, email, address, tagList, openingHour);
            } else {
                contact = new Attraction(name, phone, email, address, tagList, openingHour, closingHour);
            }
        }

        if (type.equals(TYPE_ACCOMMODATION)) {
            if (arePrefixesPresent(argMultimap, PREFIX_STARS)) {
                AccommodationStars stars = ParserUtil.parseAccommodationStars(
                        argMultimap.getValue(PREFIX_STARS).get());
                contact = new Accommodation(name, phone, email, address, tagList, stars);
            } else {
                contact = new Accommodation(name, phone, email, address, tagList);
            }
        }
        return new AddCommand(contact);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

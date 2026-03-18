package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.VALID_TYPES;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.AccommodationStars;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.ClosingHour;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.HalalStatus;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.OpeningHour;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_TYPE = "Invalid contact type.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code type} and returns it. Leading and trailing white spaces will be trimmed.
     * @throws ParseException if the type is invalid (not a contact type).
     */
    public static String parseType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!VALID_TYPES.contains(trimmedType)) {
            throw new ParseException(MESSAGE_INVALID_TYPE);
        }
        return trimmedType;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String halalStatus} into a {@code Halal Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code halalStatus} is invalid.
     */
    public static HalalStatus parseHalalStatus(String halalStatus) throws ParseException {
        String trimmedHalalStatus = halalStatus.trim();
        if (!HalalStatus.isValidHalalStatus(trimmedHalalStatus)) {
            throw new ParseException(HalalStatus.MESSAGE_CONSTRAINTS);
        }
        return new HalalStatus(halalStatus);
    }

    /**
     * Parses a {@code String openingHour} into an {@code Opening Hour}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code openingHour} is invalid.
     */
    public static OpeningHour parseOpeningHour(String openingHour) throws ParseException {
        String trimmedOpeningHour = openingHour.trim();
        if (!OpeningHour.isValidOpeningHour(trimmedOpeningHour)) {
            throw new ParseException(OpeningHour.MESSAGE_CONSTRAINTS);
        }
        return new OpeningHour(trimmedOpeningHour);
    }

    /**
     * Parses a {@code String closingHour} into an {@code Closing Hour}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code closingHour} is invalid.
     */
    public static ClosingHour parseClosingHour(String closingHour) throws ParseException {
        String trimmedClosingHour = closingHour.trim();
        if (!ClosingHour.isValidClosingHour(trimmedClosingHour)) {
            throw new ParseException(ClosingHour.MESSAGE_CONSTRAINTS);
        }
        return new ClosingHour(trimmedClosingHour);
    }

    public static Set<Tour> parseTours(List<String> allValues) {
        Set<Tour> tourSet = new HashSet<>();
        for (String tourName : allValues) {
            tourSet.add(new Tour(tourName));
        }
        return tourSet;
    }

    /**
     * Parses a {@code String accommodationStar} into an {@code Accommodation Stars}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code accommodationStar} is invalid.
     */
    public static AccommodationStars parseAccommodationStars(String accommodationStar) throws ParseException {
        String trimmedAccommodationStar = accommodationStar.trim();
        if (!AccommodationStars.isValidAccommodationStars(trimmedAccommodationStar)) {
            throw new ParseException(AccommodationStars.MESSAGE_CONSTRAINTS);
        }
        return new AccommodationStars(trimmedAccommodationStar);
    }
}

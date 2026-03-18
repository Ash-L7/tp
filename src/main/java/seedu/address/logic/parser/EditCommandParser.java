package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOUR;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_TOUR, PREFIX_HALAL_STATUS, PREFIX_OPENING_HOUR,
                        PREFIX_CLOSING_HOUR, PREFIX_STARS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editContactDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editContactDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editContactDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editContactDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editContactDescriptor::setTags);
        parseToursForEdit(argMultimap.getAllValues(PREFIX_TOUR)).ifPresent(editContactDescriptor::setTours);
        if (argMultimap.getValue(PREFIX_HALAL_STATUS).isPresent()) {
            editContactDescriptor.setHalal(ParserUtil.parseHalalStatus(
                    argMultimap.getValue(PREFIX_HALAL_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_OPENING_HOUR).isPresent()) {
            editContactDescriptor.setOpeningHour(ParserUtil.parseOpeningHour(
                    argMultimap.getValue(PREFIX_OPENING_HOUR).get()));
        }
        if (argMultimap.getValue(PREFIX_CLOSING_HOUR).isPresent()) {
            editContactDescriptor.setClosingHour(ParserUtil.parseClosingHour(
                    argMultimap.getValue(PREFIX_CLOSING_HOUR).get()));
        }
        if (argMultimap.getValue(PREFIX_STARS).isPresent()) {
            editContactDescriptor.setStars(ParserUtil.parseAccommodationStars(
                    argMultimap.getValue(PREFIX_STARS).get()));
        }
        if (!editContactDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editContactDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> tours} into a {@code Set<Tour>} if {@code tours} is non-empty.
     * If {@code tours} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tour>} containing zero tours.
     */
    private Optional<Set<Tour>> parseToursForEdit(Collection<String> tours) throws ParseException {
        assert tours != null;

        if (tours.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tourSet = tours.size() == 1 && tours.contains("") ? Collections.emptySet() : tours;
        return Optional.of(ParserUtil.parseTours(tourSet));
    }
}

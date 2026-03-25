package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOUR;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tour.TourUnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnassignTourCommand object.
 */
public class TourUnassignCommandParser implements Parser<TourUnassignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * UnassignTourCommand
     * and returns an UnassignTourCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TourUnassignCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TOUR);

        if (!arePrefixesPresent(argMultimap, PREFIX_TOUR) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourUnassignCommand.MESSAGE_USAGE));
        }

        Index contactIndex;
        try {
            contactIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourUnassignCommand.MESSAGE_USAGE), pe);
        }

        Index tourIndex;
        try {
            tourIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TOUR).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourUnassignCommand.MESSAGE_USAGE), pe);
        }

        return new TourUnassignCommand(contactIndex, tourIndex);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.TourAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tour.Tour;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class TourAddCommandParser implements Parser<TourAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TourAddCommand
     * and returns an TourAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TourAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourAddCommand.MESSAGE_USAGE));
        }

        Tour tour = ParserUtil.parseTour(argMultimap.getValue(PREFIX_NAME).get());

        return new TourAddCommand(tour);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

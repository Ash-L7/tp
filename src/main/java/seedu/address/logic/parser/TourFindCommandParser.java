package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.tour.TourFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tour.TourNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new TourFindCommand object
 */
public class TourFindCommandParser implements Parser<TourFindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TourFindCommand
     * and returns a TourFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TourFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new TourFindCommand(new TourNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}

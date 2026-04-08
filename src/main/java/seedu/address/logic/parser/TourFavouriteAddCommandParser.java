package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favourite.TourFavouriteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TourFavouriteAddCommand object
 */
public class TourFavouriteAddCommandParser implements Parser<TourFavouriteAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TourFavouriteAddCommand
     * and returns a TourFavouriteAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TourFavouriteAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TourFavouriteAddCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourFavouriteAddCommand.MESSAGE_USAGE), pe);
        }
    }
}

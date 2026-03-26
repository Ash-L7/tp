package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favorite.FavoriteRemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FavoriteRemoveCommand object
 */
public class FavoriteRemoveCommandParser implements Parser<FavoriteRemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavoriteRemoveCommand
     * and returns a FavoriteRemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FavoriteRemoveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavoriteRemoveCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavoriteRemoveCommand.MESSAGE_USAGE), pe);
        }
    }
}

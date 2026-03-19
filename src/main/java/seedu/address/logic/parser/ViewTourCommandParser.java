package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewTourCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewTourCommand object
 */
public class ViewTourCommandParser implements Parser<ViewTourCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewTourCommand
     * and returns a ViewTourCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTourCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewTourCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTourCommand.MESSAGE_USAGE), pe);
        }
    }

}

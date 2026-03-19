package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewTourCommand;

public class ViewTourCommandParserTest {

    private ViewTourCommandParser parser = new ViewTourCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTourCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "notAnIndex",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTourCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewTourCommand() {
        ViewTourCommand expectedCommand = new ViewTourCommand(Index.fromOneBased(1));

        // valid index
        assertParseSuccess(parser, "1", expectedCommand);

        // leading and trailing whitespaces trimmed
        assertParseSuccess(parser, "  1  ", expectedCommand);
    }
}

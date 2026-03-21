package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tour.TourViewCommand;

public class TourViewCommandParserTest {

    private TourViewCommandParser parser = new TourViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "notAnIndex",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsTourViewCommand() {
        TourViewCommand expectedCommand = new TourViewCommand(Index.fromOneBased(1));

        // valid index
        assertParseSuccess(parser, "1", expectedCommand);

        // leading and trailing whitespaces trimmed
        assertParseSuccess(parser, "  1  ", expectedCommand);
    }
}

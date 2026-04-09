package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.tour.TourDuplicateCommand;

public class TourDuplicateCommandParserTest {

    private final TourDuplicateCommandParser parser = new TourDuplicateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 n/New Tour Name",
                new TourDuplicateCommand(INDEX_FIRST_TOUR, "New Tour Name"));
    }

    @Test
    public void parse_extraWhitespace_success() {
        assertParseSuccess(parser, "  1   n/New Tour Name  ",
                new TourDuplicateCommand(INDEX_FIRST_TOUR, "New Tour Name"));
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, "n/New Tour Name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingNamePrefix_failure() {
        assertParseFailure(parser, "1 New Tour Name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingNamePrefixValidIndex_failure() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingBothFields_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "0 n/New Tour Name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIndex_failure() {
        assertParseFailure(parser, "-1 n/New Tour Name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonIntegerIndex_failure() {
        assertParseFailure(parser, "abc n/New Tour Name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDuplicateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicateNamePrefix_failure() {
        assertParseFailure(parser, "1 n/New Tour Name n/Another Name",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }
}

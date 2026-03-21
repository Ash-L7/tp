package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TOUR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tour.TourAssignCommand;

public class TourAssignCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourAssignCommand.MESSAGE_USAGE);

    private TourAssignCommandParser parser = new TourAssignCommandParser();

    @Test
    public void parse_validArgs_returnsAssignTourCommand() {
        assertParseSuccess(parser, "1 tour/1",
                new TourAssignCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR));
    }

    @Test
    public void parse_validArgsWithWhitespace_returnsAssignTourCommand() {
        assertParseSuccess(parser, "  1  tour/2  ",
                new TourAssignCommand(INDEX_FIRST_CONTACT, INDEX_SECOND_TOUR));
    }

    @Test
    public void parse_missingContactIndex_failure() {
        // no preamble
        assertParseFailure(parser, "tour/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingTourPrefix_failure() {
        // no tour/ prefix
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidContactIndex_failure() {
        assertParseFailure(parser, "a tour/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTourIndex_failure() {
        assertParseFailure(parser, "1 tour/a", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_zeroContactIndex_failure() {
        assertParseFailure(parser, "0 tour/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_zeroTourIndex_failure() {
        assertParseFailure(parser, "1 tour/0", MESSAGE_INVALID_FORMAT);
    }
}

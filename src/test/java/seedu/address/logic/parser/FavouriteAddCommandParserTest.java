package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.favourite.FavouriteAddCommand;

public class FavouriteAddCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavouriteAddCommand.MESSAGE_USAGE);

    private final FavouriteAddCommandParser parser = new FavouriteAddCommandParser();

    @Test
    public void parse_validArgs_returnsFavouriteAddCommand() {
        assertParseSuccess(parser, "1",
                new FavouriteAddCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_validArgsWithWhitespace_returnsFavouriteAddCommand() {
        assertParseSuccess(parser, "   1   ",
                new FavouriteAddCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_zeroIndex_failure() {
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_negativeIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.TYPE_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.TYPE_ATTRACTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.model.contact.ContactTypePredicate;
import seedu.address.model.contact.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeyword_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validKeyword_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE,
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t  \n \t  " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validType_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ContactTypePredicate(TYPE_ACCOMMODATION),
                        NameContainsKeywordsPredicate.ANY_NAME_ALLOWED_PREDICATE);
        assertParseSuccess(parser, " " + PREFIX_TYPE + TYPE_ACCOMMODATION, expectedFindCommand);

        // multiple leading and trailing whitespaces, whitespaces between keywords
        assertParseSuccess(parser, " \n \t  \n \t  " + PREFIX_TYPE + TYPE_ACCOMMODATION + " \n  \n \t   \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validTypeAndKeyword_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ContactTypePredicate(TYPE_ATTRACTION),
                        new NameContainsKeywordsPredicate(Arrays.asList("NUS", "the", "best", "campus", "life")));
        assertParseSuccess(parser, " " + PREFIX_TYPE + TYPE_ATTRACTION + " "
                        + PREFIX_NAME + "NUS the best campus life", expectedFindCommand);

        // multiple leading and trailing whitespaces, whitespaces between keywords
        assertParseSuccess(parser, "\n    \t   \t\n " + PREFIX_TYPE + "\t\t \n   \t \n  " + TYPE_ATTRACTION
                        + "\t  \n  \t   \t   \n\n " + PREFIX_NAME
                        + "\t \n   \t NUS \t \t the        best \ncampus\t  \n  life\t  \n\t",
                expectedFindCommand);
    }

}

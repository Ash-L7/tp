package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactTypePredicate;
import seedu.address.model.contact.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NAME);
        if (!anyPrefixPresent(argMultimap, PREFIX_TYPE, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE, PREFIX_NAME);

        ContactTypePredicate typePredicate = ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE;
        NameContainsKeywordsPredicate namePredicate = NameContainsKeywordsPredicate.ANY_NAME_ALLOWED_PREDICATE;
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            String type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
            typePredicate = new ContactTypePredicate(type);
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");
            namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        }

        return new FindCommand(typePredicate, namePredicate);
    }

    /**
     * Returns true if one of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

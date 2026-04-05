package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.TYPE_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.TYPE_FNB;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactTypePredicate;
import seedu.address.model.contact.NameContainsKeywordsPredicate;

/**
 * Finds and lists all contacts in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of "
            + "the specified keywords (case-insensitive) or types and displays them as a list with index numbers.\n"
            + "At least one of TYPE or KEYWORD(s) parameters must be supplied. "
            + "If KEYWORD is supplied, it cannot be blank.\n"
            + "Parameters: "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_NAME + "KEYWORD [MORE_KEYWORDS]...]\n"
            + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: "
            + String.format("%s %salice bob charlie\n", COMMAND_WORD, PREFIX_NAME)
            + String.format("%s %s%s\n", COMMAND_WORD, PREFIX_TYPE, TYPE_FNB)
            + String.format("%s %s%s %sSentosa USS MBS\n", COMMAND_WORD, PREFIX_TYPE, TYPE_ATTRACTION, PREFIX_NAME);

    private static final Logger logger = LogsCenter.getLogger(FindCommand.class);

    private final ContactTypePredicate typePredicate;
    private final NameContainsKeywordsPredicate namePredicate;

    /**
     * Instantiates a FindCommand with a {@code typePredicate} that filters contacts by their type,
     * and {@code namePredicate} that filters contacts by their name.
     */
    public FindCommand(ContactTypePredicate typePredicate,
                       NameContainsKeywordsPredicate namePredicate) {
        requireAllNonNull(typePredicate, namePredicate);
        this.typePredicate = typePredicate;
        this.namePredicate = namePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(contact -> typePredicate.test(contact) && namePredicate.test(contact));
        logger.fine(String.format("Filtered contact list: %s", model.getFilteredContactList()));
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return typePredicate.equals(otherFindCommand.typePredicate)
                && namePredicate.equals(otherFindCommand.namePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("typePredicate", typePredicate)
                .add("namePredicate", namePredicate)
                .toString();
    }
}

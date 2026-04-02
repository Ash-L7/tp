package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.contact.AddCommand;
import seedu.address.logic.commands.contact.DeleteCommand;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.commands.contact.ListCommand;
import seedu.address.logic.commands.favourite.FavouriteAddCommand;
import seedu.address.logic.commands.favourite.FavouriteRemoveCommand;
import seedu.address.logic.commands.favourite.FavouriteViewCommand;
import seedu.address.logic.commands.general.ClearCommand;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.commands.tour.TourAddCommand;
import seedu.address.logic.commands.tour.TourAssignCommand;
import seedu.address.logic.commands.tour.TourDeleteCommand;
import seedu.address.logic.commands.tour.TourFindCommand;
import seedu.address.logic.commands.tour.TourListCommand;
import seedu.address.logic.commands.tour.TourUnassignCommand;
import seedu.address.logic.commands.tour.TourViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case FavouriteViewCommand.COMMAND_WORD:
            return new FavouriteViewCommand();

        case FavouriteAddCommand.COMMAND_WORD:
            return new FavouriteAddCommandParser().parse(arguments);

        case FavouriteRemoveCommand.COMMAND_WORD:
            return new FavouriteRemoveCommandParser().parse(arguments);

        case TourListCommand.COMMAND_WORD:
            return new TourListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TourAddCommand.COMMAND_WORD:
            return new TourAddCommandParser().parse(arguments);

        case TourDeleteCommand.COMMAND_WORD:
            return new TourDeleteCommandParser().parse(arguments);

        case TourViewCommand.COMMAND_WORD:
            return new TourViewCommandParser().parse(arguments);

        case TourAssignCommand.COMMAND_WORD:
            return new TourAssignCommandParser().parse(arguments);

        case TourUnassignCommand.COMMAND_WORD:
            return new TourUnassignCommandParser().parse(arguments);

        case TourFindCommand.COMMAND_WORD:
            return new TourFindCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

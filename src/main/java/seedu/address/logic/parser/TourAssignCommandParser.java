package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOUR;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tour.TourAssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignTourCommand object.
 */
public class TourAssignCommandParser implements Parser<TourAssignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTourCommand
     * and returns an AssignTourCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TourAssignCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TOUR);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TOUR) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourAssignCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TOUR);

        try {
            Index contactIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            Index tourIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TOUR).get());
            return new TourAssignCommand(contactIndex, tourIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourAssignCommand.MESSAGE_USAGE), pe);
        }
    }
}

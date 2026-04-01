package seedu.address.logic.commands.general;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the most recent command that modified the address book.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_NOTHING_TO_UNDO = "No commands to undo.";
    public static final String MESSAGE_SUCCESS = "Undo successful!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_NOTHING_TO_UNDO);
        }
        model.undoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

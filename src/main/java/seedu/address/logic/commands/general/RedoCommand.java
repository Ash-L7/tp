package seedu.address.logic.commands.general;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redo the most recent undone command in the address book.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_NOTHING_TO_REDO = "No commands to redo.";
    public static final String MESSAGE_SUCCESS = "Redo successful!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_NOTHING_TO_REDO);
        }
        model.redoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

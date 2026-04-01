package seedu.address.logic.commands.general;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nothingToUndo_failure() {
        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NOTHING_TO_UNDO);
    }

    @Test
    public void execute_singleUndoableState_success() {
        model.commitAddressBook(); // simulate a command having been executed

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_multipleUndoableStates_success() {
        model.commitAddressBook(); // simulate first command
        model.commitAddressBook(); // simulate second command

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoAfterAllStatesUndone_failure() {
        model.commitAddressBook();
        model.undoAddressBook(); // undo the one commit, now nothing left to undo

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_NOTHING_TO_UNDO);
    }
}

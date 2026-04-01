package seedu.address.logic.commands.general;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nothingToRedo_failure() {
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_NOTHING_TO_REDO);
    }

    @Test
    public void execute_singleRedoableState_success() {
        model.commitAddressBook();
        model.undoAddressBook(); // undo to create a redoable state

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();

        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_multipleRedoableStates_success() {
        model.commitAddressBook();
        model.commitAddressBook();
        model.undoAddressBook();
        model.undoAddressBook(); // undo twice to create two redoable states

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();

        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoAfterAllStatesRedone_failure() {
        model.commitAddressBook();
        model.undoAddressBook();
        model.redoAddressBook(); // redo the one undone state, now nothing left to redo

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_NOTHING_TO_REDO);
    }

    @Test
    public void execute_redoAfterNewCommit_failure() {
        // committing after undoing purges redo states
        model.commitAddressBook();
        model.undoAddressBook();
        model.commitAddressBook(); // new commit purges redo history

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_NOTHING_TO_REDO);
    }
}
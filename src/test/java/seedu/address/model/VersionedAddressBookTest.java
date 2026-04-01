package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

public class VersionedAddressBookTest {

    private final VersionedAddressBook versionedAddressBook =
            new VersionedAddressBook(getTypicalAddressBook());

    // ======================== canUndo() ========================

    @Test
    public void canUndo_initialState_returnsFalse() {
        assertFalse(versionedAddressBook.canUndoAddressBook());
    }

    @Test
    public void canUndo_afterCommit_returnsTrue() {
        versionedAddressBook.commit();
        assertTrue(versionedAddressBook.canUndoAddressBook());
    }

    @Test
    public void canUndo_afterUndoToInitialState_returnsFalse() {
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        assertFalse(versionedAddressBook.canUndoAddressBook());
    }

    // ======================== canRedo() ========================

    @Test
    public void canRedo_initialState_returnsFalse() {
        assertFalse(versionedAddressBook.canRedoAddressBook());
    }

    @Test
    public void canRedo_afterUndo_returnsTrue() {
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        assertTrue(versionedAddressBook.canRedoAddressBook());
    }

    @Test
    public void canRedo_afterCommitFollowingUndo_returnsFalse() {
        // committing after undoing purges redo states
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        versionedAddressBook.commit();
        assertFalse(versionedAddressBook.canRedoAddressBook());
    }

    @Test
    public void canRedo_afterRedoToLatestState_returnsFalse() {
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        versionedAddressBook.redo();
        assertFalse(versionedAddressBook.canRedoAddressBook());
    }

    // ======================== commit() ========================

    @Test
    public void commit_singleCommit_canUndo() {
        versionedAddressBook.commit();
        assertTrue(versionedAddressBook.canUndoAddressBook());
    }

    @Test
    public void commit_afterUndo_purgesRedoHistory() {
        versionedAddressBook.commit();
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        // new commit should purge the undone state
        versionedAddressBook.commit();
        assertFalse(versionedAddressBook.canRedoAddressBook());
    }

    // ======================== undo() ========================

    @Test
    public void undo_singleCommit_restoredToPreviousState() {
        ReadOnlyAddressBook stateBefore = new AddressBook(versionedAddressBook);
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        assertEquals(stateBefore, versionedAddressBook);
    }

    @Test
    public void undo_multipleCommits_restoredToPreviousState() {
        versionedAddressBook.commit();
        ReadOnlyAddressBook stateAfterFirstCommit = new AddressBook(versionedAddressBook);
        versionedAddressBook.commit();
        versionedAddressBook.undo();
        assertEquals(stateAfterFirstCommit, versionedAddressBook);
    }

    // ======================== redo() ========================

    @Test
    public void redo_singleUndo_restoredToUndoneState() {
        versionedAddressBook.commit();
        ReadOnlyAddressBook stateAfterCommit = new AddressBook(versionedAddressBook);
        versionedAddressBook.undo();
        versionedAddressBook.redo();
        assertEquals(stateAfterCommit, versionedAddressBook);
    }

    @Test
    public void redo_multipleUndos_restoredToCorrectState() {
        versionedAddressBook.commit();
        versionedAddressBook.commit();
        ReadOnlyAddressBook stateAfterSecondCommit = new AddressBook(versionedAddressBook);
        versionedAddressBook.undo();
        versionedAddressBook.undo();
        versionedAddressBook.redo();
        versionedAddressBook.redo();
        assertEquals(stateAfterSecondCommit, versionedAddressBook);
    }
}

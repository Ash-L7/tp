package seedu.address.model;

import java.util.ArrayList;

/**
 * An AddressBook which tracks a history of states, allowing the undoing and redoing of states.
 */
public class VersionedAddressBook extends AddressBook {
    private final ArrayList<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedAddressBook using the AddressBook in the {@code initialState} with the
     * state history containing only the initial state.
     */
    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        super(addressBook);
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(addressBook));
        currentStatePointer = 0;
    }

    /**
     * Saves the current address book state to addressBookStateList.
     */
    public void commit() {
        // purge any redo states after current pointer
        addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        // snapshot current live state
        addressBookStateList.add(new AddressBook(this));
        currentStatePointer++;
    }

    /**
     * Restores the previous address book state from addressBookStateList.
     */
    public void undo() {
        if (!canUndoAddressBook()) {
            throw new AssertionError("No undoable state available.");
        }
        currentStatePointer--;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Restores a previously undone address book state from addressBookStateLIst.
     */
    public void redo() {
        if (!canRedoAddressBook()) {
            throw new AssertionError("No redoable state available.");
        }
        currentStatePointer++;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if there is an address book state to undo to.
     */
    public boolean canUndoAddressBook() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if there is an address book state to redo to.
     */
    public boolean canRedoAddressBook() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }
}

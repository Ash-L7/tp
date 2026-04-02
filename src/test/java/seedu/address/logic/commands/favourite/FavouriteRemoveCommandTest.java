package seedu.address.logic.commands.favourite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVOURITE_STATUS_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVOURITE_STATUS_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

public class FavouriteRemoveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Contact originalContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact contactToUnfavourite = ContactBuilder.fromContact(
                originalContact).withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE).build();
        model.setContact(originalContact, contactToUnfavourite);

        FavouriteRemoveCommand command = new FavouriteRemoveCommand(INDEX_FIRST_CONTACT);

        Contact expectedEditedContact = ContactBuilder.fromContact(
                contactToUnfavourite).withFavouriteStatus(VALID_FAVOURITE_STATUS_FALSE).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToUnfavourite, expectedEditedContact);
        expectedModel.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);

        assertCommandSuccess(command, model,
                String.format(FavouriteRemoveCommand.MESSAGE_REMOVE_FAVOURITE_SUCCESS,
                        Messages.format(expectedEditedContact)),
                expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        FavouriteRemoveCommand command = new FavouriteRemoveCommand(outOfBoundsIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyNonFavourite_failure() {
        FavouriteRemoveCommand command = new FavouriteRemoveCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(command, model, FavouriteRemoveCommand.MESSAGE_DUPLICATE_NON_FAVOURITE);
    }

    @Test
    public void equals() {
        FavouriteRemoveCommand firstCommand = new FavouriteRemoveCommand(INDEX_FIRST_CONTACT);
        FavouriteRemoveCommand secondCommand = new FavouriteRemoveCommand(INDEX_SECOND_CONTACT);

        assertTrue(firstCommand.equals(firstCommand));
        assertTrue(firstCommand.equals(new FavouriteRemoveCommand(INDEX_FIRST_CONTACT)));
        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        FavouriteRemoveCommand command = new FavouriteRemoveCommand(INDEX_FIRST_CONTACT);
        String expected = FavouriteRemoveCommand.class.getCanonicalName()
                + "{contactIndex=" + INDEX_FIRST_CONTACT + "}";
        assertEquals(expected, command.toString());
    }
}

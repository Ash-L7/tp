package seedu.address.logic.commands.favourite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class FavouriteAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Contact contactToFavourite = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        FavouriteAddCommand command = new FavouriteAddCommand(INDEX_FIRST_CONTACT);

        Contact expectedEditedContact = ContactBuilder.fromContact(
                contactToFavourite).withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToFavourite, expectedEditedContact);
        expectedModel.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);

        assertCommandSuccess(command, model,
                String.format(FavouriteAddCommand.MESSAGE_ADD_FAVOURITE_SUCCESS,
                        Messages.format(expectedEditedContact)),
                expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        FavouriteAddCommand command = new FavouriteAddCommand(outOfBoundsIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyFavourite_failure() {
        Contact contactToFavourite = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact expectedAlreadyFavouriteContact = ContactBuilder.fromContact(
                contactToFavourite).withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE).build();
        model.setContact(contactToFavourite, expectedAlreadyFavouriteContact);

        FavouriteAddCommand command = new FavouriteAddCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(command, model, FavouriteAddCommand.MESSAGE_DUPLICATE_FAVOURITE);
    }

    @Test
    public void equals() {
        FavouriteAddCommand firstCommand = new FavouriteAddCommand(INDEX_FIRST_CONTACT);
        FavouriteAddCommand secondCommand = new FavouriteAddCommand(INDEX_SECOND_CONTACT);

        assertTrue(firstCommand.equals(firstCommand));
        assertTrue(firstCommand.equals(new FavouriteAddCommand(INDEX_FIRST_CONTACT)));
        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        FavouriteAddCommand command = new FavouriteAddCommand(INDEX_FIRST_CONTACT);
        String expected = FavouriteAddCommand.class.getCanonicalName()
                + "{contactIndex=" + INDEX_FIRST_CONTACT + "}";
        assertEquals(expected, command.toString());
    }
}

package seedu.address.logic.commands.favourite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVOURITE_STATUS_TRUE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

public class FavouriteViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_someFavouritesOnly_showsFavouriteContacts() {
        Contact firstContact = model.getFilteredContactList().get(0);
        Contact secondContact = model.getFilteredContactList().get(1);

        Contact favouriteFirstContact = ContactBuilder.fromContact(firstContact)
                .withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE)
                .build();
        Contact favouriteSecondContact = ContactBuilder.fromContact(secondContact)
                .withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE)
                .build();

        model.setContact(firstContact, favouriteFirstContact);
        model.setContact(secondContact, favouriteSecondContact);

        FavouriteViewCommand command = new FavouriteViewCommand();
        CommandResult result = command.execute(model);

        assertEquals(String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2), result.getFeedbackToUser());
        assertEquals(2, model.getFilteredContactList().size());
        assertEquals(favouriteFirstContact, model.getFilteredContactList().get(0));
        assertEquals(favouriteSecondContact, model.getFilteredContactList().get(1));
    }

    @Test
    public void execute_noFavourites_showsEmptyList() {
        FavouriteViewCommand command = new FavouriteViewCommand();
        CommandResult result = command.execute(model);

        assertEquals(String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0), result.getFeedbackToUser());
        assertEquals(0, model.getFilteredContactList().size());
    }

    @Test
    public void toStringMethod() {
        FavouriteViewCommand command = new FavouriteViewCommand();
        String expected = FavouriteViewCommand.class.getCanonicalName()
                + "{predicate=" + new seedu.address.model.contact.ContactIsFavouritePredicate() + "}";
        assertEquals(expected, command.toString());
    }
}

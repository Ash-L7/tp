package seedu.address.logic.commands.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactIsInTourPredicate;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code TourViewCommand}.
 */
public class TourViewCommandTest {

    @Test
    public void equals() {
        TourViewCommand viewFirstCommand = new TourViewCommand(Index.fromOneBased(1));
        TourViewCommand viewSecondCommand = new TourViewCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        TourViewCommand viewFirstCommandCopy = new TourViewCommand(Index.fromOneBased(1));
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_noContactsInTour_noContactFound() {
        Tour emptyTour = new Tour("empty tour");

        AddressBook ab = new AddressBook();
        ab.addTour(emptyTour);

        Model testModel = new ModelManager(ab, new UserPrefs());
        Model testExpectedModel = new ModelManager(ab, new UserPrefs());

        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        TourViewCommand command = new TourViewCommand(Index.fromOneBased(1));
        testExpectedModel.updateFilteredContactList(new ContactIsInTourPredicate(emptyTour));
        assertCommandSuccess(command, testModel, expectedMessage, testExpectedModel);
        assertEquals(java.util.Collections.emptyList(), testModel.getFilteredContactList());
    }

    @Test
    public void execute_tourWithContacts_contactsFound() {
        Tour targetTour = new Tour("walking tour");
        Tour otherTour = new Tour("food tour");
        Contact alice = new PersonBuilder().withName("Alice").withTours("walking tour").build();
        Contact bob = new PersonBuilder().withName("Bob").withTours("walking tour").build();
        Contact charlie = new PersonBuilder().withName("Charlie").withTours("food tour").build();

        AddressBook ab = new AddressBook();
        ab.addTour(targetTour);
        ab.addTour(otherTour);
        ab.addContact(alice);
        ab.addContact(bob);
        ab.addContact(charlie);

        Model testModel = new ModelManager(ab, new UserPrefs());
        Model testExpectedModel = new ModelManager(ab, new UserPrefs());

        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        TourViewCommand command = new TourViewCommand(Index.fromOneBased(1));
        testExpectedModel.updateFilteredContactList(new ContactIsInTourPredicate(targetTour));
        assertCommandSuccess(command, testModel, expectedMessage, testExpectedModel);
        assertEquals(Arrays.asList(alice, bob), testModel.getFilteredContactList());
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        TourViewCommand viewTourCommand = new TourViewCommand(index);
        String expected = TourViewCommand.class.getCanonicalName() + "{targetIndex=" + index + "}";
        assertEquals(expected, viewTourCommand.toString());
    }
}

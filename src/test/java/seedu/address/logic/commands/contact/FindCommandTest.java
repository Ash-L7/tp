package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.TYPE_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.TYPE_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.TYPE_FNB;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.AL_AZHAR;
import static seedu.address.testutil.TypicalContacts.CARL;
import static seedu.address.testutil.TypicalContacts.ELLE;
import static seedu.address.testutil.TypicalContacts.FIONA;
import static seedu.address.testutil.TypicalContacts.HOTEL;
import static seedu.address.testutil.TypicalContacts.USS;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactTypePredicate;
import seedu.address.model.contact.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ContactTypePredicate firstTypePredicate = new ContactTypePredicate(TYPE_FNB);
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        ContactTypePredicate secondTypePredicate = new ContactTypePredicate(TYPE_ATTRACTION);
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstTypePredicate, firstNamePredicate);
        FindCommand findSecondCommand = new FindCommand(secondTypePredicate, secondNamePredicate);
        FindCommand anyTypeFindCommand = new FindCommand(ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE,
                firstNamePredicate);
        FindCommand anyNameFindCommand = new FindCommand(firstTypePredicate,
                NameContainsKeywordsPredicate.ANY_NAME_ALLOWED_PREDICATE);
        FindCommand findThirdCommand = new FindCommand(firstTypePredicate, secondNamePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstTypePredicate, firstNamePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        FindCommand anyTypeFindCommandCopy = new FindCommand(ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE,
                firstNamePredicate);
        assertTrue(anyTypeFindCommand.equals(anyTypeFindCommandCopy));

        FindCommand anyNameFindCommandCopy = new FindCommand(firstTypePredicate,
                NameContainsKeywordsPredicate.ANY_NAME_ALLOWED_PREDICATE);
        assertTrue(anyNameFindCommand.equals(anyNameFindCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findFirstCommand.equals(findThirdCommand));
        assertFalse(findSecondCommand.equals(findThirdCommand));
        assertFalse(findFirstCommand.equals(anyTypeFindCommand));
        assertFalse(findFirstCommand.equals(anyNameFindCommand));
    }

    @Test
    public void execute_zeroKeywords_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactTypePredicate typePredicate = ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE;
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(typePredicate, namePredicate);
        expectedModel.updateFilteredContactList(testTypeNamePredicate(typePredicate, namePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        ContactTypePredicate typePredicate = new ContactTypePredicate(TYPE_PERSON);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(typePredicate, namePredicate);
        expectedModel.updateFilteredContactList(testTypeNamePredicate(typePredicate, namePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredContactList());
    }

    @Test
    public void execute_anyTypeMultipleKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 4);
        ContactTypePredicate typePredicate = ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE;
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Azhar USS Hotel Alice");
        FindCommand command = new FindCommand(typePredicate, namePredicate);
        expectedModel.updateFilteredContactList(testTypeNamePredicate(typePredicate, namePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, AL_AZHAR, USS, HOTEL), model.getFilteredContactList());
    }

    @Test
    public void toStringMethod() {
        ContactTypePredicate typePredicate = new ContactTypePredicate(TYPE_ACCOMMODATION);
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(typePredicate, namePredicate);
        String expected = FindCommand.class.getCanonicalName()
                + String.format("{typePredicate=%s, namePredicate=%s}", typePredicate, namePredicate);
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private Predicate<Contact> testTypeNamePredicate(ContactTypePredicate typePredicate,
                                                     NameContainsKeywordsPredicate namePredicate) {
        return contact -> typePredicate.test(contact) && namePredicate.test(contact);
    }
}

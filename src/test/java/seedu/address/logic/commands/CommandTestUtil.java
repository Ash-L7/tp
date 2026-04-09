package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.EditContactDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TYPE_AMY = "person";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_TYPE_BOB = "person";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_FAVOURITE_STATUS_TRUE = "true";
    public static final String VALID_FAVOURITE_STATUS_FALSE = "false";
    public static final String INVALID_FAVOURITE_STATUS = "maybe";

    public static final String VALID_TYPE_FNB = "fnb";
    public static final String VALID_TYPE_ATTRACTION = "attraction";
    public static final String VALID_TYPE_ACCOMMODATION = "accomm";

    public static final String VALID_NAME_FNB = "Al Azhar";
    public static final String VALID_NAME_ATTRACTION = "USS";
    public static final String VALID_NAME_ACCOMMODATION = "Hotel 81";

    public static final String VALID_PHONE_FNB = "63334444";
    public static final String VALID_PHONE_ATTRACTION = "65552222";
    public static final String VALID_PHONE_ACCOMMODATION = "61234567";

    public static final String VALID_EMAIL_FNB = "alazhar@example.com";
    public static final String VALID_EMAIL_ATTRACTION = "uss@example.com";
    public static final String VALID_EMAIL_ACCOMMODATION = "hotel81@example.com";

    public static final String VALID_ADDRESS_FNB = "1 Jalan Besar";
    public static final String VALID_ADDRESS_ATTRACTION = "1 Sentosa Drive";
    public static final String VALID_ADDRESS_ACCOMMODATION = "1 Hotel Drive";

    public static final String VALID_TAG_INDIAN = "Indian";
    public static final String VALID_TAG_FUN = "fun";
    public static final String VALID_TAG_STAY = "stay";

    public static final String VALID_HALAL_STATUS_TRUE_FNB = "true";
    public static final String VALID_HALAL_STATUS_FALSE_FNB = "false";
    public static final String VALID_OPENING_HOUR_DEFAULT_ATTRACTION = "08:00";
    public static final String VALID_CLOSING_HOUR_DEFAULT_ATTRACTION = "22:00";
    public static final String VALID_OPENING_HOUR_ATTRACTION = "09:00";
    public static final String VALID_CLOSING_HOUR_ATTRACTION = "23:00";
    public static final String VALID_STARS_DEFAULT_ACCOMMODATION = "3";
    public static final String VALID_STARS_ACCOMMODATION = "4";

    public static final String VALID_TOUR_NAME_JAMES = "Le Tour James";
    public static final String VALID_TOUR_NAME_JAMES_JR = "Le Tour James Jr";
    public static final String VALID_TOUR_NAME_JAMES_SR = "Le Tour James Sr";
    public static final String VALID_TOUR_NAME_HALLOWEEN_WALK = "Halloween Walk";
    public static final String VALID_TOUR_FAVOURITE_STATUS_TRUE = "true";
    public static final String VALID_TOUR_FAVOURITE_STATUS_FALSE = "false";
    public static final String INVALID_TOUR_FAVOURITE_STATUS = "maybe";

    public static final String TYPE_DESC_AMY = " " + PREFIX_TYPE + VALID_TYPE_AMY;
    public static final String TYPE_DESC_BOB = " " + PREFIX_TYPE + VALID_TYPE_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String TYPE_DESC_FNB = " " + PREFIX_TYPE + VALID_TYPE_FNB;
    public static final String TYPE_DESC_ATTRACTION = " " + PREFIX_TYPE + VALID_TYPE_ATTRACTION;
    public static final String TYPE_DESC_ACCOMMODATION = " " + PREFIX_TYPE + VALID_TYPE_ACCOMMODATION;

    public static final String INVALID_TYPE_DESC = " " + PREFIX_TYPE + "people";
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "+651234"; // '+' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String TOUR_NAME_DESC_JAMES = " " + PREFIX_NAME + VALID_TOUR_NAME_JAMES;
    public static final String TOUR_NAME_DESC_JAMES_JR = " " + PREFIX_NAME + VALID_TOUR_NAME_JAMES_JR;

    public static final String INVALID_TOUR_NAME_DESC = " " + PREFIX_NAME + "Le Tour James Sr.";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditContactDescriptor DESC_AMY;
    public static final EditCommand.EditContactDescriptor DESC_BOB;
    public static final EditCommand.EditContactDescriptor DESC_FNB;
    public static final EditCommand.EditContactDescriptor DESC_ATTRACTION;
    public static final EditCommand.EditContactDescriptor DESC_ACCOMMODATION;

    static {
        DESC_AMY = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_FNB = new EditContactDescriptorBuilder().withName(VALID_NAME_FNB)
                .withPhone(VALID_PHONE_FNB).withEmail(VALID_EMAIL_FNB).withAddress(VALID_ADDRESS_FNB)
                .withTags(VALID_TAG_INDIAN).withHalalStatus(VALID_HALAL_STATUS_TRUE_FNB)
                .withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE).build();

        DESC_ATTRACTION = new EditContactDescriptorBuilder().withName(VALID_NAME_ATTRACTION)
                .withPhone(VALID_PHONE_ATTRACTION).withEmail(VALID_EMAIL_ATTRACTION)
                .withAddress(VALID_ADDRESS_ATTRACTION).withTags(VALID_TAG_FUN)
                .withOpeningHour(VALID_OPENING_HOUR_ATTRACTION)
                .withClosingHour(VALID_CLOSING_HOUR_ATTRACTION)
                .withFavouriteStatus(VALID_FAVOURITE_STATUS_FALSE).build();

        DESC_ACCOMMODATION = new EditContactDescriptorBuilder().withName(VALID_NAME_ACCOMMODATION)
                .withPhone(VALID_PHONE_ACCOMMODATION).withEmail(VALID_EMAIL_ACCOMMODATION)
                .withAddress(VALID_ADDRESS_ACCOMMODATION).withTags(VALID_TAG_STAY)
                .withStars(VALID_STARS_ACCOMMODATION)
                .withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered contact list and selected contact in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredContactList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredContactList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the contact at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());

        Contact contact = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().fullName.split("\\s+");
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredContactList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tour at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTourAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTourList().size());

        Tour tour = model.getFilteredTourList().get(targetIndex.getZeroBased());
        model.updateFilteredTourList(t -> t.tourName.equals(tour.tourName));

        assertEquals(1, model.getFilteredTourList().size());
    }

}

package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.TOUR_NAME_DESC_JAMES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.AddCommand;
import seedu.address.logic.commands.contact.DeleteCommand;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.commands.contact.ListCommand;
import seedu.address.logic.commands.general.ClearCommand;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.commands.tour.TourAddCommand;
import seedu.address.logic.commands.tour.TourDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactTypePredicate;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.ContactUtil;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Contact contact = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddCommand(contact), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact contact = new PersonBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CONTACT.getOneBased() + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(ContactTypePredicate.ANY_TYPE_ALLOWED_PREDICATE,
                new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_tourAdd() throws Exception {
        Tour tour = TOUR_JAMES;
        TourAddCommand command = (TourAddCommand) parser.parseCommand(TourAddCommand.COMMAND_WORD
                + TOUR_NAME_DESC_JAMES);
        assertEquals(new TourAddCommand(tour), command);
    }

    @Test
    public void parseCommand_tourDelete() throws Exception {
        TourDeleteCommand command = (TourDeleteCommand) parser.parseCommand(TourDeleteCommand.COMMAND_WORD
                + " " + INDEX_FIRST_TOUR.getOneBased());
        assertEquals(new TourDeleteCommand(INDEX_FIRST_TOUR), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}

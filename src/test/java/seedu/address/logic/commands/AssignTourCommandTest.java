package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TOUR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AssignTourCommand.
 */
public class AssignTourCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        // Add tours to the model so tour indices are valid
        model.addTour(new Tour("City Tour"));
        model.addTour(new Tour("Beach Tour"));
    }

    @Test
    public void execute_validIndices_success() {
        Contact contactToAssign = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Tour tourToAssign = model.getFilteredTourList().get(INDEX_FIRST_TOUR.getZeroBased());

        AssignTourCommand command = new AssignTourCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR);

        String expectedMessage = String.format(AssignTourCommand.MESSAGE_ASSIGN_TOUR_SUCCESS,
                Messages.format(contactToAssign));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Contact expectedContact = contactToAssign.edit(buildDescriptorWithTour(contactToAssign, tourToAssign));
        expectedModel.setContact(contactToAssign, expectedContact);

        assertCommandSuccess(command, model,
                String.format(AssignTourCommand.MESSAGE_ASSIGN_TOUR_SUCCESS, Messages.format(expectedContact)),
                expectedModel);
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        AssignTourCommand command = new AssignTourCommand(outOfBoundsIndex, INDEX_FIRST_TOUR);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTourIndex_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredTourList().size() + 1);
        AssignTourCommand command = new AssignTourCommand(INDEX_FIRST_CONTACT, outOfBoundsIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateTour_throwsCommandException() throws Exception {
        // Assign the tour once first
        new AssignTourCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR).execute(model);

        // Attempt to assign the same tour again
        AssignTourCommand command = new AssignTourCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR);
        assertCommandFailure(command, model, AssignTourCommand.MESSAGE_DUPLICATE_TOUR);
    }

    @Test
    public void equals() {
        AssignTourCommand firstCommand = new AssignTourCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR);
        AssignTourCommand secondCommand = new AssignTourCommand(INDEX_SECOND_CONTACT, INDEX_SECOND_TOUR);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        assertTrue(firstCommand.equals(new AssignTourCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR)));

        // different contact index -> returns false
        assertFalse(firstCommand.equals(new AssignTourCommand(INDEX_SECOND_CONTACT, INDEX_FIRST_TOUR)));

        // different tour index -> returns false
        assertFalse(firstCommand.equals(new AssignTourCommand(INDEX_FIRST_CONTACT, INDEX_SECOND_TOUR)));

        // different types -> returns false
        assertFalse(firstCommand.equals("string"));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Builds an EditContactDescriptor that adds {@code tour} to the existing tours of {@code contact}.
     */
    private EditCommand.EditContactDescriptor buildDescriptorWithTour(Contact contact, Tour tour) {
        java.util.Set<Tour> updatedTours = new java.util.HashSet<>(contact.getTours());
        updatedTours.add(tour);
        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setTours(updatedTours);
        return descriptor;
    }
}

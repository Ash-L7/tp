package seedu.address.logic.commands.tour;

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
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TourUnassignCommand.
 */
public class TourUnassignCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addTour(new Tour("City Tour"));
        model.addTour(new Tour("Beach Tour"));
        // Pre-assign tour 1 to contact 1 so unassign tests have something to remove
        new TourAssignCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR).execute(model);
    }

    @Test
    public void execute_validIndices_success() {
        Contact contactToUnassign = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Tour tourToUnassign = model.getFilteredTourList().get(INDEX_FIRST_TOUR.getZeroBased());

        TourUnassignCommand command = new TourUnassignCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToUnassign, contactToUnassign.withTourRemoved(tourToUnassign));

        assertCommandSuccess(command, model,
                String.format(TourUnassignCommand.MESSAGE_UNASSIGN_TOUR_SUCCESS, Messages.format(contactToUnassign)),
                expectedModel);
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        TourUnassignCommand command = new TourUnassignCommand(outOfBoundsIndex, INDEX_FIRST_TOUR);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTourIndex_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredTourList().size() + 1);
        TourUnassignCommand command = new TourUnassignCommand(INDEX_FIRST_CONTACT, outOfBoundsIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_contactNotInTour_throwsCommandException() {
        // Contact 1 is only assigned to tour 1 (from setUp), not tour 2
        TourUnassignCommand command = new TourUnassignCommand(INDEX_FIRST_CONTACT, INDEX_SECOND_TOUR);

        assertCommandFailure(command, model, TourUnassignCommand.MESSAGE_NOT_IN_TOUR);
    }

    @Test
    public void equals() {
        TourUnassignCommand firstCommand = new TourUnassignCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR);
        TourUnassignCommand secondCommand = new TourUnassignCommand(INDEX_SECOND_CONTACT, INDEX_SECOND_TOUR);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        assertTrue(firstCommand.equals(new TourUnassignCommand(INDEX_FIRST_CONTACT, INDEX_FIRST_TOUR)));

        // different contact index -> returns false
        assertFalse(firstCommand.equals(new TourUnassignCommand(INDEX_SECOND_CONTACT, INDEX_FIRST_TOUR)));

        // different tour index -> returns false
        assertFalse(firstCommand.equals(new TourUnassignCommand(INDEX_FIRST_CONTACT, INDEX_SECOND_TOUR)));

        // different types -> returns false
        assertFalse(firstCommand.equals("string"));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Builds an EditContactDescriptor that removes {@code tour} from the existing tours of {@code contact}.
     */
    private EditCommand.EditContactDescriptor buildDescriptorWithoutTour(Contact contact, Tour tour) {
        java.util.Set<Tour> updatedTours = new java.util.HashSet<>(contact.getTours());
        updatedTours.remove(tour);
        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setTours(updatedTours);
        return descriptor;
    }
}

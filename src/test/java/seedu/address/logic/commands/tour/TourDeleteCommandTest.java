package seedu.address.logic.commands.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTourAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES_JR;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TOUR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tour.Tour;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TourDeleteCommand}.
 */
public class TourDeleteCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        // Add tours to the model so tour indices are valid
        model.addTour(TOUR_JAMES);
        model.addTour(TOUR_JAMES_JR);
    }

    @Test
    public void execute_nullIndex_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> new TourDeleteCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        // equivalence partition: in full list
        Tour tourToDelete = model.getFilteredTourList().get(INDEX_FIRST_TOUR.getZeroBased());
        TourDeleteCommand tourDeleteCommand = new TourDeleteCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(TourDeleteCommand.MESSAGE_DELETE_TOUR_SUCCESS,
                Messages.format(tourToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTour(tourToDelete);

        assertCommandSuccess(tourDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        // equivalence partition: not in full list
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTourList().size() + 1);
        TourDeleteCommand tourDeleteCommand = new TourDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tourDeleteCommand, model, Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // equivalence partition: in filtered list
        showTourAtIndex(model, INDEX_FIRST_TOUR);

        Tour tourToDelete = model.getFilteredTourList().get(INDEX_FIRST_TOUR.getZeroBased());
        TourDeleteCommand tourDeleteCommand = new TourDeleteCommand(INDEX_FIRST_TOUR);

        String expectedMessage = String.format(TourDeleteCommand.MESSAGE_DELETE_TOUR_SUCCESS,
                Messages.format(tourToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTour(tourToDelete);
        showNoTour(expectedModel);

        assertCommandSuccess(tourDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        // equivalence partition: not in filtered list
        showTourAtIndex(model, INDEX_FIRST_TOUR);

        Index outOfBoundIndex = INDEX_SECOND_TOUR;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTourList().size());

        TourDeleteCommand tourDeleteCommand = new TourDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tourDeleteCommand, model, Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TourDeleteCommand tourDeleteFirstCommand = new TourDeleteCommand(INDEX_FIRST_TOUR);
        TourDeleteCommand tourDeleteSecondCommand = new TourDeleteCommand(INDEX_SECOND_TOUR);

        // same object -> returns true
        assertTrue(tourDeleteFirstCommand.equals(tourDeleteFirstCommand));

        // same values -> returns true
        TourDeleteCommand tourDeleteFirstCommandCopy = new TourDeleteCommand(INDEX_FIRST_TOUR);
        assertTrue(tourDeleteFirstCommand.equals(tourDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(tourDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(tourDeleteFirstCommand.equals(null));

        // different tour -> returns false
        assertFalse(tourDeleteFirstCommand.equals(tourDeleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        TourDeleteCommand tourDeleteCommand = new TourDeleteCommand(targetIndex);
        String expected = TourDeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, tourDeleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTour(Model model) {
        model.updateFilteredTourList(p -> false);

        assertTrue(model.getFilteredTourList().isEmpty());
    }
}

package seedu.address.logic.commands.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOUR_NAME_JAMES;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES_JR;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tour.Tour;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TourAddCommand.
 */
public class TourAddCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_nullTour_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> new TourAddCommand(null));
    }

    @Test
    public void execute_newTour_success() {
        // equivalence partition: not in list
        Tour validTour = TOUR_JAMES;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTour(validTour);

        assertCommandSuccess(new TourAddCommand(validTour), model,
                String.format(TourAddCommand.MESSAGE_SUCCESS, Messages.format(validTour)),
                expectedModel);
    }

    @Test
    public void execute_duplicateTour_throwsCommandException() {
        // equivalence partition: in list
        Tour tour = TOUR_JAMES;
        model.addTour(tour);
        assertCommandFailure(new TourAddCommand(tour), model,
                TourAddCommand.MESSAGE_DUPLICATE_TOUR);
    }


    @Test
    public void equals() {
        Tour james = TOUR_JAMES;
        Tour jamesJr = TOUR_JAMES_JR;
        TourAddCommand addJamesCommand = new TourAddCommand(james);
        TourAddCommand addJamesJrCommand = new TourAddCommand(jamesJr);

        // same object -> returns true
        assertTrue(addJamesCommand.equals(addJamesCommand));

        // same values -> returns true
        TourAddCommand addJamesCommandCopy = new TourAddCommand(james);
        assertTrue(addJamesCommand.equals(addJamesCommandCopy));

        // different types -> returns false
        assertFalse(addJamesCommand.equals(1));

        // null -> returns false
        assertFalse(addJamesCommand.equals(null));

        // different contact -> returns false
        assertFalse(addJamesCommand.equals(addJamesJrCommand));
    }

    @Test
    public void toStringMethod() {
        TourAddCommand tourAddCommand = new TourAddCommand(TOUR_JAMES);
        String expected = TourAddCommand.class.getCanonicalName() + "{toAdd=" + VALID_TOUR_NAME_JAMES + "}";
        assertEquals(expected, tourAddCommand.toString());
    }

}

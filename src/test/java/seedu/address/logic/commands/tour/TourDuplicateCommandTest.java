package seedu.address.logic.commands.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TOUR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tour.Tour;

public class TourDuplicateCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addTour(new Tour("City Tour"));
        model.addTour(new Tour("Beach Tour"));
        // Assign tour 1 to contact 1 so duplicate can test contact reassignment
        new TourAssignCommand(INDEX_FIRST_TOUR,
                seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT).execute(model);
    }

    @Test
    public void execute_validIndex_success() {
        Tour tourToDuplicate = model.getFilteredTourList().get(INDEX_FIRST_TOUR.getZeroBased());
        String newName = "City Tour Copy";
        TourDuplicateCommand command = new TourDuplicateCommand(INDEX_FIRST_TOUR, newName);

        Tour newTour = new Tour(newName);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addTour(newTour);
        // Contact 1 was assigned to tour 1, so it should also be assigned to the duplicate
        expectedModel.assignTour(
                expectedModel.getFilteredContactList().get(
                        seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT.getZeroBased()),
                newTour);

        assertCommandSuccess(command, model,
                String.format(TourDuplicateCommand.MESSAGE_SUCCESS, Messages.format(newTour)),
                expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredTourList().size() + 1);
        TourDuplicateCommand command = new TourDuplicateCommand(outOfBoundsIndex, "Some Name");

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateTourName_throwsCommandException() {
        // "City Tour" already exists
        TourDuplicateCommand command = new TourDuplicateCommand(INDEX_FIRST_TOUR, "City Tour");

        assertCommandFailure(command, model, TourDuplicateCommand.MESSAGE_DUPLICATE_TOUR);
    }

    @Test
    public void toStringMethod() {
        TourDuplicateCommand command = new TourDuplicateCommand(INDEX_FIRST_TOUR, "New Tour Name");
        String expected = new ToStringBuilder(command)
                .add("targetIndex", INDEX_FIRST_TOUR)
                .add("name", "New Tour Name")
                .toString();
        assertEquals(expected, command.toString());
    }

    @Test
    public void equals() {
        TourDuplicateCommand firstCommand = new TourDuplicateCommand(INDEX_FIRST_TOUR, "Copy A");
        TourDuplicateCommand secondCommand = new TourDuplicateCommand(INDEX_SECOND_TOUR, "Copy B");

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        assertTrue(firstCommand.equals(new TourDuplicateCommand(INDEX_FIRST_TOUR, "Copy A")));

        // different index -> returns false
        assertFalse(firstCommand.equals(new TourDuplicateCommand(INDEX_SECOND_TOUR, "Copy A")));

        // different name -> returns false
        assertFalse(firstCommand.equals(new TourDuplicateCommand(INDEX_FIRST_TOUR, "Copy B")));

        // different types -> returns false
        assertFalse(firstCommand.equals("string"));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}

package seedu.address.logic.commands.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_TOURS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.TOUR_HALLOWEEN_WALK;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES_JR;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES_SR;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tour.TourNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code TourFindCommand}.
 */
public class TourFindCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        // Add tours to the model so tour indices are valid
        model.addTour(TOUR_JAMES);
        model.addTour(TOUR_JAMES_JR);
        model.addTour(TOUR_JAMES_SR);
        model.addTour(TOUR_HALLOWEEN_WALK);
        expectedModel.addTour(TOUR_JAMES);
        expectedModel.addTour(TOUR_JAMES_JR);
        expectedModel.addTour(TOUR_JAMES_SR);
        expectedModel.addTour(TOUR_HALLOWEEN_WALK);
    }

    @Test
    public void equals() {
        TourNameContainsKeywordsPredicate firstPredicate =
                new TourNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TourNameContainsKeywordsPredicate secondPredicate =
                new TourNameContainsKeywordsPredicate(Collections.singletonList("second"));

        TourFindCommand findFirstCommand = new TourFindCommand(firstPredicate);
        TourFindCommand findSecondCommand = new TourFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        assertTrue(findFirstCommand.equals(new TourFindCommand(firstPredicate)));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTourFound() {
        String expectedMessage = String.format(MESSAGE_TOURS_LISTED_OVERVIEW, 0);
        TourNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        TourFindCommand command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTourList());
    }

    @Test
    public void execute_singleKeywords_multipleToursFound() {
        String expectedMessage = String.format(MESSAGE_TOURS_LISTED_OVERVIEW, 3);
        TourNameContainsKeywordsPredicate predicate = preparePredicate("James");
        TourFindCommand command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_JAMES, TOUR_JAMES_JR, TOUR_JAMES_SR), model.getFilteredTourList());
    }

    @Test
    public void execute_singleKeyword_singleTourFound() {
        // Case 1: Tour contains keyword "Jr"
        String expectedMessage = String.format(MESSAGE_TOURS_LISTED_OVERVIEW, 1);
        TourNameContainsKeywordsPredicate predicate = preparePredicate("Jr");
        TourFindCommand command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_JAMES_JR), model.getFilteredTourList());

        // Case 2: Tour contains keyword "Sr"
        predicate = preparePredicate("Sr");
        command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_JAMES_SR), model.getFilteredTourList());

        // Case 3: Tour contains keyword "Halloween"
        predicate = preparePredicate("Halloween");
        command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_HALLOWEEN_WALK), model.getFilteredTourList());

        // Case 4: Tour contains keyword "Walk"
        predicate = preparePredicate("Walk");
        command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_HALLOWEEN_WALK), model.getFilteredTourList());
    }

    @Test
    public void execute_multipleKeyword_singleTourFound() {
        String expectedMessage = String.format(MESSAGE_TOURS_LISTED_OVERVIEW, 1);
        TourNameContainsKeywordsPredicate predicate = preparePredicate("Halloween Walk");
        TourFindCommand command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_HALLOWEEN_WALK), model.getFilteredTourList());
    }

    @Test
    public void execute_multipleKeyword_multipleToursFound() {
        String expectedMessage = String.format(MESSAGE_TOURS_LISTED_OVERVIEW, 2);
        TourNameContainsKeywordsPredicate predicate = preparePredicate("Jr Sr");
        TourFindCommand command = new TourFindCommand(predicate);
        expectedModel.updateFilteredTourList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TOUR_JAMES_JR, TOUR_JAMES_SR), model.getFilteredTourList());
    }

    @Test
    public void toStringMethod() {
        TourNameContainsKeywordsPredicate predicate = new TourNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        TourFindCommand findCommand = new TourFindCommand(predicate);
        String expected = TourFindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TourNameContainsKeywordsPredicate}.
     */
    private TourNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TourNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

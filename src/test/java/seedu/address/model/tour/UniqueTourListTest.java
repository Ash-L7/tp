package seedu.address.model.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES_JR;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tour.exceptions.DuplicateTourException;
import seedu.address.model.tour.exceptions.TourNotFoundException;

public class UniqueTourListTest {

    private final UniqueTourList uniqueTourList = new UniqueTourList();

    @Test
    public void contains_nullTour_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> uniqueTourList.contains(null));
    }

    @Test
    public void contains_tourNotInList_returnsFalse() {
        // equivalence partition: not in list
        assertFalse(uniqueTourList.contains(TOUR_JAMES));
    }

    @Test
    public void contains_tourInList_returnsTrue() {
        // equivalence partition: in list
        uniqueTourList.add(TOUR_JAMES);
        assertTrue(uniqueTourList.contains(TOUR_JAMES));
    }

    @Test
    public void add_nullTour_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> uniqueTourList.add(null));
    }

    @Test
    public void add_duplicateTour_throwsDuplicateTourException() {
        // equivalence partition: in list
        uniqueTourList.add(TOUR_JAMES);
        assertThrows(DuplicateTourException.class, () -> uniqueTourList.add(TOUR_JAMES));
    }

    @Test
    public void remove_nullTour_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> uniqueTourList.remove(null));
    }

    @Test
    public void remove_tourDoesNotExist_throwsTourNotFoundException() {
        // equivalence partition: not in list
        assertThrows(TourNotFoundException.class, () -> uniqueTourList.remove(TOUR_JAMES));
    }

    @Test
    public void remove_existingTour_removesTour() {
        // equivalence partition: in list
        uniqueTourList.add(TOUR_JAMES);
        uniqueTourList.remove(TOUR_JAMES);
        UniqueTourList expectedUniqueTourList = new UniqueTourList();
        assertEquals(expectedUniqueTourList, uniqueTourList);
    }

    @Test
    public void setTours_nullUniqueTourList_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> uniqueTourList.setTours((UniqueTourList) null));
    }

    @Test
    public void setTours_uniqueTourList_replacesOwnListWithProvidedUniqueTourList() {
        // equivalence partition: UniqueTourList
        uniqueTourList.add(TOUR_JAMES);
        UniqueTourList expectedUniqueTourList = new UniqueTourList();
        expectedUniqueTourList.add(TOUR_JAMES_JR);
        uniqueTourList.setTours(expectedUniqueTourList);
        assertEquals(expectedUniqueTourList, uniqueTourList);
    }

    @Test
    public void setTours_nullList_throwsNullPointerException() {
        // equivalence partition: null
        assertThrows(NullPointerException.class, () -> uniqueTourList.setTours((List<Tour>) null));
    }

    @Test
    public void setTours_list_replacesOwnListWithProvidedList() {
        // equivalence partition: list with no duplicates
        uniqueTourList.add(TOUR_JAMES);
        List<Tour> tourList = Collections.singletonList(TOUR_JAMES_JR);
        uniqueTourList.setTours(tourList);
        UniqueTourList expectedUniqueTourList = new UniqueTourList();
        expectedUniqueTourList.add(TOUR_JAMES_JR);
        assertEquals(expectedUniqueTourList, uniqueTourList);
    }

    @Test
    public void setTours_listWithDuplicateTours_throwsDuplicateTourException() {
        // equivalence partition: list with duplicates
        List<Tour> listWithDuplicateTours = Arrays.asList(TOUR_JAMES, TOUR_JAMES);
        assertThrows(DuplicateTourException.class, () -> uniqueTourList.setTours(listWithDuplicateTours));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTourList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTourList.asUnmodifiableObservableList().toString(), uniqueTourList.toString());
    }
}

package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class OpeningHourTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OpeningHour(null));
    }

    @Test
    public void constructor_invalidOpeningHour_throwsIllegalArgumentException() {
        String invalidOpeningHour = "";
        assertThrows(IllegalArgumentException.class, () -> new OpeningHour(invalidOpeningHour));
    }

    @Test
    public void isValidOpeningHour() {
        // null opening hour
        assertThrows(NullPointerException.class, () -> OpeningHour.isValidOpeningHour(null));

        // invalid opening hours
        assertFalse(OpeningHour.isValidOpeningHour("")); // empty string
        assertFalse(OpeningHour.isValidOpeningHour(" ")); // spaces only
        assertFalse(OpeningHour.isValidOpeningHour("7:00")); // missing leading zero
        assertFalse(OpeningHour.isValidOpeningHour("07:0")); // invalid minutes
        assertFalse(OpeningHour.isValidOpeningHour("24:00")); // invalid hour
        assertFalse(OpeningHour.isValidOpeningHour("12:60")); // invalid minute
        assertFalse(OpeningHour.isValidOpeningHour("ab:cd"));
        assertFalse(OpeningHour.isValidOpeningHour("07.00"));

        // valid opening hours
        assertTrue(OpeningHour.isValidOpeningHour("00:00"));
        assertTrue(OpeningHour.isValidOpeningHour("08:00"));
        assertTrue(OpeningHour.isValidOpeningHour("12:34"));
        assertTrue(OpeningHour.isValidOpeningHour("23:59"));
    }

    @Test
    public void getOpeningHour_validValue_success() {
        OpeningHour openingHour = new OpeningHour("08:00");
        assertEquals(LocalTime.of(8, 0), openingHour.getOpeningHour());
    }

    @Test
    public void toString_validValue_success() {
        assertEquals("08:00", new OpeningHour("08:00").toString());
        assertEquals("23:59", new OpeningHour("23:59").toString());
    }

    @Test
    public void equals() {
        OpeningHour openingHour = new OpeningHour("08:00");

        // same values -> returns true
        assertTrue(openingHour.equals(new OpeningHour("08:00")));

        // same object -> returns true
        assertTrue(openingHour.equals(openingHour));

        // null -> returns false
        assertFalse(openingHour.equals(null));

        // different types -> returns false
        assertFalse(openingHour.equals(5));

        // different values -> returns false
        assertFalse(openingHour.equals(new OpeningHour("09:00")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new OpeningHour("08:00").hashCode(), new OpeningHour("08:00").hashCode());
    }
}
